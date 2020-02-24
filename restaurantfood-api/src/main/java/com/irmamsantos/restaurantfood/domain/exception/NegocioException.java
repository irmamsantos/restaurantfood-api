package com.irmamsantos.restaurantfood.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class NegocioException extends /*Runtime*/Exception {

	private static final long serialVersionUID = -5013637716752574478L;
	
	public NegocioException(String mensagem) {
		super(mensagem);
	}
}
