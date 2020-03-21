package com.irmamsantos.restaurantfood.api.model.mixinResta;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.irmamsantos.restaurantfood.domain.model.Restaurante;

public abstract class CozinhaMixin {

	/*
	 * Só vai conter os campos de Cozinha que tenhas anotações do Jackon
	 * por exemplo Cozinha poderia ser uma classe externa não acessivel 
	 */
	
	@JsonIgnore
	@OneToMany(mappedBy="cozinha")
	private List<Restaurante> restaurantes = new ArrayList<>();

}
