package com.irmamsantos.restaurantfood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.irmamsantos.restaurantfood.domain.model.Cidade;
import com.irmamsantos.restaurantfood.domain.model.Cidade;
import com.irmamsantos.restaurantfood.domain.repository.CidadeRepository;

@Component
public class CidadeRepositoryImpl implements CidadeRepository {

	@PersistenceContext
	private EntityManager manager;

	public List<Cidade> todas() {
		TypedQuery<Cidade> typedQuery = manager.createQuery("from Cidade", Cidade.class);
		List<Cidade> cidades = typedQuery.getResultList();
		return cidades;
	}

	public Cidade porId(Long id) {
		return manager.find(Cidade.class, id);
	}
	
	@Transactional
	public Cidade adicionar(Cidade cidade) {
		return manager.merge(cidade);
	}
	
	@Transactional
	public void remover(Long id) {
		Cidade cidadeApagar = porId(id);
		
		if (cidadeApagar == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		manager.remove(cidadeApagar);
	}

}
