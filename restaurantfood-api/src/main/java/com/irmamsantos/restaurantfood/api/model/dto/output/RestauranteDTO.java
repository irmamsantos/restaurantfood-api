package com.irmamsantos.restaurantfood.api.model.dto.output;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RestauranteDTO /*RestauranteModel*/{

	private Long id;
	
	private String nome;
	private BigDecimal taxaFrete;
	private CozinhaDTO cozinha;
}
