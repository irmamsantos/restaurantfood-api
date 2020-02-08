package com.irmamsantos.restaurantfood.di.service;


import com.irmamsantos.restaurantfood.di.modelo.Cliente;
import com.irmamsantos.restaurantfood.di.notificacao.Notificador;

//@Component
//foi comentado por vai ser criado a instancia através anotação @bean
public class AtivacaoClienteService {

	private Notificador notificador;
	
	public AtivacaoClienteService(Notificador notificador) {
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
