package com.irmamsantos.restaurantfood.domain.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.irmamsantos.restaurantfood.domain.model.Pedido;

@Service
public class FluxoPedidoService {
	
	@Autowired
	private EmissaoPedidoService emissaoPedido;

	@Transactional
	public void confirmar(Long pedidoId) {
		Pedido pedido = emissaoPedido.buscarOuFalhar(pedidoId);
		pedido.confirmar();
	}
	
	@Transactional
	public void cancelar(Long pedidoId) {
	    Pedido pedido = emissaoPedido.buscarOuFalhar(pedidoId);
	    pedido.cancelar();
	}

	@Transactional
	public void entregar(Long pedidoId) {
	    Pedido pedido = emissaoPedido.buscarOuFalhar(pedidoId);
	    pedido.entregar();
	}
}
