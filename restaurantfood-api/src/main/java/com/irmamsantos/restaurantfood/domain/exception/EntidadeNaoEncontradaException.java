package com.irmamsantos.restaurantfood.domain.exception;

//@ResponseStatus(value = HttpStatus.NOT_FOUND) //, reason = "Não encontrado")
public abstract class EntidadeNaoEncontradaException extends NegocioException {

	private static final long serialVersionUID = -9049366794948064577L;
	
	public EntidadeNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
}
