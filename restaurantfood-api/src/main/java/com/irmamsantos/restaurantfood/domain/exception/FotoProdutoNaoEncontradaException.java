package com.irmamsantos.restaurantfood.domain.exception;

public class FotoProdutoNaoEncontradaException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = -9049366794948064577L;
	
	public FotoProdutoNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
	
	public FotoProdutoNaoEncontradaException(Long restauranteId, Long produtoId) {
		 this(String.format("Não existe um cadastro de foto do produto com código %d para "
		 		+ "o restaurante de código %d", produtoId, restauranteId));
	}
}
