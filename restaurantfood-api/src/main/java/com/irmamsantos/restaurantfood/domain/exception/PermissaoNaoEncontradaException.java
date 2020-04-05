package com.irmamsantos.restaurantfood.domain.exception;

public class PermissaoNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = -9049366794948064577L;
	
	public PermissaoNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
	
	public PermissaoNaoEncontradaException(Long estadoId) {
		this(String.format("Não existe um cadastro de permissão com código %d", estadoId));
	}
}
