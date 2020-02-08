package com.irmamsantos.restaurantfood.di.notificacao;


import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.irmamsantos.restaurantfood.di.modelo.Cliente;

@Profile("prod")
@TipoDoNotificador(EnumNivelUrgencia.NAO_URGENTE)
@Component
public class NotificadorEmail implements Notificador  {
	
	public NotificadorEmail() {
		System.out.println("Notificador mail REAL");
	}
	
	public void notificar(Cliente cliente, String mensagem) {
		
		System.out.printf("Notificando %s através do e-mail %s: %s\n", 
				cliente.getNome(), cliente.getEmail(), mensagem);
	}
}
