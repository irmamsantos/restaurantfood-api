package com.irmamsantos.restaurantfood.api.model.dto.input;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.irmamsantos.restaurantfood.core.validation.Multiplo;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RestauranteInputDTO {
	
	@NotBlank
	private String nome;
	
	@Multiplo(numero=5)
	private BigDecimal taxaFrete;
	
	@Valid
	@NotNull
	private CozinhaIdInputDTO cozinha;
	
	@Valid
	@NotNull
	private EnderecoInputDTO endereco;
}
