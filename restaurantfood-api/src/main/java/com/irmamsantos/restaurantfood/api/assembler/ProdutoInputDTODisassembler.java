package com.irmamsantos.restaurantfood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.irmamsantos.restaurantfood.api.model.dto.input.ProdutoInputDTO;
import com.irmamsantos.restaurantfood.domain.model.Produto;

@Component
public class ProdutoInputDTODisassembler {
	
	@Autowired
	private ModelMapper modelMapper;

	public Produto toDomainObject(ProdutoInputDTO produtoInput) {
		
		return modelMapper.map(produtoInput, Produto.class);
	}
	
	public void copyToDomainObject(ProdutoInputDTO produtoInput, 
			Produto produto) {
		modelMapper.map(produtoInput, produto);
	}
	
	public ProdutoInputDTO domainObjectToDTO(Produto produto) {
		return modelMapper.map(produto, ProdutoInputDTO.class);
	}
}
