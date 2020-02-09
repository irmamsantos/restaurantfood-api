package com.irmamsantos.restaurantfood.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.irmamsantos.restaurantfood.domain.model.Cozinha;

@Component
public class CadastroCozinha {
	
	@PersistenceContext
	private EntityManager manager;

	public List<Cozinha> listar() {
		TypedQuery<Cozinha> typedQuery = manager.createQuery("from Cozinha", Cozinha.class);
		List<Cozinha> cozinhas = typedQuery.getResultList();
		return cozinhas;
	}
	
	@Transactional
	public void adiciona(Cozinha cozinha) {
		manager.merge(cozinha);
	}
}
