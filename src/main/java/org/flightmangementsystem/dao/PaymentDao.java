package org.flightmangementsystem.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import org.flightmangementsystem.entity.ModeOfPayment;
import org.flightmangementsystem.entity.Payment;
import org.flightmangementsystem.entity.PaymentStatus;
import org.flightmangementsystem.repository.PaymentRepository;

@Repository
public class PaymentDao {
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	public Payment addPayment(Payment payment) {
		return paymentRepository.save(payment);
	}
	
	public List<Payment> getAllPayment() {
		return paymentRepository.findAll();
	}
	public Optional<Payment> getPaymentById(Integer id) {
		return paymentRepository.findById(id);
	}
	public List<Payment> getPaymentByStatus(PaymentStatus status) {
		return paymentRepository.findByStatus(status);
	}
	public List<Payment> getPaymentByModeOfTransaction(ModeOfPayment mode) {
		return paymentRepository.findByModeOfTransaction(mode);
	}
	public List<Payment> getPaymentWhereAmountIsGreaterThenAParticularValue(Double amount ) {
		return paymentRepository.findByAmountGreaterThan(amount);
	}
	public Payment updatePaymentStatus(Payment payment) {
		return paymentRepository.save(payment);
	}
	public Payment getPaymentByBookingId(Integer id) {
		return paymentRepository.findByBookingId(id);
	}
	public Page<Payment> getPaymentByPaginationAndSorting(Integer pageNumber,Integer pageSize,String field){
		return paymentRepository.findAll(PageRequest.of(pageNumber,pageSize ,Sort.by(field).ascending()));
	}
	
	public Payment updatePaymentByModeOfTransaction(Payment payment) {
		return paymentRepository.save(payment);
	}
	
	public void deletePayment(Integer id) {
		paymentRepository.deleteById(id);
	}
}
