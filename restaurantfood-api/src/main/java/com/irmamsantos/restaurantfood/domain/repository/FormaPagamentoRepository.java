package com.irmamsantos.restaurantfood.domain.repository;

import java.time.OffsetDateTime;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.irmamsantos.restaurantfood.domain.model.FormaPagamento;

@Repository
public interface FormaPagamentoRepository extends JpaRepository<FormaPagamento, Long>{
	
	@Query("select max(dataAtualizacao) from FormaPagamento")
	OffsetDateTime getDataUltimaAtualizacao();
}
