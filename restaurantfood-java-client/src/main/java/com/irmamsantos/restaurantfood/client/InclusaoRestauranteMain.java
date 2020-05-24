package com.irmamsantos.restaurantfood.client;

import java.math.BigDecimal;

import org.springframework.web.client.RestTemplate;

import com.irmamsantos.restaurantfood.client.api.ClientApiException;
import com.irmamsantos.restaurantfood.client.api.RestaurantClient;
import com.irmamsantos.restaurantfood.client.model.RestauranteDTO;
import com.irmamsantos.restaurantfood.client.model.input.CidadeIdInputDTO;
import com.irmamsantos.restaurantfood.client.model.input.CozinhaIdInputDTO;
import com.irmamsantos.restaurantfood.client.model.input.EnderecoInputDTO;
import com.irmamsantos.restaurantfood.client.model.input.RestauranteInputDTO;

public class InclusaoRestauranteMain {

	public static void main(String[] args) {
		try {
			RestTemplate restTemplate = new RestTemplate();

			RestaurantClient restauranteClient = new RestaurantClient(restTemplate, 
					"http://localhost:8080");

			CozinhaIdInputDTO cozinha = new CozinhaIdInputDTO();
			cozinha.setId(1L);

			CidadeIdInputDTO cidade = new CidadeIdInputDTO();
			cidade.setId(1L);

			EnderecoInputDTO endereco = new EnderecoInputDTO();
			endereco.setCidade(cidade);
			endereco.setCep("38500-111");
			endereco.setLogradouro("Rua Xyz");
			endereco.setNumero("300");
			endereco.setBairro("Centro");

			RestauranteInputDTO restaurante = new RestauranteInputDTO();
			restaurante.setNome("Comida Mineira");
			//para mostrar erros
			//restaurante.setNome("");
			//restaurante.setTaxaFrete(new BigDecimal(9.5));
			restaurante.setTaxaFrete(new BigDecimal(10.0));
			restaurante.setCozinha(cozinha);
			restaurante.setEndereco(endereco);

			RestauranteDTO restauranteModel = restauranteClient.adicionar(restaurante);
			System.out.println(restauranteModel);
			
		} catch (ClientApiException e) {
			if (e.getProblem() != null) {
				System.out.println(e.getProblem().getUserMessage());
				
				e.getProblem().getObjects().stream()
				.forEach(objecto -> System.out.println("- " + objecto.getUserMessage()));
				
			} else {
				System.out.println("Erro desconhecido");
				e.printStackTrace();
			}
		}
	}
}
