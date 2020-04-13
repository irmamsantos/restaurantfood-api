package com.irmamsantos.restaurantfood.infrastructure.service;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CompoundSelection;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.irmamsantos.restaurantfood.domain.filter.VendaDiariaFilter;
import com.irmamsantos.restaurantfood.domain.model.Pedido;
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
		
		//date do sql é uma função da bd
		Expression<Date> functionDateDataCricao = builder.function(
				"date", Date.class, root.get("dataCriacao"));
		
		CompoundSelection<VendaDiaria> selection = builder.construct(VendaDiaria.class, 
				functionDateDataCricao,
				builder.count(root.get("id")),
				builder.sum(root.get("valorTotal")));
		
		query.select(selection);
		query.groupBy(functionDateDataCricao);
		
		return manager.createQuery(query).getResultList();
	}
}
