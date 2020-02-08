package com.irmamsantos.restaurantfood.di.service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;

import com.irmamsantos.restaurantfood.di.modelo.Cliente;
import com.irmamsantos.restaurantfood.di.notificacao.EnumNivelUrgencia;
import com.irmamsantos.restaurantfood.di.notificacao.Notificador;
import com.irmamsantos.restaurantfood.di.notificacao.TipoDoNotificador;

//porque foi definido o bean 
//@Component
public class AtivacaoClienteService {

	//@Qualifier(value="urgente")
	@TipoDoNotificador(EnumNivelUrgencia.NAO_URGENTE)
	@Autowired
	private Notificador notificador;
	
	//@PostConstruct
	public void init() {
		System.out.println("INIT AtivacaoClienteService");
	}

	//@PreDestroy
	public void destroy() {
		System.out.println("DESTROY AtivacaoClienteService");
	}
	
	public void ativar(Cliente cliente) {
		cliente.ativar();
		notificador.notificar(cliente, "Seu cadastro no sistema está ativo!");
	}
}
