package com.irmamsantos.restaurantfood.domain.repository;

import java.math.BigDecimal;
import java.util.List;

import com.irmamsantos.restaurantfood.domain.model.Restaurante;

public interface RestauranteRepositoryQueries {
	
	List<Restaurante> find(String nome, 
			BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal);
}