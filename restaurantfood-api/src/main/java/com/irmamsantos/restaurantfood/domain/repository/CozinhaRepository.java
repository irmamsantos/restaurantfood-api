package com.irmamsantos.restaurantfood.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.irmamsantos.restaurantfood.domain.model.Cozinha;

@Repository
public interface CozinhaRepository extends CustomJpaRepository<Cozinha, Long>{
	
	//13.8 Implementando paginação e ordenação em recursos de coleção da API
	//Se quisesse acrescentar paginação a qualquer método do repository
	//Page<Cozinha> findTodasByNomeContaining(String nome, Pageable pageable);
	List<Cozinha> findTodasByNomeContaining(String nome);
	
	Optional<Cozinha> findByNome(String nome);
	
	Boolean existsByNome(String nome);
}
