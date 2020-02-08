package com.irmamsantos.restaurantfood.di.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.irmamsantos.restaurantfood.di.modelo.Cliente;
import com.irmamsantos.restaurantfood.di.notificacao.Notificador;

@Component
public class AtivacaoClienteService {

	@Autowired
	private Notificador notificador;
	
//	public AtivacaoClienteService(Notificador notificador) {
//		this.notificador = notificador;
//	}
	
//	public AtivacaoClienteService() {
//		System.out.println("AtivacaoClienteService");
//	}
	
	public void ativar(Cliente cliente) {
		cliente.ativar();
		
		this.notificador.notificar(cliente, "Seu cadastro no sistema está ativo!");
	}

	public Notificador getNotificador() {
		return notificador;
	}

	public void setNotificador(Notificador notificador) {
		this.notificador = notificador;
	}
}
