package com.irmamsantos.restaurantfood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.irmamsantos.restaurantfood.api.model.dto.input.CidadeInputDTO;
import com.irmamsantos.restaurantfood.domain.model.Cidade;
import com.irmamsantos.restaurantfood.domain.model.Estado;

@Component
public class CidadeInputDTODisassembler {
	@Autowired
	private ModelMapper modelMapper;

	public Cidade toDomainObject(CidadeInputDTO cidadeInput) {
		
		return modelMapper.map(cidadeInput, Cidade.class);
	}
	
	//aqui não instanciar objectos, vai recebe-los instancias já criadas como parametro
	//e copia os valores da origem para o destino
	public void copyToDomainObject(CidadeInputDTO cidadeInput, Cidade cidade) {
		
		//Para evitar org.hibernate.HibernateException: identifier of an instance of 
		//com.irmamsantos.restaurantfood.domain.model.Estado was altered from 1 to 2
		cidade.setEstado(new Estado());
		//porque o objecto estado pode ser uma instância gerida pelo JPA que tenha id já atribuido
		//ao estado e não deixa alterar a pk, por faz set de uma nova instancia de estado para não ser
		//gerido pelo JPA e assim não alterar o id do objecto mas update para outro estado com outro id

		modelMapper.map(cidadeInput, cidade);
	}
}
