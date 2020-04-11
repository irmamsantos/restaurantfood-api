package com.irmamsantos.restaurantfood.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.irmamsantos.restaurantfood.domain.model.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long>{
	
	//MUITO IMPORTANTE
	//12.20 Otimizando a query de pedidos e retornando model resumido na listagem
	//como cliente e restaurante estavam como ManyToOne -> Eager faziam sempre um select
	//por cada um, o join fetch traz tudo na mesma query select
	
	//cozinha apesar ficar EAGER (disse que sempre que quisesse um restaurante precisava de cozinha)
	//também fez fecth de cozinha para trazer tudo no mesmo select
	@Query("from Pedido p join fetch p.cliente join fetch p.restaurante r join fetch r.cozinha")
	List<Pedido> findAll();
}
