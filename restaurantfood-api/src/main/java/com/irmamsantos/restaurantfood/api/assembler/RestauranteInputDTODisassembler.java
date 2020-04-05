package com.irmamsantos.restaurantfood.api.assembler;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.irmamsantos.restaurantfood.api.model.dto.input.RestauranteInputDTO;
import com.irmamsantos.restaurantfood.domain.model.Cidade;
import com.irmamsantos.restaurantfood.domain.model.Cozinha;
import com.irmamsantos.restaurantfood.domain.model.Restaurante;

@Component
public class RestauranteInputDTODisassembler {
	
	@Autowired
	private ModelMapper modelMapper;

	public Restaurante toDomainObject(RestauranteInputDTO restauranteInput) {
		
		return modelMapper.map(restauranteInput, Restaurante.class);
	}
	
	//aqui não instanciar objectos, vai recebe-los instancias já criadas como parametro
	//e copia os valores da origem para o destino
	public void copyToDomainObject(RestauranteInputDTO restauranteInput, Restaurante restaurante) {
		
		//Para evitar org.hibernate.HibernateException: identifier of an instance of 
		//com.irmamsantos.restaurantfood.domain.model.Cozinha was altered from 1 to 2
		restaurante.setCozinha(new Cozinha());
		//porque o objecto restaurante pode ser uma instância gerida pelo JPA que tenha id já atribuido
		//a cozinha e não deixa alterar a pk, por faz set de uma nova instancia de cozinha para não ser
		//gerido pelo JPA e assim não alterar o id do objecto mas update para outra cozinha com outro id
		
		//mesma situação anterior
		if (restaurante.getEndereco() != null) {
			//identifier of an instance of com.irmamsantos.restaurantfood.domain.model.Cidade was altered from 1 to 2
			restaurante.getEndereco().setCidade(new Cidade());
		}

		modelMapper.map(restauranteInput, restaurante);
	}
	
	public RestauranteInputDTO domainObjectToDTO(Restaurante restaurante) {
		return modelMapper.map(restaurante, RestauranteInputDTO.class);
	}
}
