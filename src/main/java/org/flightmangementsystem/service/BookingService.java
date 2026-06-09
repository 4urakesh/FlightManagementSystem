package org.flightmangementsystem.service;

import org.flightmangementsystem.entity.Payment;
import org.flightmangementsystem.entity.PaymentStatus;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.flightmangementsystem.dao.BookingDao;
import org.flightmangementsystem.dto.ResponseStructure;
import org.flightmangementsystem.entity.Booking;
import org.flightmangementsystem.entity.BookingStatus;
import org.flightmangementsystem.entity.Flight;
import org.flightmangementsystem.entity.Passenger;
import org.flightmangementsystem.exception.IdNotFoundException;
import org.flightmangementsystem.exception.NoRecordAvailableException;
import org.flightmangementsystem.exception.PaymentNotConfirmedException;
import org.flightmangementsystem.exception.flightNotFoundException;
import org.flightmangementsystem.repository.FlightRepository;

@Service
public class BookingService {

	@Autowired
	private BookingDao bookingDao;

	@Autowired
	private FlightRepository flightRepository;

	@Transactional
	public ResponseEntity<ResponseStructure<Booking>> addBooking(Booking booking) {

		// Payment validation
		if (booking.getPayment() == null || booking.getPayment().getStatus() != PaymentStatus.SUCCESS) {
			throw new PaymentNotConfirmedException("Booking cannot be created because payment is not successful");
		}
		
		//BookingStatus validation
		
		if (booking.getStatus() != BookingStatus.CONFIRMED) {
			throw new PaymentNotConfirmedException("Booking cannot be created unless status is CONFIRMED.");
		}

		// Passenger validation
		if (booking.getPassengers() == null || booking.getPassengers().isEmpty()) {
			throw new IllegalArgumentException("Cannot create booking without passengers");
		
		}
		// Flight validation
		if (booking.getFlight() == null || booking.getFlight().getId() == 0) {
			throw new flightNotFoundException("Must pass the flight details to book a Flight");
		}

		// Validate & Attach Flight
		int flightId = booking.getFlight().getId();
		Optional<Flight> flightOpt = flightRepository.findById(flightId);

		if (flightOpt.isEmpty()) {
			throw new IdNotFoundException("Flight not found in the database");
		}

		Flight flight = flightOpt.get();
		booking.setFlight(flight);

		// Attach booking to passengers
		for (Passenger p : booking.getPassengers()) {
			p.setBooking(booking);
		}

		// Calculate total amount
		int passengerCount = booking.getPassengers().size();
		double totalAmount = passengerCount * flight.getPrice();

		booking.getPayment().setAmount(totalAmount);
		booking.getPayment().setBooking(booking);

		// Save booking
		Booking savedBooking = bookingDao.addBooking(booking);

		ResponseStructure<Booking> response = new ResponseStructure<>();
		response.setStatusCode(HttpStatus.CREATED.value());
		response.setMessage("Booking Created Successfully");
		response.setData(savedBooking);

		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	// Fetch all booking
	public ResponseEntity<ResponseStructure<List<Booking>>> getAllBooking() {
		ResponseStructure<List<Booking>> response = new ResponseStructure<>();
		List<Booking> booking = bookingDao.getAllBooking();

		if (!booking.isEmpty()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("All the Booking record is fetched successfully");
			response.setData(booking);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			throw new NoRecordAvailableException("No records present in Booking");
		}
	}

	// Fetch booking by id
	public ResponseEntity<ResponseStructure<Booking>> getBookingById(Integer id) {
		ResponseStructure<Booking> response = new ResponseStructure<>();
		Optional<Booking> booking = bookingDao.getBookingById(id);

		if (booking.isPresent()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Booking record fetched successfully");
			response.setData(booking.get());
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			throw new IdNotFoundException("Booking id not found");
		}
	}

	// Fetch booking by flight id
	public ResponseEntity<ResponseStructure<List<Booking>>> getBookingByFlightId(Integer id) {
		ResponseStructure<List<Booking>> response = new ResponseStructure<>();
		List<Booking> booking = bookingDao.getBookingByFlightId(id);

		if (!booking.isEmpty()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Booking fetched by flight id successfully");
			response.setData(booking);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			throw new IdNotFoundException("No booking found for given flight id");
		}
	}

	// Fetch booking by date
	public ResponseEntity<ResponseStructure<List<Booking>>> getBookingByDate(LocalDateTime bookingDate) {
		ResponseStructure<List<Booking>> response = new ResponseStructure<>();
		List<Booking> booking = bookingDao.getBookingByDate(bookingDate);

		if (!booking.isEmpty()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Booking fetched by date successfully");
			response.setData(booking);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			throw new IdNotFoundException("No booking found for given date");
		}
	}

	// Fetch booking by status
	public ResponseEntity<ResponseStructure<List<Booking>>> getBookingByStatus(BookingStatus status) {
		ResponseStructure<List<Booking>> response = new ResponseStructure<>();
		List<Booking> booking = bookingDao.getBookingByStatus(status);

		if (!booking.isEmpty()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Booking fetched by status successfully");
			response.setData(booking);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			throw new IdNotFoundException("No booking found for given status");
		}
	}

	// Get payment by booking id
	public ResponseEntity<ResponseStructure<Payment>> getPaymentByBookingId(Integer id) {
		ResponseStructure<Payment> response = new ResponseStructure<>();
		Optional<Booking> opt = bookingDao.getBookingById(id);

		if (opt.isPresent()) {
			Payment payment = opt.get().getPayment();

			if (payment != null) {
				response.setStatusCode(HttpStatus.OK.value());
				response.setMessage("Payment fetched successfully");
				response.setData(payment);
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				throw new NoRecordAvailableException("No payment found");
			}
		} else {
			throw new IdNotFoundException("Booking not found");
		}
	}

	// Get passenger by booking id
	public ResponseEntity<ResponseStructure<List<Passenger>>> getPassengerById(Integer id) {
		ResponseStructure<List<Passenger>> response = new ResponseStructure<>();
		Optional<Booking> opt = bookingDao.getBookingById(id);

		if (opt.isPresent()) {
			List<Passenger> passengers = opt.get().getPassengers();

			if (!passengers.isEmpty()) {
				response.setStatusCode(HttpStatus.OK.value());
				response.setMessage("Passengers fetched successfully");
				response.setData(passengers);
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				throw new NoRecordAvailableException("No passengers found");
			}
		} else {
			throw new IdNotFoundException("Booking not found");
		}
	}

	// Update booking status
	public ResponseEntity<ResponseStructure<Booking>> updateStatusByBooking(Integer bookingId, BookingStatus status) {
		Optional<Booking> opt = bookingDao.getBookingById(bookingId);

		if (opt.isPresent()) {
			Booking booking = opt.get();
			booking.setStatus(status);

			Booking updated = bookingDao.updateBooking(booking);

			ResponseStructure<Booking> response = new ResponseStructure<>();
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Status updated successfully");
			response.setData(updated);

			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			throw new IdNotFoundException("Booking not found");
		}
	}

	// Delete booking
	public ResponseEntity<ResponseStructure<String>> deleteBooking(Integer id) {
		Optional<Booking> opt = bookingDao.getBookingById(id);

		if (opt.isPresent()) {
			bookingDao.deleteBooking(opt.get());

			ResponseStructure<String> response = new ResponseStructure<>();
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Booking deleted successfully");
			response.setData("Booking deleted with id " + id);

			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			throw new IdNotFoundException("Booking id not found");
		}
	}

	// Pagination
	public ResponseEntity<ResponseStructure<Page<Booking>>> GetBookingByPaginationAndSorting(Integer pageNumber,
			Integer pageSize, String field) {

		Page<Booking> page = bookingDao.GetBookingByPaginationAndSorting(pageNumber, pageSize, field);

		if (!page.isEmpty()) {
			ResponseStructure<Page<Booking>> response = new ResponseStructure<>();
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Records fetched with pagination");
			response.setData(page);

			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			throw new NoRecordAvailableException("No record found");
		}
	}
}