package com.irmamsantos.restaurantfood.di.service;


import org.springframework.stereotype.Component;

import com.irmamsantos.restaurantfood.di.modelo.Cliente;
import com.irmamsantos.restaurantfood.di.notificacao.NotificadorEmail;

@Component
public class AtivacaoClienteService {

	private NotificadorEmail notificador;
	
	public AtivacaoClienteService(NotificadorEmail notificador) {
		this.notificador = notificador;
	}
	
//	public AtivacaoClienteService() {
//		System.out.println("AtivacaoClienteService");
//	}
	
	public void ativar(Cliente cliente) {
		cliente.ativar();
		
		this.notificador.notificar(cliente, "Seu cadastro no sistema está ativo!");
	}
	
}
