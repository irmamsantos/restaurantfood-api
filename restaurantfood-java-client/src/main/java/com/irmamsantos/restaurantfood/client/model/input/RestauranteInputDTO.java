package com.irmamsantos.restaurantfood.client.model.input;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class RestauranteInputDTO {

	private String nome;
	private BigDecimal taxaFrete;
	private CozinhaIdInputDTO cozinha;
	private EnderecoInputDTO endereco;
}
