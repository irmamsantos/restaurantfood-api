package com.irmamsantos.restaurantfood.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.irmamsantos.restaurantfood.api.model.dto.output.PedidoResumoDTO;
import com.irmamsantos.restaurantfood.domain.model.Pedido;

@Component
public class PedidoResumoDTOAssembler {
	@Autowired
	private ModelMapper modelMapper;

	public PedidoResumoDTO toDTO(Pedido pedido) {
		return modelMapper.map(pedido, PedidoResumoDTO.class);
	}
	
	public List<PedidoResumoDTO> toCollectionDTO(List<Pedido> pedidos) {
		return pedidos.stream()
				.map(pedido -> toDTO(pedido))
				.collect(Collectors.toList());
	}
}
