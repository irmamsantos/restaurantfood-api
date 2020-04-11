package com.irmamsantos.restaurantfood.api.model.dto.output;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PedidoResumoDTO /*PedidoResumoModel*/{

	private Long id;
	private BigDecimal subTotal;
	private BigDecimal taxaFrete;
	private BigDecimal valorTotal;
	private String status;	
	private OffsetDateTime dataCriacao;
	private UsuarioDTO cliente;
	private RestauranteResumoDTO restaurante;

}
