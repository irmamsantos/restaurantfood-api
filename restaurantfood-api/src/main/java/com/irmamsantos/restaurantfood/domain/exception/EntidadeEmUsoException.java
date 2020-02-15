package com.irmamsantos.restaurantfood.domain.exception;

public class EntidadeEmUsoException extends /*Runtime*/Exception {

	private static final long serialVersionUID = -5013637716752574478L;
	
	public EntidadeEmUsoException(String mensagem) {
		super(mensagem);
	}
}
