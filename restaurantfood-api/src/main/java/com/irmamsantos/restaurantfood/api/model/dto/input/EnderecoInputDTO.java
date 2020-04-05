package com.irmamsantos.restaurantfood.api.model.dto.input;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EnderecoInputDTO {
	
	@NotBlank
	private String cep;
	
	@NotBlank
	private String logradouro;
	
	@NotBlank
	private String numero;
	
	private String complemento;
	
	@NotBlank
	private String bairro;
	
	//validar os campos da cidade
	@Valid
	//obriga a indicar o objecto cidade
	@NotNull
	//Só precisa do id da cidade
	private CozinhaIdInputDTO cidade;
}
