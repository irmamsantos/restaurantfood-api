package com.irmamsantos.restaurantfood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.irmamsantos.restaurantfood.domain.model.Restaurante;
import com.irmamsantos.restaurantfood.domain.model.Restaurante;
import com.irmamsantos.restaurantfood.domain.repository.RestauranteRepository;

@Component
public class RestauranteRepositoryImpl implements RestauranteRepository {

	@PersistenceContext
	private EntityManager manager;

	public List<Restaurante> todas() {
		TypedQuery<Restaurante> typedQuery = manager.createQuery("from Restaurante", Restaurante.class);
		List<Restaurante> restaurantes = typedQuery.getResultList();
		return restaurantes;
	}

	public Restaurante porId(Long id) {
		return manager.find(Restaurante.class, id);
	}
	
	@Transactional
	public Restaurante adicionar(Restaurante restaurante) {
		return manager.merge(restaurante);
	}
	
	@Transactional
	public void remover(Long id) {
		Restaurante restauranteApagar = porId(id);
		
		if (restauranteApagar == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		manager.remove(restauranteApagar);
	}

}
