package com.irmamsantos.restaurantfood.api.model.dto.output;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RestauranteDTO /*RestauranteModel*/{

	private Long id;
	
	private String nome;
	private BigDecimal precoFrete;
	private CozinhaDTO cozinha;
	private Boolean ativo;
	private Boolean aberto;
	private EnderecoDTO endereco;
}
