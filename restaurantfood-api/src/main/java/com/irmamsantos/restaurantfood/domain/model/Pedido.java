package com.irmamsantos.restaurantfood.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
	private BigDecimal subTotal;
	
	@Column(nullable=false)
	private BigDecimal taxaFrete;
	
	@Column(nullable=false)
	private BigDecimal valorTotal;
	
	@Column(nullable = false, columnDefinition="datetime")
	private LocalDateTime dataCriacao;
	
	@Column(columnDefinition="datetime")
	private LocalDateTime dataConfirmacao;
	
	@Column(columnDefinition="datetime")
	private LocalDateTime dataCancelamento;

	@Column(columnDefinition="datetime")
	private LocalDateTime dataEntrega;
	
	@ManyToOne
	@JoinColumn(name="forma_pagamento_id", nullable = false)
	private FormaPagamento formaPagamento;
	
	@OneToMany(mappedBy = "pedido")
	private List<ItemPedido> itens = new ArrayList<>();
	
	@Enumerated(EnumType.STRING)
	private StatusPedido status;
	
	@Embedded
	private Endereco enderecoEntrega;
	
	@ManyToOne
	@JoinColumn(name="ususario_cliente_id")
	private Usuario cliente;
	
	@ManyToOne
	@JoinColumn(name="restaurante_id", nullable = false)
	private Restaurante restaurante;
}
