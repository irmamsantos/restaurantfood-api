package com.irmamsantos.restaurantfood.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.irmamsantos.restaurantfood.RestaurantfoodApiApplication;
import com.irmamsantos.restaurantfood.domain.model.Cozinha;

public class AlteracaoCozinhaMain {
	
	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(RestaurantfoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		CadastroCozinha cadastroCozinha = applicationContext.getBean(CadastroCozinha.class);
		
		Cozinha cozinha = new Cozinha();
		cozinha.setId(1L);
		cozinha.setNome("Brasileira");
		
		cadastroCozinha.salvar(cozinha);
	}

}
