package com.irmamsantos.restaurantfood.api.model.mixinResta;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class PedidoMixin {

	/*
	 * Só vai conter os campos de Pedido que tenhas anotações do Jackon
	 */
	
	@JsonIgnore
	private LocalDateTime dataCriacao;
	
	@JsonIgnore
	private LocalDateTime dataConfirmacao;
	
	@JsonIgnore
	private LocalDateTime dataCancelamento;

	@JsonIgnore
	private LocalDateTime dataEntrega;
}
