package com.irmamsantos.restaurantfood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.irmamsantos.restaurantfood.api.model.dto.output.FotoProdutoDTO;
import com.irmamsantos.restaurantfood.domain.model.FotoProduto;

@Component
public class FotoProdutoDTOAssembler {
	
	@Autowired
	private ModelMapper modelMapper;

	public FotoProdutoDTO toDTO(FotoProduto fotoProduto) {
		return modelMapper.map(fotoProduto, FotoProdutoDTO.class);
	}
	
/*	
	public List<FotoProdutoDTO> toCollectionDTO(List<FotoProduto> fotos) {
		return fotos.stream()
				.map(foto -> toDTO(foto))
				.collect(Collectors.toList());
	}
*/	
}
