package com.irmamsantos.restaurantfood.domain.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

//@ResponseStatus(value = HttpStatus.NOT_FOUND) //, reason = "Não encontrado")
public class EntidadeNaoEncontradaException extends ResponseStatusException {

	private static final long serialVersionUID = -9049366794948064577L;
	
	private EntidadeNaoEncontradaException(HttpStatus status, String mensagem) {
		super(status, mensagem);
	}

	public EntidadeNaoEncontradaException(String mensagem) {
		super(HttpStatus.NOT_FOUND, mensagem);
	}
}
