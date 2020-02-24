package com.irmamsantos.restaurantfood.domain.exception;

public class CidadeNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = -9049366794948064577L;
	
	public CidadeNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
	
	public CidadeNaoEncontradaException(Long cidadeId) {
		this(String.format("Cidade de código %d não pode removida, pois está em uso", cidadeId));
	}
}
