package com.irmamsantos.restaurantfood.infrastructure.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CompoundSelection;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaBuilder.In;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.irmamsantos.restaurantfood.domain.filter.VendaDiariaFilter;
import com.irmamsantos.restaurantfood.domain.model.Pedido;
import com.irmamsantos.restaurantfood.domain.model.StatusPedido;
import com.irmamsantos.restaurantfood.domain.model.dto.VendaDiaria;
import com.irmamsantos.restaurantfood.domain.service.VendaQueryService;

@Repository
public class VendaQueryServiceImpl implements VendaQueryService {
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro) {
		
		//usar como guideline
		//select date(p.data_criacao) as data_criacao,
		//       count(p.id) as total_vendas,
		//       sum(p.valor_total) as total_faturado
		//from pedido p
		//group by date(p.data_criacao)
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<VendaDiaria> query = builder.createQuery(VendaDiaria.class);
		Root<Pedido> root = query.from(Pedido.class);
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		//adicionar predicates no arrayList
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
		
		//status in ENTREGUE e CONFIRMADO
		predicates.add(root.get("status").in(
				StatusPedido.CONFIRMADO, StatusPedido.ENTREGUE));
		
		//date do sql é uma função da bd
		Expression<Date> functionDateDataCricao = builder.function(
				"date", Date.class, root.get("dataCriacao"));
		
		CompoundSelection<VendaDiaria> selection = builder.construct(VendaDiaria.class, 
				functionDateDataCricao,
				builder.count(root.get("id")),
				builder.sum(root.get("valorTotal")));
		
		query.select(selection);
		query.where(predicates.toArray(new Predicate[0]));
		query.groupBy(functionDateDataCricao);
		
		return manager.createQuery(query).getResultList();
	}
}
