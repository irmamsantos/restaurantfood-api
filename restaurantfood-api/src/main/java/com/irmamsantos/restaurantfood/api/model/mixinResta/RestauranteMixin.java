package com.irmamsantos.restaurantfood.api.model.mixinResta;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.irmamsantos.restaurantfood.domain.model.Cozinha;
import com.irmamsantos.restaurantfood.domain.model.Endereco;
import com.irmamsantos.restaurantfood.domain.model.FormaPagamento;
import com.irmamsantos.restaurantfood.domain.model.Produto;

public abstract class RestauranteMixin {

	/*
	 * Só vai conter os campos de Restaurante que tenhas anotações do Jackon
	 * por exemplo Restaurante poderia ser uma classe externa não acessivel 
	 */
	
	//Pela classe restaurante o campo nome da cozinha não é permitido para save/update
	//mas no get/read mostra o campo
	@JsonIgnoreProperties(value="nome", allowGetters=true)
	private Cozinha cozinha;
	
	@JsonIgnore
	private Endereco endereco;
	
	@JsonIgnore
	private OffsetDateTime dataCadastro;

	@JsonIgnore
	private OffsetDateTime dataAtualizacao;
	
	//@JsonIgnore
	private List<FormaPagamento> formasPagamento = new ArrayList<>();
	
	@JsonIgnore
	private List<Produto> produtos = new ArrayList<>();

}
