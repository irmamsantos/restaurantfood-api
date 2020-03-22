package com.irmamsantos.restaurantfood.api.assembler;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.irmamsantos.restaurantfood.api.model.dto.output.EstadoDTO;
import com.irmamsantos.restaurantfood.domain.model.Estado;

@Component
public class EstadoDTOAssembler {
	
	@Autowired
	private ModelMapper modelMapper;

	public EstadoDTO toDTO(Estado estado) {
		return modelMapper.map(estado, EstadoDTO.class);
	}
	
	public List<EstadoDTO> toCollectionDTO(List<Estado> estados) {
		return estados.stream()
				.map(estado -> toDTO(estado))
				.collect(Collectors.toList());
	}
}
