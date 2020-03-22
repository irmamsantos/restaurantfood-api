package com.irmamsantos.restaurantfood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.irmamsantos.restaurantfood.api.model.dto.input.RestauranteInputDTO;
import com.irmamsantos.restaurantfood.domain.model.Restaurante;

@Component
public class RestauranteInputDTODisassembler {
	
	@Autowired
	private ModelMapper modelMapper;

	public Restaurante toDomainObject(RestauranteInputDTO restauranteInput) {
		
		return modelMapper.map(restauranteInput, Restaurante.class);
	}
	
	public RestauranteInputDTO domainObjectToDTO(Restaurante restaurante) {
		return modelMapper.map(restaurante, RestauranteInputDTO.class);
	}
}
