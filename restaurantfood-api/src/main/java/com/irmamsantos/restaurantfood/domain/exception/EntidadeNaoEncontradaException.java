package com.irmamsantos.restaurantfood.domain.exception;

public class EntidadeNaoEncontradaException extends /*Runtime*/Exception {

	private static final long serialVersionUID = -9049366794948064577L;
	
	public EntidadeNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
}
