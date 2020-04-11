package com.irmamsantos.restaurantfood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.irmamsantos.restaurantfood.api.model.dto.input.PedidoInputDTO;
import com.irmamsantos.restaurantfood.domain.model.Pedido;

@Component
public class PedidoInputDTODisassembler {
	
	@Autowired
	private ModelMapper modelMapper;

	public Pedido toDomainObject(PedidoInputDTO pedidoInput) {
		
		return modelMapper.map(pedidoInput, Pedido.class);
	}
	
	public void copyToDomainObject(PedidoInputDTO pedidoInput, Pedido pedido) {
		modelMapper.map(pedidoInput, pedido);
	}
	
	public PedidoInputDTO domainObjectToDTO(Pedido pedido) {
		return modelMapper.map(pedido, PedidoInputDTO.class);
	}
}
