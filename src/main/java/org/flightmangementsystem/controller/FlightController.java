package org.flightmangementsystem.controller;

import java.util.List;

import org.flightmangementsystem.dto.ResponseStructure;
import org.flightmangementsystem.entity.Flight;
import org.flightmangementsystem.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/flights")
public class FlightController {

	@Autowired
	FlightService flightService;

	@PostMapping
	public ResponseEntity<ResponseStructure<Flight>> addFlight(@RequestBody Flight flight) {
		return flightService.addFlight(flight);
	}

	@GetMapping("/get/all")
	public ResponseEntity<ResponseStructure<List<Flight>>> getAllFlight() {
		return flightService.getAllFlight();
	}

	// flight by id
	@GetMapping("/{id}")
	public ResponseEntity<ResponseStructure<Flight>> getFlightById(@PathVariable Integer id) {
		return flightService.getFlightById(id);
	}

	// flight by source and destination
	@GetMapping("/{source}/{destination}")
	public ResponseEntity<ResponseStructure<List<Flight>>> getFlightBySourceAndDestination(@PathVariable String source,
			@PathVariable String destination) {
		return flightService.getFlightBySourceAndDestination(source, destination);
	}

	@GetMapping("/airline/{airline}")
	public ResponseEntity<ResponseStructure<List<Flight>>> getFlightByAirline(@PathVariable String airline) {
		return flightService.getFlightByAirline(airline);
	}

	@PutMapping
	public ResponseEntity<ResponseStructure<Flight>> updateBook(@RequestBody Flight flight) {
		return flightService.update(flight);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteFlight(@PathVariable Integer id) {
		return flightService.deleteFlight(id);
	}

	@GetMapping("/{pageNumber}/{pageSize}/{field}")
	public ResponseEntity<ResponseStructure<Page<Flight>>> getFlightByPaginationAndSorting(
			@PathVariable Integer pageNumber, @PathVariable Integer pageSize, @PathVariable String field) {
		return flightService.getFlightByPaginationAndSorting(pageNumber, pageSize, field);

	}
}
