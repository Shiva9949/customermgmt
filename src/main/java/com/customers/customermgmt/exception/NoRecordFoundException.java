package com.customers.customermgmt.exception;

public class NoRecordFoundException extends RuntimeException {
	public NoRecordFoundException() {
		super("No records are available");
	}
	
	public NoRecordFoundException(String message) {
		super(message);
	}
	
	public NoRecordFoundException(String message, Exception e) {
		super(message,e);
	}
	 
	public NoRecordFoundException(String message, Throwable t) {
		super(message,t);
	}


}
