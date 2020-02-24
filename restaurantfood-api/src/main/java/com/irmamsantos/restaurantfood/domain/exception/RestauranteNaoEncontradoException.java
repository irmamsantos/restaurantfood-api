package com.irmamsantos.restaurantfood.domain.exception;

public class RestauranteNaoEncontradoException extends EntidadeNaoEncontradaException {

	private static final long serialVersionUID = -9049366794948064577L;
	
	public RestauranteNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	
	public RestauranteNaoEncontradoException(Long restauranteId) {
		this(String.format("Restaurante de código %d não pode removida, pois está em uso", restauranteId));
	}
}
