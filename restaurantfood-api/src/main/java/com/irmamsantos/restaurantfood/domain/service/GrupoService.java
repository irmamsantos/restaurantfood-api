package com.irmamsantos.restaurantfood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.irmamsantos.restaurantfood.domain.exception.EntidadeEmUsoException;
import com.irmamsantos.restaurantfood.domain.exception.GrupoNaoEncontradoException;
import com.irmamsantos.restaurantfood.domain.model.Grupo;
import com.irmamsantos.restaurantfood.domain.model.Permissao;
import com.irmamsantos.restaurantfood.domain.repository.GrupoRepository;

@Service
public class GrupoService {
	
	private static final String MSG_GRUPO_EM_USO = 
			"Grupo de código %d não pode removido, pois está em uso";

	@Autowired
	private GrupoRepository grupoRepository;
	
	@Autowired
	private PermissaoService permissaoService; 

	@Transactional
	public Grupo salvar(Grupo grupo) {
		return grupoRepository.save(grupo);
	}
	
	@Transactional
	public void excluir(Long grupoId) throws GrupoNaoEncontradoException, 
													EntidadeEmUsoException {
		try {
			grupoRepository.deleteById(grupoId);
			grupoRepository.flush();
			
		} catch (EmptyResultDataAccessException e) {
			throw new GrupoNaoEncontradoException(grupoId);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format(MSG_GRUPO_EM_USO, grupoId));
		}
	}
	
	public Grupo buscarOuFalhar(Long grupoId) throws GrupoNaoEncontradoException {
		return grupoRepository.findById(grupoId).orElseThrow(
				() -> new GrupoNaoEncontradoException(grupoId));
	}
	
	@Transactional
	public void desassociarPermissao(Long grupoId, Long permissaoId) {
		Grupo grupoActual = buscarOuFalhar(grupoId);
		Permissao permissao = permissaoService.buscarOuFalhar(permissaoId);
		
		grupoActual.removerPermissao(permissao);	
	}
	
	@Transactional
	public void associarPermissao(Long grupoId, Long permissaoId) {
		Grupo grupoActual = buscarOuFalhar(grupoId);
		Permissao permissao = permissaoService.buscarOuFalhar(permissaoId);
		
		grupoActual.adicionarPermissao(permissao);	
	}	
}
