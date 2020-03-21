package com.irmamsantos.restaurantfood.api.model.mixin;

import java.time.OffsetDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

public abstract class PedidoMixin {

	/*
	 * Só vai conter os campos de Pedido que tenhas anotações do Jackon
	 */
	
	@JsonIgnore
	private OffsetDateTime dataCriacao;
	
	@JsonIgnore
	private OffsetDateTime dataConfirmacao;
	
	@JsonIgnore
	private OffsetDateTime dataCancelamento;

	@JsonIgnore
	private OffsetDateTime dataEntrega;
}
