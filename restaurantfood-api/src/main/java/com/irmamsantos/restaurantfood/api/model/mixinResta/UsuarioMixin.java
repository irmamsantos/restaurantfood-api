package com.irmamsantos.restaurantfood.api.model.mixinResta;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.irmamsantos.restaurantfood.domain.model.Grupo;

public abstract class UsuarioMixin {

	/*
	 * Só vai conter os campos de Restaurante que tenhas anotações do Jackon
	 * por exemplo Restaurante poderia ser uma classe externa não acessivel 
	 */
	
	@JsonIgnore
	private LocalDateTime dataCadastro;
	
	@JsonIgnore
	private List<Grupo> grupos = new ArrayList<>();		

}
