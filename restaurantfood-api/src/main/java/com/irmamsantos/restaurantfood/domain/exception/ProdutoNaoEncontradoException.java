package com.irmamsantos.restaurantfood.domain.exception;

public class ProdutoNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = -9049366794948064577L;
	
	public ProdutoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public ProdutoNaoEncontradoException(Long restauranteId, Long produtoId) {
        this(String.format("Não existe um cadastro de produto com código %d para o restaurante de código %d", 
                produtoId, restauranteId));
    }
}
