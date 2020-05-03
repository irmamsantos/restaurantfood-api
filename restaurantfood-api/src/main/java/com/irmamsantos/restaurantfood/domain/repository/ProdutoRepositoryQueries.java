package com.irmamsantos.restaurantfood.domain.repository;

import com.irmamsantos.restaurantfood.domain.model.FotoProduto;

public interface ProdutoRepositoryQueries {
	
	/*
	 * Como Produto é tratado como um dominio, FotoProduto faz parte dele.
	 * Não vai criar um repositório para FotoProduto, mas não pode usar o save porque espera um Produto
	 * então têm de implementar o save, e para criar uma implementação própria cria uma interface
	 * que és esta que é usado em ProdutoRepository
	 */
	FotoProduto save(FotoProduto foto);
}
