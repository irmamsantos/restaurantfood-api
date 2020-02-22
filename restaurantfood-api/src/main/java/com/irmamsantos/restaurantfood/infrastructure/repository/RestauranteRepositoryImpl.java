package com.irmamsantos.restaurantfood.infrastructure.repository;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.springframework.stereotype.Repository;

import com.irmamsantos.restaurantfood.domain.model.Restaurante;
import com.irmamsantos.restaurantfood.domain.repository.RestauranteRepositoryQueries;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryQueries {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		
		CriteriaQuery<Restaurante> criteria = builder.createQuery(Restaurante.class);
		criteria.from(Restaurante.class);
		
		TypedQuery<Restaurante> query = manager.createQuery(criteria);
		return query.getResultList();
	}
	
	
//INFO: Implementation with JPQL	
//	@Override
//	public List<Restaurante> find(String nome, 
//			BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {
//		
//		var jpql = new StringBuilder();
//		jpql.append("from Restaurante where 1=1 ");
//		
//		var parametros = new HashMap<String, Object>();
//		
//		if (StringUtils.hasLength(nome)) {
//			jpql.append("and nome like :nome ");
//			parametros.put("nome", "%" + nome + "%");
//		}
//		
//		if (taxaFreteInicial != null) {
//			jpql.append("and taxaFrete >= :taxaInicial ");
//			parametros.put("taxaInicial", taxaFreteInicial);
//		}
//		
//		if (taxaFreteFinal != null) {
//			jpql.append("and taxaFrete <= :taxaFinal ");
//			parametros.put("taxaFinal", taxaFreteFinal);
//		}		
//		
//		TypedQuery<Restaurante> query = manager.createQuery(jpql.toString(), Restaurante.class);
//		
//		parametros.forEach((chave, valor) -> query.setParameter(chave, valor));
//				
//		return query.getResultList();
//	}
}
