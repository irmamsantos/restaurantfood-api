package com.irmamsantos.restaurantfood.infrastructure.repository.spec;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.irmamsantos.restaurantfood.domain.filter.PedidoFilter;
import com.irmamsantos.restaurantfood.domain.model.Pedido;

public class PedidoSpecs {
	
	public static Specification<Pedido> usandoFiltro(PedidoFilter filtro) {
		//root, query, builder são os parametros do método toPredicate obrigatório de Specification
		return (root, query, builder) -> {
			
			//13.6 Implementando pesquisas complexas na API
			//MUITO IMPORTANTE FETCH CRITERIA
			//from Pedido p join fetch p.cliente join fetch p.restaurante r join fetch r.cozinha
			
			//error on count
			//select count(generatedAlias0) from com.irmamsantos.restaurantfood.domain.model.Pedido as 
			//generatedAlias0 inner join fetch generatedAlias0.restaurante as generatedAlias1 inner join 
			//fetch generatedAlias1.cozinha as generatedAlias2 inner join fetch generatedAlias0.cliente 
			//as generatedAlias3 where ( generatedAlias0.cliente=1L ) and ( generatedAlias0.restaurante=1L )];
			if (Pedido.class.equals(query.getResultType())) {
				//no count o result type é um número
				root.fetch("restaurante").fetch("cozinha");
				root.fetch("cliente");
			}
			
			List<Predicate> predicates = new ArrayList<Predicate>();
			
			//adicionar predicates no arrayList
			if (filtro.getClienteId() != null) {
				predicates.add(builder.equal(root.get("cliente"), filtro.getClienteId()));
			}
			
			if (filtro.getRestauranteId() != null) {
				predicates.add(builder.equal(root.get("restaurante"), filtro.getRestauranteId()));
			}
			
			if (filtro.getDataCriacaoInicio() != null) {
				predicates.add(builder.greaterThanOrEqualTo(root.get("dataCriacao")
						, filtro.getDataCriacaoInicio()));
			}
			
			if (filtro.getDataCriacaoFim() != null) {
				predicates.add(builder.lessThanOrEqualTo(root.get("dataCriacao")
						, filtro.getDataCriacaoFim()));
			}			
			
			return builder.and(predicates.toArray(new Predicate[0]));
		};
	}
}
