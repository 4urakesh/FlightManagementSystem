package org.flightmangementsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.flightmangementsystem.entity.ModeOfPayment;
import org.flightmangementsystem.entity.Payment;
import org.flightmangementsystem.entity.PaymentStatus;
import org.flightmangementsystem.exception.IdNotFoundException;
import org.flightmangementsystem.exception.NoRecordAvailableException;
import org.flightmangementsystem.exception.PaymentNotConfirmedException;

import java.util.List;
import java.util.Optional;

import org.apache.catalina.connector.Response;
import org.flightmangementsystem.dao.PaymentDao;
import org.flightmangementsystem.dto.ResponseStructure;

@Service
public class PaymentService {
	@Autowired
	private PaymentDao paymentDao;

	public ResponseEntity<ResponseStructure<Payment>> addPayment(Payment payment) {
		ResponseStructure<Payment> response = new ResponseStructure<Payment>();
		response.setStatusCode(HttpStatus.CREATED.value());
		response.setMessage("Payment record is saved");
		response.setData(paymentDao.addPayment(payment));
		return new ResponseEntity<ResponseStructure<Payment>>(response, HttpStatus.CREATED);
	}

	public ResponseEntity<ResponseStructure<List<Payment>>> getAllPayment() {
		ResponseStructure<List<Payment>> response = new ResponseStructure<>();
		List<Payment> payment = paymentDao.getAllPayment();
		if (!payment.isEmpty()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("All Payment");
			response.setData(payment);
			return new ResponseEntity<ResponseStructure<List<Payment>>>(response, HttpStatus.OK);
		} else {
			throw new NoRecordAvailableException("No Payment Available");
		}
	}

	public ResponseEntity<ResponseStructure<Payment>> getPaymentById(Integer id) {
		ResponseStructure<Payment> response = new ResponseStructure<>();
		Optional<Payment> payment = paymentDao.getPaymentById(id);
		if (payment.isPresent()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Payment Details Available with the id " + id);
			response.setData(payment.get());
			return new ResponseEntity<ResponseStructure<Payment>>(response, HttpStatus.OK);
		} else {
			throw new IdNotFoundException("Id Not Exist");
		}
	}

	public ResponseEntity<ResponseStructure<List<Payment>>> getPaymentByStatus(PaymentStatus status) {
		ResponseStructure<List<Payment>> response = new ResponseStructure<>();
		List<Payment> payment = paymentDao.getPaymentByStatus(status);
		if (!payment.isEmpty()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("All Payment with Status " + status);
			response.setData(payment);
			return new ResponseEntity<ResponseStructure<List<Payment>>>(response, HttpStatus.OK);
		} else {
			throw new NoRecordAvailableException("No Payment Available with status " + status);
		}
	}

	public ResponseEntity<ResponseStructure<List<Payment>>> getPaymentByModeOfTransaction(ModeOfPayment mode) {
		ResponseStructure<List<Payment>> response = new ResponseStructure<>();
		List<Payment> payment = paymentDao.getPaymentByModeOfTransaction(mode);
		if (!payment.isEmpty()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("All Payment with  " + mode);
			response.setData(payment);
			return new ResponseEntity<ResponseStructure<List<Payment>>>(response, HttpStatus.OK);
		} else {
			throw new NoRecordAvailableException("No Payment Available with " + mode);
		}
	}

	public ResponseEntity<ResponseStructure<List<Payment>>> getPaymentWhereAmountIsGreaterThenAParticularValue(
			double amount) {
		ResponseStructure<List<Payment>> response = new ResponseStructure<>();
		List<Payment> payment = paymentDao.getPaymentWhereAmountIsGreaterThenAParticularValue(amount);
		if (!payment.isEmpty()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage(
					"Payment info is fatched successfully which are greater then the given value " + amount);
			response.setData(payment);
			return new ResponseEntity<ResponseStructure<List<Payment>>>(response, HttpStatus.OK);
		} else {
			throw new NoRecordAvailableException(
					"There is no payment is record which is greather then the given value amount " + amount);
		}
	}

	public ResponseEntity<ResponseStructure<Payment>> updatePaymentStatus(Integer id, PaymentStatus status) {
		ResponseStructure<Payment> response = new ResponseStructure<Payment>();
		Optional<Payment> payment1 = paymentDao.getPaymentById(id);
		if (payment1.isPresent()) {
			try {
				Payment payment = payment1.get();
				payment.setStatus(status);
				response.setStatusCode(HttpStatus.OK.value());
				response.setMessage("Payment status is updated successfully");
				response.setData(paymentDao.addPayment(payment));
				return new ResponseEntity<ResponseStructure<Payment>>(response, HttpStatus.OK);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			}
		} else {
			throw new IdNotFoundException("Unable to update the status as is the id is not found");
		}
		return null;
	}

	public ResponseEntity<ResponseStructure<Payment>> getPaymentByBookingId(Integer id) {
		ResponseStructure<Payment> response = new ResponseStructure<>();
		Payment payment = paymentDao.getPaymentByBookingId(id);
		response.setStatusCode(HttpStatus.OK.value());
		response.setMessage("Payment with booking id " + id);
		response.setData(payment);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	public ResponseEntity<ResponseStructure<Page<Payment>>> getPaymentByPaginationAndSorting(Integer pageNumber,
			Integer pageSize, String field) {
		ResponseStructure<Page<Payment>> response = new ResponseStructure<>();
		Page<Payment> pl = paymentDao.getPaymentByPaginationAndSorting(pageNumber, pageSize, field);
		if (!pl.isEmpty()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("All Payment info is fatched successfully using pagination and sorting");
			response.setData(pl);
			return new ResponseEntity<ResponseStructure<Page<Payment>>>(response, HttpStatus.OK);
		} else {
			throw new NoRecordAvailableException(
					"There is no record found in the databse due to the paginationa or field of sorting");
		}

	}

	public ResponseEntity<ResponseStructure<Page<Payment>>> getPaymentByPagination(Integer pn, Integer ps,
			String field) {
		ResponseStructure<Page<Payment>> response = new ResponseStructure<Page<Payment>>();
		Page<Payment> page = paymentDao.getPaymentByPaginationAndSorting(pn, ps, field);

		if (!page.isEmpty()) {
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("FOund the page");
			response.setData(page);
			return new ResponseEntity<ResponseStructure<Page<Payment>>>(response, HttpStatus.OK);
		} else {
			throw new PaymentNotConfirmedException("There is no record found ");
		}
	}

	// updateByModeOfTransaction
	public ResponseEntity<ResponseStructure<Payment>> updatePaymentByModeOfTransaction(Payment payment) {
		ResponseStructure<Payment> response = new ResponseStructure<Payment>();
		Optional<Payment> opt = paymentDao.getPaymentById(payment.getId());

		if (opt.isPresent()) {
			paymentDao.updatePaymentByModeOfTransaction(payment);
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Payment detail is Updated");
			response.setData(paymentDao.updatePaymentByModeOfTransaction(payment));
			return new ResponseEntity<ResponseStructure<Payment>>(response, HttpStatus.OK);
		} else {
			throw new PaymentNotConfirmedException("No Payment found to be Updated");
		}
	}
	
	//deletePayment
	
	public ResponseEntity<ResponseStructure<String>> deletePayment(Integer id){
		ResponseStructure<String> response=new ResponseStructure<String>();
		Optional<Payment>opt=paymentDao.getPaymentById(id);
		if(opt.isPresent()) {
			paymentDao.deletePayment(id);
			response.setStatusCode(HttpStatus.OK.value());
			response.setMessage("Payment Successfully deleted");
			response.setMessage(null);
			return new ResponseEntity<ResponseStructure<String>>(response,HttpStatus.OK);
		}
		else {
			throw new PaymentNotConfirmedException("No payment found to be Deleted");
		}
	}
}
