package com.irmamsantos.restaurantfood.client.api;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.client.RestTemplate;

import com.irmamsantos.restaurantfood.client.model.RestauranteResumoDTO;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RestaurantClient {

	private static final String RESOURCE_PATH = "/restaurantes";
	
	private RestTemplate restTemplate;
	private String url;
	
	public List<RestauranteResumoDTO> listar () {
		URI resourceUri = URI.create(url + RESOURCE_PATH);
		
		RestauranteResumoDTO[] restaurantes = restTemplate
				.getForObject(resourceUri, RestauranteResumoDTO[].class);
		
		return Arrays.asList(restaurantes);
	}
}
