package com.irmamsantos.restaurantfood.api.model.mixinResta;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.irmamsantos.restaurantfood.core.validation.Groups;
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
	private LocalDateTime dataCadastro;

	@JsonIgnore
	private LocalDateTime dataAtualizacao;
	
	//@JsonIgnore
	private List<FormaPagamento> formasPagamento = new ArrayList<>();
	
	@JsonIgnore
	private List<Produto> produtos = new ArrayList<>();

}
