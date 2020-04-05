package com.irmamsantos.restaurantfood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.irmamsantos.restaurantfood.api.model.dto.input.GrupoInputDTO;
import com.irmamsantos.restaurantfood.domain.model.Grupo;

@Component
public class GrupoInputDTODisassembler {
	
	@Autowired
	private ModelMapper modelMapper;

	public Grupo toDomainObject(GrupoInputDTO grupoInput) {
		
		return modelMapper.map(grupoInput, Grupo.class);
	}
	
	public void copyToDomainObject(GrupoInputDTO grupoInput, Grupo grupo) {
		modelMapper.map(grupoInput, grupo);
	}
	
	public GrupoInputDTO domainObjectToDTO(Grupo grupo) {
		return modelMapper.map(grupo, GrupoInputDTO.class);
	}
}
