package com.irmamsantos.restaurantfood.client.api;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import com.irmamsantos.restaurantfood.client.model.RestauranteDTO;
import com.irmamsantos.restaurantfood.client.model.RestauranteResumoDTO;
import com.irmamsantos.restaurantfood.client.model.input.RestauranteInputDTO;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RestaurantClient {

	private static final String RESOURCE_PATH = "/restaurantes";

	private RestTemplate restTemplate;
	private String url;

	public List<RestauranteResumoDTO> listar () {
		try {
			URI resourceUri = URI.create(url + RESOURCE_PATH);

			RestauranteResumoDTO[] restaurantes = restTemplate
					.getForObject(resourceUri, RestauranteResumoDTO[].class);

			return Arrays.asList(restaurantes);
		} catch (RestClientResponseException e) {
			throw new ClientApiException(e.getMessage(), e);
		}
	}

	public RestauranteDTO adicionar(RestauranteInputDTO restaurante) {
		try {
			URI resourceUri = URI.create(url + RESOURCE_PATH);

			RestauranteDTO restauranteModel = restTemplate
					.postForObject(resourceUri, restaurante, RestauranteDTO.class);

			return restauranteModel;
		} catch (RestClientResponseException e) {
			throw new ClientApiException(e.getMessage(), e);
		}
	}
}
