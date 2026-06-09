package org.flightmangementsystem.controller;
import java.util.List;

import org.flightmangementsystem.dto.ResponseStructure;
import org.flightmangementsystem.entity.ModeOfPayment;
import org.flightmangementsystem.entity.Payment;
import org.flightmangementsystem.entity.PaymentStatus;
import org.flightmangementsystem.service.PaymentService;
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

@RequestMapping("/payment")
@RestController
public class PaymentController {
	@Autowired
	private PaymentService paymentService;

	@PostMapping
	public ResponseEntity<ResponseStructure<Payment>> addPayment(@RequestBody Payment payment) {
		return paymentService.addPayment(payment);
	}

	@GetMapping("/all")
	public ResponseEntity<ResponseStructure<List<Payment>>> getAllPayment() {
		return paymentService.getAllPayment();
	}

	@GetMapping("{id}")
	public ResponseEntity<ResponseStructure<Payment>> getPaymentById(@PathVariable Integer id) {
		return paymentService.getPaymentById(id);
	}

	@GetMapping("/status/{status}")
	public ResponseEntity<ResponseStructure<List<Payment>>> getPaymentByStatus(@PathVariable PaymentStatus status) {
		return paymentService.getPaymentByStatus(status);
	}

	@GetMapping("/mode/{mode}")
	public ResponseEntity<ResponseStructure<List<Payment>>> getPaymentByModeOfTransaction(
			@PathVariable ModeOfPayment mode) {
		return paymentService.getPaymentByModeOfTransaction(mode);
	}

	@GetMapping("/amount/{amount}")
	public ResponseEntity<ResponseStructure<List<Payment>>> getPaymentByAmount(@PathVariable Double amount) {
		return paymentService.getPaymentWhereAmountIsGreaterThenAParticularValue(amount);
	}

	@PutMapping("/{id}/{status}")
	public ResponseEntity<ResponseStructure<Payment>> updatePaymentStatus(@PathVariable Integer id,
			@PathVariable PaymentStatus status) {
		return paymentService.updatePaymentStatus(id, status);
	}

	@GetMapping("/booking/{id}")
	public ResponseEntity<ResponseStructure<Payment>> getPaymentByBooking(@PathVariable int id) {
		return paymentService.getPaymentByBookingId(id);
	}

	@GetMapping("/PaginationAndSorting/{pageNumber}/{pageSize}/{field}")
	public ResponseEntity<ResponseStructure<Page<Payment>>> getPaymentByPaginationAndSorting(
			@PathVariable Integer pageNumber, @PathVariable Integer pageSize, @PathVariable String field) {
		return paymentService.getPaymentByPaginationAndSorting(pageNumber, pageSize, field);
	}
	
	@PutMapping("/updateMode")
	public ResponseEntity<ResponseStructure<Payment>> updatePaymentByModeOfTransaction(@RequestBody Payment payment){
		return paymentService.updatePaymentByModeOfTransaction(payment);
	}
	
	@DeleteMapping("delete/{id}")
	public ResponseEntity<ResponseStructure<String>> deletePayment(@PathVariable Integer id){
		return paymentService.deletePayment(id);
	}
}