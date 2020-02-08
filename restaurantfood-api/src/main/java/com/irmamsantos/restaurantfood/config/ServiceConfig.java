package com.irmamsantos.restaurantfood.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.irmamsantos.restaurantfood.di.notificacao.Notificador;
import com.irmamsantos.restaurantfood.di.service.AtivacaoClienteService;


@Configuration
public class ServiceConfig {
	
	@Bean
	public AtivacaoClienteService ativacaoClienteService(Notificador notificador) {
		return new AtivacaoClienteService(notificador);	
	}
}
