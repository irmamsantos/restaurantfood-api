package com.irmamsantos.restaurantfood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.irmamsantos.restaurantfood.domain.exception.EntidadeEmUsoException;
import com.irmamsantos.restaurantfood.domain.exception.NegocioException;
import com.irmamsantos.restaurantfood.domain.exception.UsuarioNaoEncontradoException;
import com.irmamsantos.restaurantfood.domain.model.Usuario;
import com.irmamsantos.restaurantfood.domain.repository.UsuarioRepository;

@Service
public class UsuarioService {
	
	private static final String MSG_USUARIO_EM_USO = 
			"Usuário de código %d não pode removido, pois está em uso";

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Transactional
	public Usuario salvar(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
	
	@Transactional
	public void excluir(Long usuarioId) throws UsuarioNaoEncontradoException, 
													EntidadeEmUsoException {
		try {
			usuarioRepository.deleteById(usuarioId);
			usuarioRepository.flush();
			
		} catch (EmptyResultDataAccessException e) {
			throw new UsuarioNaoEncontradoException(usuarioId);
		} catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format(MSG_USUARIO_EM_USO, usuarioId));
		}
	}
	
	public Usuario buscarOuFalhar(Long usuarioId) throws UsuarioNaoEncontradoException {
		return usuarioRepository.findById(usuarioId).orElseThrow(
				() -> new UsuarioNaoEncontradoException(usuarioId));
	}
	
	@Transactional
    public void alterarSenha(Long usuarioId, String senhaAtual, String novaSenha) {
        Usuario usuario = buscarOuFalhar(usuarioId);
        
        if (usuario.senhaNaoCoincideCom(senhaAtual)) {
            throw new NegocioException("Senha atual informada não coincide com a senha do usuário.");
        }
        
        usuario.setSenha(novaSenha);
    }
}
