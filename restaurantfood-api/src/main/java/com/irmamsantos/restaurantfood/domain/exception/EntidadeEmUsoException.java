package com.irmamsantos.restaurantfood.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class EntidadeEmUsoException extends /*Runtime*/NegocioException {

	private static final long serialVersionUID = -5013637716752574478L;
	
	public EntidadeEmUsoException(String mensagem) {
		super(mensagem);
	}
}
