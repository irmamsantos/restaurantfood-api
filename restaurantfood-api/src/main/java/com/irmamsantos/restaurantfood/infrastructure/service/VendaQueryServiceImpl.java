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
	public List<VendaDiaria> consultarVendasDiarias(VendaDiariaFilter filtro, String timeOffset) {
		//ainda pensei em vez de timeOffset ser Locale mas não faz sentido porque o locale teria de ser no
		//cliente dado que está em UTC no server, o cliente é quem chama API REST e do lado do cliente
		//é que pode usar Locale para "calcular" o offset em vez de hardcoded com base num número
		
		//usar como guideline 
		//a data está guardada em UTC (+00:00)
		//e no select usando as datas convertes para "locale brasilia" -03:00
		//select date(convert_tz(p.data_criacao, '+00:00', '-03:00')) as data_criacao,
		//       count(p.id) as total_vendas,
		//       sum(p.valor_total) as total_faturado
		//from pedido p
		//where p.status in ('ENTREGUE', 'CONFIRMADO')
		//group by date(convert_tz(p.data_criacao, '+00:00', '-03:00'))
		
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
		Expression<Date> functionConvertTzDateDataCricao = builder.function(
				"convert_tz", Date.class, 
				root.get("dataCriacao"), builder.literal("+00:00"), builder.literal(timeOffset));
		
		Expression<Date> functionDateDataCricao = builder.function(
				"date", Date.class, functionConvertTzDateDataCricao);
		
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
