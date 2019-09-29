package com.goar.otrs.userservice.exception;

public class DuplicateUserException extends Exception {

	private static final long serialVersionUID = -8890080495441147845L;

	private String message;
	private Object[] args;

	public DuplicateUserException(String name) {
		this.message = String.format("There is already a booking with the name - %s", name);
	}

	public DuplicateUserException(Object[] args) {
		this.args = args;
	}

	public DuplicateUserException(String message, Object[] args) {
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
