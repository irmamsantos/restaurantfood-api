package com.irmamsantos.restaurantfood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.irmamsantos.restaurantfood.api.model.dto.input.CozinhaInputDTO;
import com.irmamsantos.restaurantfood.domain.model.Cozinha;

@Component
public class CozinhaInputDTODisassembler {
	
	@Autowired
	private ModelMapper modelMapper;

	public Cozinha toDomainObject(CozinhaInputDTO cozinhaInput) {
		
		return modelMapper.map(cozinhaInput, Cozinha.class);
	}
	
	public void copyToDomainObject(CozinhaInputDTO cozinhaInput, Cozinha cozinha) {
		modelMapper.map(cozinhaInput, cozinha);
	}
	
	public CozinhaInputDTO domainObjectToDTO(Cozinha cozinha) {
		return modelMapper.map(cozinha, CozinhaInputDTO.class);
	}
}
