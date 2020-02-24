package com.irmamsantos.restaurantfood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.irmamsantos.restaurantfood.domain.exception.CidadeNaoEncontradaException;
import com.irmamsantos.restaurantfood.domain.exception.EntidadeEmUsoException;
import com.irmamsantos.restaurantfood.domain.exception.EstadoNaoEncontradoException;
import com.irmamsantos.restaurantfood.domain.model.Cidade;
import com.irmamsantos.restaurantfood.domain.model.Estado;
import com.irmamsantos.restaurantfood.domain.repository.CidadeRepository;

@Service
public class CidadeService {
	
	private static final String MSG_CIDADE_EM_USO = "Cidade de código %d não pode removida, pois está em uso";

	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoService estadoService; 

	public Cidade salvar(Cidade cidade) throws EstadoNaoEncontradoException {
		Long estadoId = cidade.getEstado().getId();
		
		Estado estado = estadoService.buscarOuFalhar(estadoId);
		
//		Estado estado = estadoRepository.findById(estadoId)
//				.orElseThrow(() -> new EntidadeNaoEncontradaException(
//						String.format(MSG_ESTADO_NAO_ENCONTRADO, estadoId)));
		
		cidade.setEstado(estado);
		
		return cidadeRepository.save(cidade);
	}
	
	public void excluir(Long cidadeId) throws CidadeNaoEncontradaException, EntidadeEmUsoException {
		try {
			cidadeRepository.deleteById(cidadeId);
			
		} catch (EmptyResultDataAccessException e) {
			//alternativa mas não é uma exception de negócio
//			throw new ResponseStatusException(HttpStatus.NOT_FOUND, 
//					String.format("Não existe cadastro de cidade com código %d", cidadeId));
			
			throw new CidadeNaoEncontradaException(cidadeId);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format(MSG_CIDADE_EM_USO, cidadeId));
		}
	}
	
	public Cidade buscarOuFalhar(Long cidadeId) throws CidadeNaoEncontradaException {
		return cidadeRepository.findById(cidadeId).orElseThrow(
				() -> new CidadeNaoEncontradaException(cidadeId));
	}
}
