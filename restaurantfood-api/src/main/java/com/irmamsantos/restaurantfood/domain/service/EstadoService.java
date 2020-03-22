package com.irmamsantos.restaurantfood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.irmamsantos.restaurantfood.domain.exception.EntidadeEmUsoException;
import com.irmamsantos.restaurantfood.domain.exception.EstadoNaoEncontradoException;
import com.irmamsantos.restaurantfood.domain.model.Estado;
import com.irmamsantos.restaurantfood.domain.repository.EstadoRepository;

@Service
public class EstadoService {
	
	private static final String MSG_ESTADO_EM_USO = 
			"Estado de código %d não pode removida, pois está em uso";

	@Autowired
	private EstadoRepository estadoRepository;

	@Transactional
	public Estado salvar(Estado estado) {
		return estadoRepository.save(estado);
	}
	
	@Transactional
	public void excluir(Long estadoId) throws EstadoNaoEncontradoException, EntidadeEmUsoException {
		try {
			estadoRepository.deleteById(estadoId);
			estadoRepository.flush();
			
		} catch (EmptyResultDataAccessException e) {
			throw new EstadoNaoEncontradoException(estadoId);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format(MSG_ESTADO_EM_USO, estadoId));
		}
	}
	
	public Estado buscarOuFalhar(Long estadoId) throws EstadoNaoEncontradoException {
		return estadoRepository.findById(estadoId).orElseThrow(
				() -> new EstadoNaoEncontradoException(estadoId));
	}
}
