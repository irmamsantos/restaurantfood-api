package com.irmamsantos.restaurantfood.api.model.mixinResta;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.irmamsantos.restaurantfood.domain.model.Permissao;

public abstract class GrupoMixin {

	/*
	 * Só vai conter os campos de Grupo que tenhas anotações do Jackon
	 * por exemplo Grupo poderia ser uma classe externa não acessivel 
	 */
	
	@JsonIgnore
	private List<Permissao> permissoes = new ArrayList<>();	


}
