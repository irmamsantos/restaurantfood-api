package com.irmamsantos.restaurantfood.di.service;

import com.irmamsantos.restaurantfood.di.modelo.Cliente;

public class ClienteActivadoEvent {
	
	private Cliente cliente;

	public ClienteActivadoEvent(Cliente cliente) {
		super();
		this.cliente = cliente;
	}

	public Cliente getCliente() {
		return cliente;
	}
}
