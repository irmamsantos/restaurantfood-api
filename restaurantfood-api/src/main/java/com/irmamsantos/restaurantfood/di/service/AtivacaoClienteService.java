package com.irmamsantos.restaurantfood.di.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.irmamsantos.restaurantfood.di.modelo.Cliente;
import com.irmamsantos.restaurantfood.di.notificacao.Notificador;

@Component
public class AtivacaoClienteService {

	@Autowired(required = false)
	private List<Notificador> notificadores;
	
//	public AtivacaoClienteService(Notificador notificador) {
//		this.notificador = notificador;
//	}
	
//	public AtivacaoClienteService() {
//		System.out.println("AtivacaoClienteService");
//	}
	
	public void ativar(Cliente cliente) {
		cliente.ativar();
		for (Notificador notificador : notificadores) {
			if (notificador != null) {
				notificador.notificar(cliente, "Seu cadastro no sistema está ativo!");
			} else {
				System.out.println("Não existe notificador mas o cliente foi activo!");
			}
		}
	}

	public List<Notificador> getNotificadores() {
		return notificadores;
	}

	public void setNotificadores(List<Notificador> notificadores) {
		this.notificadores = notificadores;
	}
}
