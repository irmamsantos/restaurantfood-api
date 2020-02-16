package com.irmamsantos.restaurantfood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.irmamsantos.restaurantfood.domain.model.Permissao;
import com.irmamsantos.restaurantfood.domain.repository.PermissaoRepository;

@Repository
public class PermissaoRepositoryImpl implements PermissaoRepository {

	@PersistenceContext
	private EntityManager manager;

	public List<Permissao> todas() {
		TypedQuery<Permissao> typedQuery = manager.createQuery("from Permissao", Permissao.class);
		List<Permissao> permissaos = typedQuery.getResultList();
		return permissaos;
	}

	public Permissao porId(Long id) {
		return manager.find(Permissao.class, id);
	}
	
	@Transactional
	public Permissao adicionar(Permissao permissao) {
		return manager.merge(permissao);
	}
	
	@Transactional
	public void remover(Permissao permissao) {
		Permissao permissaoApagar = porId(permissao.getId());
		manager.remove(permissaoApagar);
	}

}
