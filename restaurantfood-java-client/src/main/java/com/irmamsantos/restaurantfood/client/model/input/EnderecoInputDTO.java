package com.irmamsantos.restaurantfood.client.model.input;

import lombok.Data;

@Data
public class EnderecoInputDTO {

	private String cep;
	private String logradouro;
	private String numero;
	private String complemento;
	private String bairro;
	private CidadeIdInputDTO cidade;
}
