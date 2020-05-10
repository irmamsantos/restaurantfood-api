package com.irmamsantos.restaurantfood.infrastructure.service.email;

public class EmailException extends RuntimeException {

	private static final long serialVersionUID = -292553803008714326L;

	public EmailException(String message, Throwable cause) {
		super(message, cause);
	}

	public EmailException(String message) {
		super(message);
	}
}
