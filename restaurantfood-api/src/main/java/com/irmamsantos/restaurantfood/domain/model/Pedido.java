package com.irmamsantos.restaurantfood.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Pedido {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@Column(nullable=false)
	private BigDecimal subtotal;
	
	@Column(nullable=false)
	private BigDecimal taxaFrete;
	
	@Column(nullable=false)
	private BigDecimal valorTotal;
	
	@Column(nullable = false, columnDefinition="datetime")
	private OffsetDateTime dataCriacao;
	
	@Column(columnDefinition="datetime")
	private OffsetDateTime dataConfirmacao;
	
	@Column(columnDefinition="datetime")
	private OffsetDateTime dataCancelamento;

	@Column(columnDefinition="datetime")
	private OffsetDateTime dataEntrega;
	
	//12.20 Otimizando a query de pedidos e retornando model resumido na listagem
	//nem sempre que faz um pedido precisa de formaPagamento
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="forma_pagamento_id", nullable = false)
	private FormaPagamento formaPagamento;
	
	@OneToMany(mappedBy = "pedido")
	private List<ItemPedido> itens = new ArrayList<>();
	
	@Enumerated(EnumType.STRING)
	private StatusPedido status;
	
	@Embedded
	private Endereco enderecoEntrega;
	
	@ManyToOne
	@JoinColumn(name="usuario_cliente_id")
	private Usuario cliente;
	
	@ManyToOne
	@JoinColumn(name="restaurante_id", nullable = false)
	private Restaurante restaurante;
	
	public void calcularValorTotal() {
	    this.subtotal = getItens().stream()
	        .map(item -> item.getPrecoTotal())
	        .reduce(BigDecimal.ZERO, BigDecimal::add);
	    
	    this.valorTotal = this.subtotal.add(this.taxaFrete);
	}

	public void definirFrete() {
	    setTaxaFrete(getRestaurante().getTaxaFrete());
	}

	public void atribuirPedidoAosItens() {
	    getItens().forEach(item -> item.setPedido(this));
	}
}
