package com.irmamsantos.restaurantfood.domain.event;

import com.irmamsantos.restaurantfood.domain.model.Pedido;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PedidoCanceladoEvent {

	private Pedido pedido;
}
