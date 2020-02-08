package com.irmamsantos.restaurantfood.di.notificacao;


import org.springframework.stereotype.Component;

import com.irmamsantos.restaurantfood.di.modelo.Cliente;

//instancia o bean pelo Spring para injectar no AtivacaoClienteService
@Component
public class NotificadorEmail  {
	
//	public NotificadorEmail() {
//		System.out.println("NotificadorEmail");
//	}

	public void notificar(Cliente cliente, String mensagem) {
		System.out.printf("Notificando %s através do e-mail %s: %s\n", 
				cliente.getNome(), cliente.getEmail(), mensagem);
	}
	
}
