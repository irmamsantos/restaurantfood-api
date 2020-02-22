package com.irmamsantos.restaurantfood.infrastructure.repository.spec;

import java.math.BigDecimal;

import org.springframework.data.jpa.domain.Specification;

import com.irmamsantos.restaurantfood.domain.model.Restaurante;

public class RestauranteSpecs {
	
	public static Specification<Restaurante> comFreteGratis() {
		//root, query, builder são os parametros do método toPredicate obrigatório de Specification
		return (root, query, builder) -> builder.equal(root.get("taxaFrete"), BigDecimal.ZERO);
	}

	public static Specification<Restaurante> comNomeSemelhante(String nome) {
		//root, query, builder são os parametros do método toPredicate obrigatório de Specification
		return (root, query, builder) -> builder.like(root.get("nome"), "%" + nome + "%");
	}
}
