package com.irmamsantos.restaurantfood.domain.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.irmamsantos.restaurantfood.domain.model.Restaurante;

@Repository
public interface RestauranteRepository extends 
			CustomJpaRepository<Restaurante, Long>, RestauranteRepositoryQueries,
			JpaSpecificationExecutor<Restaurante>{

	List<Restaurante> findByTaxaFreteBetween(BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal);
	
	//@Query("from Restaurante where nome like %:nome% and cozinha.id=:id")
	List<Restaurante> consultarPorNome(String nome, @Param("id") Long cozinhaId);
	
	//List<Restaurante> findByNomeContainingAndCozinhaId(String nome, Long cozinhaId);
	
	Optional<Restaurante> findFirstByNomeContaining(String nome);
	
	List<Restaurante> findTop2ByNomeContaining(String nome);
	
	int countByCozinhaId(Long cozinhaId);
	
	List<Restaurante> find(String nome, 
			BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal);
}
