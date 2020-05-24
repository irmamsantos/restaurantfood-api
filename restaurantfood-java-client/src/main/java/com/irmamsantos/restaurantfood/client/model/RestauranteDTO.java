package com.irmamsantos.restaurantfood.client.model;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class RestauranteDTO {

	private Long id;
	private String nome;
	private BigDecimal precoFrete;
	private CozinhaDTO cozinha;
	private Boolean activo;
	private Boolean aberto;
	private EnderecoDTO endereco;
}
