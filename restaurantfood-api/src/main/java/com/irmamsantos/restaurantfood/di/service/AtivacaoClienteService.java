package com.irmamsantos.restaurantfood.di.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import com.irmamsantos.restaurantfood.di.modelo.Cliente;


@Component
public class AtivacaoClienteService {
	
	@Autowired
	private ApplicationEventPublisher eventPublisher;

	public void ativar(Cliente cliente) {
		cliente.ativar();
		
		//notifica o conatiner que o cliente está activo neste momento
		eventPublisher.publishEvent(new ClienteActivadoEvent(cliente));
	}
}
