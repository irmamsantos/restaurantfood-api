package com.irmamsantos.restaurantfood.di.notificacao;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.irmamsantos.restaurantfood.di.modelo.Cliente;

//instancia o bean pelo Spring para injectar no AtivacaoClienteService
@Qualifier(value="urgente")
@Component
public class NotificadorSMS implements Notificador  {
	

	/* (non-Javadoc)
	 * @see com.irmamsantos.restaurantfood.di.notificacao.Notificador#notificar(com.irmamsantos.restaurantfood.di.modelo.Cliente, java.lang.String)
	 */
	public void notificar(Cliente cliente, String mensagem) {
		
		System.out.printf("Notificando %s através do SMS %s: %s\n", 
				cliente.getNome(), cliente.getTelefone(), mensagem);
	}
}
