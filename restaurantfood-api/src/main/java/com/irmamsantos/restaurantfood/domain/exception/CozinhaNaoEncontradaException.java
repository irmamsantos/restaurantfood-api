package com.irmamsantos.restaurantfood.domain.exception;

public class CozinhaNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = -9049366794948064577L;
	
	public CozinhaNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
	
	public CozinhaNaoEncontradaException(Long cozinhaId) {
		this(String.format("Cozinha de código %d não pode removida, pois está em uso", cozinhaId));
	}
}
