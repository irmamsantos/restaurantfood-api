package com.irmamsantos.restaurantfood;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.irmamsantos.restaurantfood.di.modelo.Cliente;
import com.irmamsantos.restaurantfood.di.service.AtivacaoClienteService;

@SpringBootApplication
public class RestaurantfoodApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestaurantfoodApiApplication.class, args);
		
//		Cliente joao = new Cliente("Joãoo", "joao@xyz.com", "3499998888");
//		Cliente maria = new Cliente("Maria", "maria@xyz.com", "1177772222");
//		
//		AtivacaoClienteService ativacaoCliente = new AtivacaoClienteService();
//		ativacaoCliente.ativar(joao);
//		ativacaoCliente.ativar(maria);
	}

}
