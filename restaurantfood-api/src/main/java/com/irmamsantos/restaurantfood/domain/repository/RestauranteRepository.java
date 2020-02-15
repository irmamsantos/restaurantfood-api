package com.irmamsantos.restaurantfood.domain.repository;

import java.util.List;

import com.irmamsantos.restaurantfood.domain.model.Restaurante;

public interface RestauranteRepository {
	
	List<Restaurante> todas();
	Restaurante porId(Long id);
	Restaurante adicionar(Restaurante restaurante);
	void remover(Long id);
}
