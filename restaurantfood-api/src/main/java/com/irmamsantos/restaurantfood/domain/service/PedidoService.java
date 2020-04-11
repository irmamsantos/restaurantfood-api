package com.irmamsantos.restaurantfood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.irmamsantos.restaurantfood.domain.exception.PedidoNaoEncontradoException;
import com.irmamsantos.restaurantfood.domain.model.Pedido;
import com.irmamsantos.restaurantfood.domain.repository.PedidoRepository;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository pedidoRepository;

	public Pedido buscarOuFalhar(Long pedidoId) throws PedidoNaoEncontradoException {
		return pedidoRepository.findById(pedidoId).orElseThrow(
				() -> new PedidoNaoEncontradoException(pedidoId));
	}
}
