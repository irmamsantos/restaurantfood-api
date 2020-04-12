package com.irmamsantos.restaurantfood.api.model.dto.output;

import com.fasterxml.jackson.annotation.JsonView;
import com.irmamsantos.restaurantfood.api.model.view.RestauranteView;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CozinhaDTO /*CozinhaModel*/{

	@JsonView(RestauranteView.Resumo.class)
	private Long id;
	
	@JsonView(RestauranteView.Resumo.class)
	private String nome;
	
	private EstadoDTO estado;
}
