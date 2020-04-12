package com.irmamsantos.restaurantfood.domain.exception;

public class PedidoNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = -9049366794948064577L;
	
	public PedidoNaoEncontradoException(String codigoPedido) {
		super(String.format("Não existe um cadastro de pedido com código %s", codigoPedido));
	}
}
