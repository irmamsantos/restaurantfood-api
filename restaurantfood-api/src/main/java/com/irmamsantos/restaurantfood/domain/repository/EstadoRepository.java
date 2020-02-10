package com.irmamsantos.restaurantfood.domain.repository;

import java.util.List;

import com.irmamsantos.restaurantfood.domain.model.Estado;

public interface EstadoRepository {
	
	List<Estado> todas();
	Estado porId(Long id);
	Estado adicionar(Estado estado);
	void remover(Estado estado);
}
