package com.irmamsantos.restaurantfood.client;

import org.springframework.web.client.RestTemplate;

import com.irmamsantos.restaurantfood.client.api.ClientApiException;
import com.irmamsantos.restaurantfood.client.api.RestaurantClient;

public class ListagemRestauranteMain {

	public static void main(String[] args) {
		
		try {
		RestTemplate restTemplate = new RestTemplate();
		
		RestaurantClient restauranteClient = new RestaurantClient(restTemplate, 
				"http://localhost:8080"); 
		
		restauranteClient.listar().stream()
			.forEach(restaurante -> System.out.println(restaurante));
		} catch (ClientApiException e) {
			if (e.getProblem() != null) {
				System.out.println(e.getProblem().getUserMessage());
			} else {
				System.out.println("Erro desconhecido");
				e.printStackTrace();
			}
		}
	}
}
