package com.irmamsantos.restaurantfood.api.model.dto.output;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CidadeDTO /*CidadeModel*/{
	
	private Long id;

	private String nome;
	private EstadoDTO estado;
}
