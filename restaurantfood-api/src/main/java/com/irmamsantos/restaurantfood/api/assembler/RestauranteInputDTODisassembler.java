package com.irmamsantos.restaurantfood.api.assembler;

import org.springframework.stereotype.Component;

import com.irmamsantos.restaurantfood.api.model.dto.input.CozinhaIdInputDTO;
import com.irmamsantos.restaurantfood.api.model.dto.input.RestauranteInputDTO;
import com.irmamsantos.restaurantfood.domain.model.Cozinha;
import com.irmamsantos.restaurantfood.domain.model.Restaurante;

@Component
public class RestauranteInputDTODisassembler {

	public Restaurante toDomainObject(RestauranteInputDTO restauranteInput) {
		Restaurante restaurante = new Restaurante();
		restaurante.setNome(restauranteInput.getNome());
		restaurante.setTaxaFrete(restauranteInput.getTaxaFrete());
		
		Cozinha cozinha = new Cozinha();
		cozinha.setId(restauranteInput.getCozinha().getId());
		restaurante.setCozinha(cozinha);;
		
		return restaurante;
	}
	
	public RestauranteInputDTO domainObjectToDTO(Restaurante restaurante) {
		RestauranteInputDTO restauranteInput = new RestauranteInputDTO();
		
		restauranteInput.setNome(restaurante.getNome());
		restauranteInput.setTaxaFrete(restaurante.getTaxaFrete());
		CozinhaIdInputDTO cozinhaIdInput = new CozinhaIdInputDTO();
		cozinhaIdInput.setId(restaurante.getCozinha().getId());
		restauranteInput.setCozinha(cozinhaIdInput);
		
		return restauranteInput;
	}
}
