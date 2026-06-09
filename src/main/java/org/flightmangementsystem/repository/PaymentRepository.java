package org.flightmangementsystem.repository;

import java.util.List;

import org.flightmangementsystem.entity.ModeOfPayment;
import org.flightmangementsystem.entity.Payment;
import org.flightmangementsystem.entity.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
	 List<Payment> findByStatus(PaymentStatus status);
	 
	 List<Payment> findByModeOfTransaction(ModeOfPayment mode);
	 
	 List<Payment> findByAmountGreaterThan(Double amount);
	
	 Payment findByBookingId(int bookingId);
}
