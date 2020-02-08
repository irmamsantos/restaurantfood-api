package com.irmamsantos.restaurantfood.di.notificacao;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.irmamsantos.restaurantfood.di.modelo.Cliente;

@Profile("prod")
@TipoDoNotificador(EnumNivelUrgencia.NAO_URGENTE)
@Component
public class NotificadorEmail implements Notificador  {
	
	@Autowired
	private NotificadorProperties properties;
	
	public NotificadorEmail() {
		System.out.println("Notificador mail REAL");
	}
	
	public void notificar(Cliente cliente, String mensagem) {
		
		System.out.println("Host: " + properties.getHostServidor());
		System.out.println("Porta: " + properties.getPortaServidor());
		
		System.out.printf("Notificando %s através do e-mail %s: %s\n", 
				cliente.getNome(), cliente.getEmail(), mensagem);
	}
}
