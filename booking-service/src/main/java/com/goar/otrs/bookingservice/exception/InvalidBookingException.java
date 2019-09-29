package com.goar.otrs.bookingservice.exception;

public class InvalidBookingException extends Exception {

	private static final long serialVersionUID = -8890080495441147845L;

	private String message;
	private Object[] args;

	public InvalidBookingException(String arg) {
		this.message = String.format("%s is an invalid restaurant.", arg);
	}

	public InvalidBookingException(Object[] args) {
		this.args = args;
	}

	public InvalidBookingException(String message, Object[] args) {
		this.message = message;
		this.args = args;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object[] getArgs() {
		return args;
	}

	public void setArgs(Object[] args) {
		this.args = args;
	}
}
