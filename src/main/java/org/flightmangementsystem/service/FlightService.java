package org.flightmangementsystem.service;

import java.util.List;
import java.util.Optional;

import org.flightmangementsystem.dao.FlightDao;
import org.flightmangementsystem.dto.ResponseStructure;
import org.flightmangementsystem.entity.Flight;
import org.flightmangementsystem.exception.IdNotFoundException;
import org.flightmangementsystem.exception.NoRecordAvailableException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class FlightService {

	@Autowired
	FlightDao flightDao;

	// Add Flight
	public ResponseEntity<ResponseStructure<Flight>> addFlight(Flight flight) {
		ResponseStructure<Flight> response = new ResponseStructure<>();
		response.setStatusCode(HttpStatus.CREATED.value());
		response.setMessage("New Flight Added Successfully");
		response.setData(flightDao.addFlight(flight));
		return new ResponseEntity<ResponseStructure<Flight>>(response, HttpStatus.CREATED);
	}

	// Get all the flight
	public ResponseEntity<ResponseStructure<List<Flight>>> getAllFlight() {
		ResponseStructure<List<Flight>> response = new ResponseStructure<>();
		List<Flight> flight = flightDao.getAllFlight();
		if (!flight.isEmpty()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("All the Available flight");
			response.setData(flight);
			return new ResponseEntity<ResponseStructure<List<Flight>>>(response, HttpStatus.OK);
		} else {
			throw new NoRecordAvailableException("No Flight Available");
		}
	}

	// Get Flight by id
	public ResponseEntity<ResponseStructure<Flight>> getFlightById(Integer id) {
		Optional<Flight> opt = flightDao.getFlightById(id);
		ResponseStructure<Flight> response = new ResponseStructure<>();
		if (opt.isPresent()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Flight is available with id - " + id);
			response.setData(opt.get());
			return new ResponseEntity<ResponseStructure<Flight>>(response, HttpStatus.OK);
		} else {
			throw new IdNotFoundException("Flight not found with id " + id);
		}
	}

	// Get flight by source and destination
	public ResponseEntity<ResponseStructure<List<Flight>>> getFlightBySourceAndDestination(String source,
			String destination) {
		ResponseStructure<List<Flight>> response = new ResponseStructure<>();
		List<Flight> flight = flightDao.getFlightBySourceAndDestination(source, destination);
		if (!flight.isEmpty()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Flight is available with source - " + source + " destination - " + destination);
			response.setData(flight);
			return new ResponseEntity<ResponseStructure<List<Flight>>>(response, HttpStatus.OK);
		} else {
			throw new IdNotFoundException(
					"Flight not found with source " + source + " and the destination " + destination);
		}
	}

	public ResponseEntity<ResponseStructure<List<Flight>>> getFlightByAirline(String airline) {
		ResponseStructure<List<Flight>> response = new ResponseStructure<>();
		List<Flight> flight = flightDao.getFlightByAirline(airline);
		if (!flight.isEmpty()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Flight is available with airline -" + airline);
			response.setData(flight);
			return new ResponseEntity<ResponseStructure<List<Flight>>>(response, HttpStatus.OK);
		} else {
			throw new IdNotFoundException("Flight not found with airline " + airline);
		}
	}

	// update flight
	public ResponseEntity<ResponseStructure<Flight>> update(Flight flight) {
		ResponseStructure<Flight> response = new ResponseStructure<>();

		if (flight.getId() == 0) {
			response.setStatusCode(HttpStatus.CREATED.value());
			response.setMessage("Flight record saved");
			response.setData(flightDao.updateFlight(flight));

			return new ResponseEntity<>(response, HttpStatus.CREATED);
		}

		Optional<Flight> opt = flightDao.getFlightById(flight.getId());

		if (opt.isPresent()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Flight record updated");
			response.setData(flightDao.updateFlight(flight));

			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			throw new IdNotFoundException("Flight ID " + flight.getId() + " does not exist");
		}
	}

	public ResponseEntity<ResponseStructure<String>> deleteFlight(Integer id) {

		Optional<Flight> flight = flightDao.getFlightById(id);
		ResponseStructure<String> response = new ResponseStructure<>();

		if (flight.isPresent()) {
			flightDao.deleteFlight(flight.get());

			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Flight deleted successfully");
			response.setData("Flight with id " + id + " has been removed");

			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			throw new IdNotFoundException("Flight with id " + id + " not found");
		}
	}
	
	public ResponseEntity<ResponseStructure<Page<Flight>>> getFlightByPaginationAndSorting(Integer pageNumber,Integer pageSize,String field){
		ResponseStructure<Page<Flight>> response=new ResponseStructure<Page<Flight>>();
		
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Data successfully Fetched By Pagination and sorting");
		response.setData(flightDao.getFlightByPaginationAndSorting(pageNumber, pageSize, field));
		return new ResponseEntity<ResponseStructure<Page<Flight>>>(response,HttpStatus.OK);
	}


}
