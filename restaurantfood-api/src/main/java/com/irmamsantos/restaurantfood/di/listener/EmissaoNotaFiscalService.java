package com.irmamsantos.restaurantfood.di.listener;

import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.irmamsantos.restaurantfood.di.service.ClienteActivadoEvent;

@Component
public class EmissaoNotaFiscalService {
	
	@EventListener
	public void clienteActivadoListener(ClienteActivadoEvent event) {
		System.out.println("Emitindo nota fiscal para o cliente " + event.getCliente());
	}
}
