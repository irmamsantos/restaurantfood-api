package com.irmamsantos.restaurantfood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.irmamsantos.restaurantfood.domain.exception.EntidadeEmUsoException;
import com.irmamsantos.restaurantfood.domain.exception.PermissaoNaoEncontradaException;
import com.irmamsantos.restaurantfood.domain.model.Permissao;
import com.irmamsantos.restaurantfood.domain.repository.PermissaoRepository;

@Service
public class PermissaoService {
	
	private static final String MSG_PERMISSAO_EM_USO = 
			"Permissao de código %d não pode removida, pois está em uso";

	@Autowired
	private PermissaoRepository permissaoRepository;

	@Transactional
	public Permissao salvar(Permissao permissao) {
		return permissaoRepository.save(permissao);
	}
	
	@Transactional
	public void excluir(Long permissaoId) throws PermissaoNaoEncontradaException, 
													EntidadeEmUsoException {
		try {
			permissaoRepository.deleteById(permissaoId);
			permissaoRepository.flush();
			
		} catch (EmptyResultDataAccessException e) {
			throw new PermissaoNaoEncontradaException(permissaoId);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format(MSG_PERMISSAO_EM_USO, permissaoId));
		}
	}
	
	public Permissao buscarOuFalhar(Long permissaoId) throws PermissaoNaoEncontradaException {
		return permissaoRepository.findById(permissaoId).orElseThrow(
				() -> new PermissaoNaoEncontradaException(permissaoId));
	}
}
