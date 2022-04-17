package com.customers.customermgmt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomermanagementGlobalException {

	@ExceptionHandler(value = NoRecordFoundException.class)
	public ResponseEntity<String> noRecordFoundException(NoRecordFoundException exception) {
		ResponseEntity<String> recordResponce = new ResponseEntity<String>("No Recode Found", HttpStatus.BAD_REQUEST);
		return recordResponce;
	}

	@ExceptionHandler(value = CustomerManagementIssueException.class)
	public ResponseEntity<String> customermanagementIssueException(CustomerManagementIssueException exception) {
		ResponseEntity<String> recordResponce = new ResponseEntity<String>("No record availables",
				HttpStatus.BAD_REQUEST);
		return recordResponce;
	}

	@ExceptionHandler(value = CustomerNotFoundException.class)
	public ResponseEntity<String> customerNotFoundException(CustomerNotFoundException exception) {
		ResponseEntity<String> recordResponce = new ResponseEntity<String>("Internal Error", HttpStatus.BAD_REQUEST);
		return recordResponce;
	}

}
