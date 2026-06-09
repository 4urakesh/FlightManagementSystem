package org.flightmangementsystem.dao;

import java.util.List;
import java.util.Optional;

import org.flightmangementsystem.entity.Booking;
import org.flightmangementsystem.entity.Passenger;
import org.flightmangementsystem.repository.PassengerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

@Repository
public class PassengerDao {

    @Autowired
    private PassengerRepository passengerRepository;

    public Passenger savePassenger(Passenger passenger) {
        return passengerRepository.save(passenger);
    }
    public List<Passenger> getAllPassenger(){
		return passengerRepository.findAll();
	}
	public Optional<Passenger> getPassengerById(Integer id) {
		return passengerRepository.findById(id);
	}
	public Passenger updatePassenger(Passenger passenger) {
		return passengerRepository.save(passenger);
	}
	public Passenger getPassengerByContactNumber(Long contactNo) {
		return passengerRepository.getPassengerByContactNo(contactNo);
	}
	public List<Passenger> getPassengerByFlightId(Integer id){
		return passengerRepository.getPassengersByFlightId(id);
	}
	public Page<Passenger> getPassengerByPaginationAndSorting(Integer pageNumber,Integer pageSize,String field){
		return passengerRepository.findAll(PageRequest.of(pageNumber,pageSize,Sort.by(field).ascending()));
	}
	
	public List<Passenger> findByBookingId(Integer id){
		return passengerRepository.findByBookingId(id);
	}
	
	public void deletePassengerById(Integer id) {
		 passengerRepository.deleteById(id);
	}
}

