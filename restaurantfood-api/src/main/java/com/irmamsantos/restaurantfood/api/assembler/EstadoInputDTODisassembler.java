package com.irmamsantos.restaurantfood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.irmamsantos.restaurantfood.api.model.dto.input.CozinhaInputDTO;
import com.irmamsantos.restaurantfood.api.model.dto.input.EstadoInputDTO;
import com.irmamsantos.restaurantfood.domain.model.Cozinha;
import com.irmamsantos.restaurantfood.domain.model.Estado;

@Component
public class EstadoInputDTODisassembler {
	
	@Autowired
	private ModelMapper modelMapper;

	public Estado toDomainObject(EstadoInputDTO estadoInput) {
		
		return modelMapper.map(estadoInput, Estado.class);
	}
	
	public void copyToDomainObject(EstadoInputDTO estadoInput, Estado estado) {
		modelMapper.map(estadoInput, estado);
	}
	
	public EstadoInputDTO domainObjectToDTO(Estado estado) {
		return modelMapper.map(estado, EstadoInputDTO.class);
	}
}
