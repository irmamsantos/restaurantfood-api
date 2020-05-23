package com.irmamsantos.restaurantfood.client.model;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class RestauranteResumoDTO {

	private Long id;
	private String nome;
	private BigDecimal precoFrete;
	private CozinhaDTO cozinha;
}
