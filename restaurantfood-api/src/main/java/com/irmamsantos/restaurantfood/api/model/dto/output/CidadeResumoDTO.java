package com.irmamsantos.restaurantfood.api.model.dto.output;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CidadeResumoDTO /*CidadeResumoModel*/{
	
	private Long id;

	private String nome;
	//se o nome do campo foi estado, é tratado como o objecto Estado e faz um toString
	//para ficar estado, têm de fazer config no ModelMapperConf
	private String estado;
}
