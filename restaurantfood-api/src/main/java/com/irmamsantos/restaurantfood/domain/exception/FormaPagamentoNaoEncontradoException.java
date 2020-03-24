package com.irmamsantos.restaurantfood.domain.exception;

public class FormaPagamentoNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = -9049366794948064577L;
	
	public FormaPagamentoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public FormaPagamentoNaoEncontradoException(Long estadoId) {
		this(String.format("Não existe um cadastro de forma de pagamento com código %d", estadoId));
	}
}
