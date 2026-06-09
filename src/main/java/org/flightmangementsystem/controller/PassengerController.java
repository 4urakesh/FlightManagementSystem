package org.flightmangementsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import org.flightmangementsystem.dto.ResponseStructure;
import org.flightmangementsystem.entity.Passenger;
import org.flightmangementsystem.service.PassengerService;
@RequestMapping("/passenger")
@RestController
public class PassengerController {
	@Autowired
	private PassengerService passengerService;
	
	@PostMapping
	public ResponseEntity<ResponseStructure<Passenger>> addPassenger(@RequestBody Passenger passenger){
		return passengerService.addPassenger(passenger);
	}
	@GetMapping
	public ResponseEntity<ResponseStructure<List<Passenger>>> getAllPassenger(){
		return passengerService.getAllPassenger();
	}
	@GetMapping("{id}")
	public ResponseEntity<ResponseStructure<Passenger>> getPassengerById(@PathVariable Integer id){
		return passengerService.getPassengerById(id);
	}
	@GetMapping("/ContactNumber/{contactNumber}")
	public ResponseEntity<ResponseStructure<Passenger>> getPassengerByContactNumber(@PathVariable Long contactNumber){
		return passengerService.getPassengerByContactNumber(contactNumber);
	}
	@PutMapping
	public ResponseEntity<ResponseStructure<Passenger>> updatePassenger(@RequestBody Passenger passenger){
		return passengerService.updatePassenger(passenger);
	}
	@GetMapping("/flight/{flightId}")
	public ResponseEntity<ResponseStructure<List<Passenger>>> getPassengersByFlight(@PathVariable int flightId) {
	   return   passengerService.getPassengersByFlightId(flightId);
	}
	@GetMapping("/PaginationAndSorting/{pageNumber}/{pageSize}/{field}")
	public ResponseEntity<ResponseStructure<Page<Passenger>>>  getPassengerByPaginationAndSorting(@PathVariable Integer pageNumber,@PathVariable Integer pageSize,@PathVariable String field){
		return passengerService.getPassengerByPaginationAndSorting(pageNumber, pageSize, field);
	}
	
	@GetMapping("/getByBookingId/{id}")
	public ResponseEntity<ResponseStructure<List<Passenger>>> findByBookingId(@PathVariable Integer id){
		return passengerService.findByBookingId(id);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ResponseStructure<String>> deletePassengerbyId(@PathVariable Integer id){
		return passengerService.deletePassengerById(id);
	}
}
