package com.irmamsantos.restaurantfood.api.model.dto.output;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PedidoDTO /*PedidoModel*/{

	private Long id;
	private BigDecimal subTotal;
	private BigDecimal taxaFrete;
	private BigDecimal valorTotal;
	private OffsetDateTime dataCriacao;
	private OffsetDateTime dataConfirmacao;
	private OffsetDateTime dataCancelamento;
	private OffsetDateTime dataEntrega;
	private FormaPagamentoDTO formaPagamento;
	private List<ItemPedidoDTO> itens = new ArrayList<>();
	private String status;
	private EnderecoDTO enderecoEntrega;
	private UsuarioDTO cliente;
	private RestauranteResumoDTO restaurante;

}
