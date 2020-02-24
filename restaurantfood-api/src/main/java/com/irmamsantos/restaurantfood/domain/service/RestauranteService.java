package com.irmamsantos.restaurantfood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.irmamsantos.restaurantfood.domain.exception.CozinhaNaoEncontradaException;
import com.irmamsantos.restaurantfood.domain.exception.EntidadeEmUsoException;
import com.irmamsantos.restaurantfood.domain.exception.RestauranteNaoEncontradoException;
import com.irmamsantos.restaurantfood.domain.model.Cozinha;
import com.irmamsantos.restaurantfood.domain.model.Restaurante;
import com.irmamsantos.restaurantfood.domain.repository.RestauranteRepository;

@Service
public class RestauranteService {
	
	private static final String MSG_RESTAURANTE_EM_USO = 
			"Restaurante de código %d não pode removida, pois está em uso";

	private static final String MSG_RESTAURANTE_NAO_ENCONTRADO = 
			"Não existe cadastro de restaurante com código %d";

	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CozinhaService cozinhaService; 

	public Restaurante salvar(Restaurante restaurante) throws CozinhaNaoEncontradaException {
		Long cozinhaId = restaurante.getCozinha().getId();
		
		Cozinha cozinha = cozinhaService.buscarOuFalhar(cozinhaId);
		
//		Cozinha cozinha = cozinhaRepository.findById(cozinhaId)
//				.orElseThrow(() -> new EntidadeNaoEncontradaException(
//						String.format(MSG_COZINHA_NAO_ENCONTRADA, cozinhaId)));
		
		restaurante.setCozinha(cozinha);
		
		return restauranteRepository.save(restaurante);
	}
	
	public void excluir(Long restauranteId) throws RestauranteNaoEncontradoException, EntidadeEmUsoException {
		try {
			restauranteRepository.deleteById(restauranteId);
			
		} catch (EmptyResultDataAccessException e) {
			throw new RestauranteNaoEncontradoException(restauranteId);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format(MSG_RESTAURANTE_EM_USO, restauranteId));
		}
	}
	
	public Restaurante buscarOuFalhar(Long restauranteId) throws RestauranteNaoEncontradoException {
		return restauranteRepository.findById(restauranteId).orElseThrow(
				() -> new RestauranteNaoEncontradoException(restauranteId));
	}
}
