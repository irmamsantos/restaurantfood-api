package com.irmamsantos.restaurantfood.infrastructure.service.storage;

public class StorageException extends RuntimeException {

	private static final long serialVersionUID = -292553803008714326L;

	public StorageException(String message, Throwable cause) {
		super(message, cause);
	}

	public StorageException(String message) {
		super(message);
	}
}
