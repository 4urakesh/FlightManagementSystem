package org.flightmangementsystem.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.flightmangementsystem.entity.Booking;
import org.flightmangementsystem.entity.BookingStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Integer> {

	List<Booking> findByFlightId(Integer id);

	List<Booking> findByBookingDate(LocalDateTime bookingDate);

	List<Booking> findByStatus(BookingStatus status);
	
	

}
