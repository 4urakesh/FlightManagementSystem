package org.flightmangementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.flightmangementsystem.entity.Payment;
import org.flightmangementsystem.entity.Passenger;
import java.time.LocalDateTime;
import java.util.List;
import org.flightmangementsystem.dto.ResponseStructure;
import org.flightmangementsystem.entity.Booking;
import org.flightmangementsystem.entity.BookingStatus;
import org.flightmangementsystem.service.BookingService;

@RestController
@RequestMapping("/booking")
public class BookingController {
	
	@Autowired
	private BookingService bookingService;

	@PostMapping
	public ResponseEntity<ResponseStructure<Booking>> addBooking(@RequestBody Booking booking) {
		return bookingService.addBooking(booking);
	}
   @GetMapping("/all")
   public ResponseEntity<ResponseStructure<List<Booking>>> getAllBooking() {
	   return bookingService.getAllBooking();
   }
   @GetMapping("/{id}")
   public ResponseEntity<ResponseStructure<Booking>> getBookingById(@PathVariable Integer id) {
	   return bookingService.getBookingById(id);
   }
   @GetMapping("/flight/{id}")
   public ResponseEntity<ResponseStructure<List<Booking>>> getBookingByFlightId(@PathVariable Integer id) {
	   return bookingService.getBookingByFlightId(id);
   }
   @GetMapping("/Date/{bookingDate}")
	public ResponseEntity<ResponseStructure<List<Booking>>> getBookingByDate(@PathVariable LocalDateTime bookingDate) {
		return bookingService.getBookingByDate(bookingDate);
	}

	@GetMapping("/Status/{status}")
	public ResponseEntity<ResponseStructure<List<Booking>>> getBookingByStatus(@PathVariable BookingStatus status) {
		return bookingService.getBookingByStatus(status);
	}

	@GetMapping("/Payment/{id}")
	public ResponseEntity<ResponseStructure<Payment>> getPaymentByBookingId(@PathVariable Integer id) {
		return bookingService.getPaymentByBookingId(id);
	}

	@GetMapping("/Passenger/{id}")
	public ResponseEntity<ResponseStructure<List<Passenger>>> getPassengerById(@PathVariable Integer id) {
		return bookingService.getPassengerById(id);
	}

	@PutMapping("/{bookingId}/{status}")
	public ResponseEntity<ResponseStructure<Booking>> updateStatusByBooking(@PathVariable Integer bookingId,@PathVariable BookingStatus status ) {
		return bookingService.updateStatusByBooking(bookingId, status);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteBooking(@PathVariable Integer id){
		return bookingService.deleteBooking(id);
	}
	@GetMapping("/PaginationAndSorting/{pageNumber}/{pageSize}/{field}")
	public ResponseEntity<ResponseStructure<Page<Booking>>> GetBookingByPaginationAndSorting(@PathVariable int pageNumber,@PathVariable int pageSize,@PathVariable String field){
		return bookingService.GetBookingByPaginationAndSorting(pageNumber,pageSize,field);
	}
}





