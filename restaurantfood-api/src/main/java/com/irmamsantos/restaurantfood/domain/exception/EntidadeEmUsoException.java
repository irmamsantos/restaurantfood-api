package com.irmamsantos.restaurantfood.domain.exception;

//@ResponseStatus(value = HttpStatus.CONFLICT)
public class EntidadeEmUsoException extends /*Runtime*/NegocioException {

	private static final long serialVersionUID = -5013637716752574478L;
	
	public EntidadeEmUsoException(String mensagem) {
		super(mensagem);
	}
}
