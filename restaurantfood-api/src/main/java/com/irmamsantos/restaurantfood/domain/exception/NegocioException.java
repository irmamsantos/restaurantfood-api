package com.irmamsantos.restaurantfood.domain.exception;

//@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class NegocioException extends RuntimeException {

	private static final long serialVersionUID = -5013637716752574478L;
	
	public NegocioException(String mensagem) {
		super(mensagem);
	}
	
	public NegocioException(String mensagem, Throwable causa) {
		super(mensagem, causa);
	}
}
