package org.flightmangementsystem.dao;

import java.util.List;
import java.util.Optional;

import org.flightmangementsystem.entity.Flight;
import org.flightmangementsystem.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

@Repository
public class FlightDao {
  
	@Autowired
	FlightRepository flightRepository;
	
	public Flight addFlight(Flight flight) {
		return flightRepository.save(flight);
	}
	
	public List<Flight> getAllFlight(){
		return flightRepository.findAll();
	}
	
	public Optional<Flight> getFlightById(Integer id) {
		return flightRepository.findById(id);
	}
	public List<Flight> getFlightBySourceAndDestination(String source, String destination) {
		return flightRepository.findBySourceAndDestination(source, destination);
	}
	
	public List<Flight> getFlightByAirline(String airline) {
		return flightRepository.findByAirline(airline);
	}
	public Flight updateFlight(Flight flight) {
		return flightRepository.save(flight);
	}
	public void deleteFlight(Flight flight) {
		 flightRepository.delete(flight);
	}
	
	public Page<Flight> getFlightByPaginationAndSorting(Integer pageNumber,Integer pageSize,String field){
		return flightRepository.findAll(PageRequest.of(pageNumber, pageSize , Sort.by(field).ascending()));
	}
}
