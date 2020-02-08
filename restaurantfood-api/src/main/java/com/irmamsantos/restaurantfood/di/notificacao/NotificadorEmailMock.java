package com.irmamsantos.restaurantfood.di.notificacao;


import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import com.irmamsantos.restaurantfood.di.modelo.Cliente;

@Profile("dev")
@TipoDoNotificador(EnumNivelUrgencia.NAO_URGENTE)
@Component
public class NotificadorEmailMock implements Notificador  {
	
	public NotificadorEmailMock() {
		System.out.println("Notificador mail MOCK");
	}
	
	public void notificar(Cliente cliente, String mensagem) {
		
		System.out.printf("MOCK: simulando a notificando %s através do e-mail %s: %s\n", 
				cliente.getNome(), cliente.getEmail(), mensagem);
	}
}
