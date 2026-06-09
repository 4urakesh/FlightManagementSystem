package org.flightmangementsystem.repository;

import java.util.List;

import org.flightmangementsystem.entity.Flight;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FlightRepository extends JpaRepository<Flight, Integer> {

	public List<Flight> findBySourceAndDestination(String source, String destination);
	
	public List<Flight> findByAirline(String airline);
}
