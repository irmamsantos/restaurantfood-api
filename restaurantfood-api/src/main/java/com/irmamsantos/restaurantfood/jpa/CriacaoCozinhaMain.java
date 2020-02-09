package com.irmamsantos.restaurantfood.jpa;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ApplicationContext;

import com.irmamsantos.restaurantfood.RestaurantfoodApiApplication;
import com.irmamsantos.restaurantfood.domain.model.Cozinha;

public class CriacaoCozinhaMain {
	
	public static void main(String[] args) {
		ApplicationContext applicationContext = new SpringApplicationBuilder(RestaurantfoodApiApplication.class)
				.web(WebApplicationType.NONE)
				.run(args);
		
		CadastroCozinha cadastroCozinha = applicationContext.getBean(CadastroCozinha.class);
		
		Cozinha cozinha1 = new Cozinha();
		cozinha1.setNome("Portuguesa");
		
		Cozinha cozinha2 = new Cozinha();
		cozinha2.setNome("Japonesa");
		
		cadastroCozinha.adiciona(cozinha1);
		cadastroCozinha.adiciona(cozinha2);
	}

}
