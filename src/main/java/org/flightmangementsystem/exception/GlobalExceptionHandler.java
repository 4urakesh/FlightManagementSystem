package org.flightmangementsystem.exception;

import org.flightmangementsystem.dto.ResponseStructure;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler{
	  @ExceptionHandler(IdNotFoundException.class)
	  public ResponseEntity<ResponseStructure<String>> handleIdNotFoundException(IdNotFoundException ex) {

	        ResponseStructure<String> response = new ResponseStructure<>();
	        response.setStatusCode(HttpStatus.NOT_FOUND.value());
	        response.setMessage("Flight not found");
	        response.setData(ex.getMessage());

	        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
	  }
	  
	  
	  @ExceptionHandler(NoRecordAvailableException.class)
	  public ResponseEntity<ResponseStructure<String>> handleNoRecodAvailableException(NoRecordAvailableException exception) {
		  ResponseStructure<String> response = new ResponseStructure<String>();
		  response.setStatusCode(HttpStatus.NOT_FOUND.value());
		  response.setMessage("failure");
		  response.setData(exception.getMessage());
		  return new ResponseEntity<ResponseStructure<String>>(response,HttpStatus.NOT_FOUND);
	  }
	  
	  
	  @ExceptionHandler(PaymentNotConfirmedException.class)
      public ResponseEntity<ResponseStructure<String>> handlePaymentNotConfirmed(PaymentNotConfirmedException ex) {
          
          ResponseStructure<String> structure = new ResponseStructure<>();
          structure.setStatusCode(HttpStatus.BAD_REQUEST.value());
          structure.setMessage(ex.getMessage());
          structure.setData("Please complete the payment first.");

          return new ResponseEntity<>(structure, HttpStatus.BAD_REQUEST);
      }
	  
	  
	  @ExceptionHandler(flightNotFoundException.class)
      public ResponseEntity<ResponseStructure<String>> handlingFlightNotFound(flightNotFoundException ex) {
          
          ResponseStructure<String> structure = new ResponseStructure<>();
          structure.setStatusCode(HttpStatus.BAD_REQUEST.value());
          structure.setMessage(ex.getMessage());
          structure.setData("Flight details must be passed to book a flight");

          return new ResponseEntity<>(structure, HttpStatus.BAD_REQUEST);
      }
	  
	  
	  @ExceptionHandler(IllegalArgumentException.class)
	  public ResponseEntity<ResponseStructure<String>> handleIllegalArgumentException(IllegalArgumentException e){
		  ResponseStructure<String> structure = new ResponseStructure<>();
          structure.setStatusCode(HttpStatus.BAD_REQUEST.value());
          structure.setMessage(e.getMessage());
          structure.setData("Passanger details must be Passed to Do Booking");

          return new ResponseEntity<>(structure, HttpStatus.BAD_REQUEST);
	  }

}
