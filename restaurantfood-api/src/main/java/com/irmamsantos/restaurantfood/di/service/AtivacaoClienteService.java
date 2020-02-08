package com.irmamsantos.restaurantfood.di.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.irmamsantos.restaurantfood.di.modelo.Cliente;
import com.irmamsantos.restaurantfood.di.notificacao.Notificador;

@Component
public class AtivacaoClienteService {

	@Autowired(required = false)
	private Notificador notificador;

	public void ativar(Cliente cliente) {
		cliente.ativar();
		notificador.notificar(cliente, "Seu cadastro no sistema está ativo!");
	}
}
