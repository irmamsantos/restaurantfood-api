package com.irmamsantos.restaurantfood.domain.repository;

import java.util.List;

import com.irmamsantos.restaurantfood.domain.model.Cozinha;

public interface CozinhaRepository {
	
	List<Cozinha> todas();
	Cozinha porId(Long id);
	Cozinha adicionar(Cozinha cozinha);
	void remover(Long id);
}
