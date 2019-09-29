package com.goar.otrs.bookingservice.exception;

public class BookingNotFoundException extends Exception {

	private static final long serialVersionUID = -8890080495441147845L;

	private String message;
	private Object[] args;

	public BookingNotFoundException(String message){
		this.message = String.format("Booking %s is not found.", message);
	}

	public BookingNotFoundException(Object[] args) {
		this.args = args;
	}

	public BookingNotFoundException(String message, Object[] args){
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
