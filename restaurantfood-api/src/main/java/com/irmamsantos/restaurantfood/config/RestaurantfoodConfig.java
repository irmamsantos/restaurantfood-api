package com.irmamsantos.restaurantfood.config;

import org.springframework.context.annotation.Bean;

import com.irmamsantos.restaurantfood.di.notificacao.Notificador;
import com.irmamsantos.restaurantfood.di.notificacao.NotificadorEmail;
import com.irmamsantos.restaurantfood.di.service.AtivacaoClienteService;

//passa para outra classe de configuração
//@Configuration
public class RestaurantfoodConfig {
	
	@Bean
	public Notificador notificadorEmail() {
		NotificadorEmail notificadorEmail = new NotificadorEmail("stmp.restaurantfoodmail.com.pt");
		notificadorEmail.setUpperCase(false);
		return notificadorEmail;
	}
	
	@Bean
	public AtivacaoClienteService ativacaoClienteService() {
		return new AtivacaoClienteService(notificadorEmail());	
	}
}
