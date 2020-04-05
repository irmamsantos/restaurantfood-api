package com.irmamsantos.restaurantfood.api.model.dto.output;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EnderecoDTO /*EnderecoModel*/{

	private String cep;
	private String logradouro;
	private String numero;
	private String complemento;
	private String bairro;
	private CidadeResumoDTO cidade;
}
