package com.irmamsantos.restaurantfood.core.modelmapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.irmamsantos.restaurantfood.api.model.dto.output.RestauranteDTO;
import com.irmamsantos.restaurantfood.domain.model.Restaurante;

@Configuration
public class ModelMapperConfig {

	@Bean
	public ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		
		//customizar os mapeamentos de campos entre a origem/destino
		modelMapper.createTypeMap(Restaurante.class, RestauranteDTO.class)
			.addMapping(Restaurante::getTaxaFrete, RestauranteDTO::setPrecoFrete);
		
		return modelMapper;
	}
}
