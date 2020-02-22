package com.irmamsantos.restaurantfood.infrastructure.repository.spec;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.irmamsantos.restaurantfood.domain.model.Restaurante;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class RestauranteComNomeSemelhanteSpec implements Specification<Restaurante>{

	private static final long serialVersionUID = -8904787864638060900L;
	
	private String nome;

//	public RestauranteComNomeSemelhanteSpec(String nome) {
//		this.nome = nome;
//	}

	@Override
	public Predicate toPredicate(Root<Restaurante> root, CriteriaQuery<?> query, 
			CriteriaBuilder builder) {
		return builder.like(root.get("nome"), "%" + nome + "%");
	}

	
}
