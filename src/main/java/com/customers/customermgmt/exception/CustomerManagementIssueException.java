package com.customers.customermgmt.exception;

public class CustomerManagementIssueException extends Exception {
	public CustomerManagementIssueException() {
		super("Customer  Not found");
	}

	public CustomerManagementIssueException(String message) {
		super(message);
	}

	public CustomerManagementIssueException(String message, Exception e) {
		super(message, e);
	}

	public CustomerManagementIssueException(String message, Throwable t) {
		super(message, t);
	}

}
