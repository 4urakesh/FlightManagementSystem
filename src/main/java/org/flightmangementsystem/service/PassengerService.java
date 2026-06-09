package org.flightmangementsystem.service;
import java.util.List;
import java.util.Optional;
import org.flightmangementsystem.dao.PassengerDao;
import org.flightmangementsystem.dto.ResponseStructure;
import org.flightmangementsystem.entity.Passenger;
import org.flightmangementsystem.exception.NoRecordAvailableException;
import org.flightmangementsystem.exception.IdNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class PassengerService {

	@Autowired
	private PassengerDao passengerDao;

	
	public ResponseEntity<ResponseStructure<Passenger>> addPassenger(Passenger passenger) {
		ResponseStructure<Passenger> response = new ResponseStructure<Passenger>();
		response.setStatusCode(HttpStatus.CREATED.value());
		response.setMessage("Passenger record is saved");
		response.setData(passengerDao.savePassenger(passenger));
		return new ResponseEntity<ResponseStructure<Passenger>>(response, HttpStatus.CREATED);
	}

	
	public ResponseEntity<ResponseStructure<List<Passenger>>> getAllPassenger() {
		ResponseStructure<List<Passenger>> response = new ResponseStructure<>();
		List<Passenger> pl = passengerDao.getAllPassenger();
		if (!pl.isEmpty()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("All Passenger record is fetched successfully");
			response.setData(pl);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} 
		else {
			throw new NoRecordAvailableException("There is no record available in the database");
		}
	}

	public ResponseEntity<ResponseStructure<Passenger>> getPassengerById(Integer id) {
		ResponseStructure<Passenger> response = new ResponseStructure<>();
		Optional<Passenger> opt = passengerDao.getPassengerById(id);
		if (opt.isPresent()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Passenger record is Fetched successfully with the given id");
			response.setData(opt.get());
			return new ResponseEntity<>(response, HttpStatus.OK);
		} 
		else {
			throw new IdNotFoundException("There is no passenger with the given id");
		}
	}

	public ResponseEntity<ResponseStructure<Passenger>> updatePassenger(Passenger passenger) {
		ResponseStructure<Passenger> response = new ResponseStructure<Passenger>();
		if (passenger.getId() == 0) {
			throw new IdNotFoundException("ID must be provided to update passenger details");
		}

		Optional<Passenger> opt = passengerDao.getPassengerById(passenger.getId());
		if (opt.isEmpty()) {
			throw new NoRecordAvailableException("No passenger found with the given ID");
		}

		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Passenger record is Fetched successfully with the given id");
		response.setData(passengerDao.savePassenger(passenger));
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<Passenger>> getPassengerByContactNumber(Long contactNumber) {

		ResponseStructure<Passenger> response = new ResponseStructure<Passenger>();

		Passenger p = passengerDao.getPassengerByContactNumber(contactNumber);
		if (p != null) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Passenger record is fatched successfully with given contact number");
			response.setData(p);
			return new ResponseEntity<>(response, HttpStatus.OK);

		} 
		else {
			throw new NoRecordAvailableException("There is no record available with the given contact number");
		}
	}
	public ResponseEntity<ResponseStructure<List<Passenger>>> getPassengersByFlightId(Integer id) {
		ResponseStructure<List<Passenger>> response = new ResponseStructure<>();
		 List<Passenger> passenger = passengerDao.getPassengerByFlightId(id);
		 if(!passenger.isEmpty()) {
			 response.setStatusCode(HttpStatus.OK.value());
			 response.setMessage("All Passenger with given flight id "+id);
			 response.setData(passenger);
			return new ResponseEntity<>(response, HttpStatus.OK);
		 }
		 else {
			 throw new NoRecordAvailableException("No Passenger are available");
		 }
	}
	public ResponseEntity<ResponseStructure<Page<Passenger>>> getPassengerByPaginationAndSorting(Integer pageNumber,Integer pageSize,String field){
		ResponseStructure<Page<Passenger>> response = new ResponseStructure<>();
		
		Page<Passenger> pl=passengerDao.getPassengerByPaginationAndSorting(pageNumber, pageSize, field);
		if(!pl.isEmpty()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Passenger record is fatched successfully using pagination and sorting");
			response.setData(pl);
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
		else {
			throw new NoRecordAvailableException("There is no record available with the given pagination or sorting details");
		}
	}
	
	public ResponseEntity<ResponseStructure<List<Passenger>>> findByBookingId(Integer id){
		ResponseStructure<List<Passenger>> response=new ResponseStructure<List<Passenger>>();
		List<Passenger>list=passengerDao.findByBookingId(id);
		
		if(!list.isEmpty()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage(" All passengers fetched with id "+ id);
			response.setData(list);
			
			return new ResponseEntity<ResponseStructure<List<Passenger>>>(response,HttpStatus.OK);
		}
		else {
			throw new NoRecordAvailableException("No passengers details found");
		}
	}
	
	public ResponseEntity<ResponseStructure<String>> deletePassengerById(Integer id){
		ResponseStructure<String> response=new ResponseStructure<String>();
		Optional<Passenger> opt=passengerDao.getPassengerById(id);
		
		if(opt.isPresent()) {
			passengerDao.deletePassengerById(id);
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Passenger deleted");
			response.setData(null);
			
			return new ResponseEntity<ResponseStructure<String>>(response,HttpStatus.OK);
		}
		else {
			throw new NoRecordAvailableException("No record found to be deleted ");
		}
	}
}
