package com.irmamsantos.restaurantfood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.irmamsantos.restaurantfood.domain.model.Cozinha;
import com.irmamsantos.restaurantfood.domain.repository.CozinhaRepository;

@Component
public class CozinhaRepositoryImpl implements CozinhaRepository {

	@PersistenceContext
	private EntityManager manager;

	public List<Cozinha> todas() {
		TypedQuery<Cozinha> typedQuery = manager.createQuery("from Cozinha", Cozinha.class);
		List<Cozinha> cozinhas = typedQuery.getResultList();
		return cozinhas;
	}

	public Cozinha porId(Long id) {
		return manager.find(Cozinha.class, id);
	}
	
	@Transactional
	public Cozinha adicionar(Cozinha cozinha) {
		return manager.merge(cozinha);
	}
	
	@Transactional
	public void remover(Long id) {
		Cozinha cozinhaApagar = porId(id);
		
		if (cozinhaApagar == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		manager.remove(cozinhaApagar);
	}

}
