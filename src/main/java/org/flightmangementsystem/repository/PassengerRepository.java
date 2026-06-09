package org.flightmangementsystem.repository;

import java.util.List;
import java.util.Optional;

import org.flightmangementsystem.entity.Booking;
import org.flightmangementsystem.entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface PassengerRepository extends JpaRepository<Passenger, Integer> {

	Passenger getPassengerByContactNo(Long contactNo);

	@Query("SELECT p FROM Passenger p WHERE p.booking.flight.id = :flightId")
	List<Passenger> getPassengersByFlightId(int flightId);

	List<Passenger> findByBookingId(Integer id);

}
