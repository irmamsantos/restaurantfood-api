package com.irmamsantos.restaurantfood.infrastructure.service.report;

public class ReportException extends RuntimeException {

	private static final long serialVersionUID = -590907817741317303L;

	public ReportException(String message, Throwable cause) {
		super(message, cause);
	}

	public ReportException(String message) {
		super(message);
	}
}
