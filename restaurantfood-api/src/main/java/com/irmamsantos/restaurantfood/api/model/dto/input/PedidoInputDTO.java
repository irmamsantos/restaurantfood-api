package com.irmamsantos.restaurantfood.api.model.dto.input;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PedidoInputDTO {
	
	@Valid
	@NotNull
	private RestauranteIdInputDTO restaurante;

	@Valid
	@NotNull
	private FormaPagamentoIdInputDTO formaPagamento;
	
	@Valid
	@NotNull
	private EnderecoInputDTO enderecoEntrega;
	
	@Valid
	@Size(min=1)
	@NotNull
	private List<ItemPedidoInputDTO> itens;
	
	
}
