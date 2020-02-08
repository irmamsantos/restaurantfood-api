package com.irmamsantos.restaurantfood.di.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.irmamsantos.restaurantfood.di.notificacao.EnumNivelUrgencia;
import com.irmamsantos.restaurantfood.di.notificacao.Notificador;
import com.irmamsantos.restaurantfood.di.notificacao.TipoDoNotificador;
import com.irmamsantos.restaurantfood.di.service.ClienteActivadoEvent;

@Component
public class NotificacaoService {
	
	@TipoDoNotificador(EnumNivelUrgencia.NAO_URGENTE)
	@Autowired
	private Notificador notificador;
	
	@EventListener
	public void clienteActivadoListener(ClienteActivadoEvent event) {
		notificador.notificar(event.getCliente(), "Seu cadastro no sistema está activo!");
	}
}
