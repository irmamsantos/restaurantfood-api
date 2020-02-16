package com.irmamsantos.restaurantfood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.irmamsantos.restaurantfood.domain.model.Estado;
import com.irmamsantos.restaurantfood.domain.model.Estado;
import com.irmamsantos.restaurantfood.domain.repository.EstadoRepository;

@Component
public class EstadoRepositoryImpl implements EstadoRepository {

	@PersistenceContext
	private EntityManager manager;

	public List<Estado> todas() {
		TypedQuery<Estado> typedQuery = manager.createQuery("from Estado", Estado.class);
		List<Estado> estados = typedQuery.getResultList();
		return estados;
	}

	public Estado porId(Long id) {
		return manager.find(Estado.class, id);
	}
	
	@Transactional
	public Estado adicionar(Estado estado) {
		return manager.merge(estado);
	}
	
	@Transactional
	public void remover(Long id) {
		Estado estadoApagar = porId(id);
		
		if (estadoApagar == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		manager.remove(estadoApagar);
	}

}
