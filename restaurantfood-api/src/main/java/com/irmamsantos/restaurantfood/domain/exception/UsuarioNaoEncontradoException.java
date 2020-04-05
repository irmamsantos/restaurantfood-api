package com.irmamsantos.restaurantfood.domain.exception;

public class UsuarioNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = -9049366794948064577L;
	
	public UsuarioNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public UsuarioNaoEncontradoException(Long estadoId) {
		this(String.format("Não existe um cadastro de usuário com código %d", estadoId));
	}
}
