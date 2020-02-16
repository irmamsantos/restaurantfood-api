package com.irmamsantos.restaurantfood.infrastructure.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.irmamsantos.restaurantfood.domain.model.FormaPagamento;
import com.irmamsantos.restaurantfood.domain.repository.FormaPagamentoRepository;

@Repository
public class FormaPagamentoRepositoryImpl implements FormaPagamentoRepository {

	@PersistenceContext
	private EntityManager manager;

	public List<FormaPagamento> todas() {
		TypedQuery<FormaPagamento> typedQuery = manager.createQuery("from FormaPagamento", FormaPagamento.class);
		List<FormaPagamento> formaPagamentos = typedQuery.getResultList();
		return formaPagamentos;
	}

	public FormaPagamento porId(Long id) {
		return manager.find(FormaPagamento.class, id);
	}
	
	@Transactional
	public FormaPagamento adicionar(FormaPagamento formaPagamento) {
		return manager.merge(formaPagamento);
	}
	
	@Transactional
	public void remover(FormaPagamento formaPagamento) {
		FormaPagamento formaPagamentoApagar = porId(formaPagamento.getId());
		manager.remove(formaPagamentoApagar);
	}

}
