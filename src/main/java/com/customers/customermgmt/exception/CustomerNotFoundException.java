package com.customers.customermgmt.exception;

public class CustomerNotFoundException extends RuntimeException{
	public CustomerNotFoundException() {
		super("Product Not found");
	}
	
	public CustomerNotFoundException(String message) {
		super(message);
	}
	
	public CustomerNotFoundException(String message, Exception e) {
		super(message,e);
	}
	
	public CustomerNotFoundException(String message, Throwable t) {
		super(message,t);
	}
}
