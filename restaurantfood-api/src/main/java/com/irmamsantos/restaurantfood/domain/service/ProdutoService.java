package com.irmamsantos.restaurantfood.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.irmamsantos.restaurantfood.domain.exception.ProdutoNaoEncontradoException;
import com.irmamsantos.restaurantfood.domain.model.Produto;
import com.irmamsantos.restaurantfood.domain.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Transactional
    public Produto salvar(Produto produto) {
        return produtoRepository.save(produto);
    }

	public Produto buscarOuFalhar(Long restauranteId, Long produtoId) {
        return produtoRepository.findById(restauranteId, produtoId)
            .orElseThrow(() -> new ProdutoNaoEncontradoException(restauranteId, produtoId));
    }   
}
