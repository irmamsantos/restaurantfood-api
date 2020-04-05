package com.irmamsantos.restaurantfood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.irmamsantos.restaurantfood.api.assembler.UsuarioDTOAssembler;
import com.irmamsantos.restaurantfood.api.assembler.UsuarioInputDTODisassembler;
import com.irmamsantos.restaurantfood.api.model.dto.input.SenhaInputDTO;
import com.irmamsantos.restaurantfood.api.model.dto.input.UsuarioComSenhaInputDTO;
import com.irmamsantos.restaurantfood.api.model.dto.input.UsuarioInputDTO;
import com.irmamsantos.restaurantfood.api.model.dto.output.UsuarioDTO;
import com.irmamsantos.restaurantfood.domain.exception.EntidadeEmUsoException;
import com.irmamsantos.restaurantfood.domain.exception.NegocioException;
import com.irmamsantos.restaurantfood.domain.exception.UsuarioNaoEncontradoException;
import com.irmamsantos.restaurantfood.domain.model.Usuario;
import com.irmamsantos.restaurantfood.domain.repository.UsuarioRepository;
import com.irmamsantos.restaurantfood.domain.service.UsuarioService;

@RestController
@RequestMapping(value = "/usuarios") //, produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioController {
	
	@Autowired
	private UsuarioRepository usuarioRepository; 
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private UsuarioDTOAssembler usuarioDTOAssembler;
	
	@Autowired
	private UsuarioInputDTODisassembler usuarioInputDTODisassembler;
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public List<UsuarioDTO> listar() {
		return usuarioDTOAssembler.toCollectionDTO(usuarioRepository.findAll());
	}
	
	@GetMapping("/{usuarioId}")
	public UsuarioDTO buscar(@PathVariable("usuarioId") Long id) throws UsuarioNaoEncontradoException {
		return usuarioDTOAssembler.toDTO(usuarioService.buscarOuFalhar(id));
	}
	
	@ResponseStatus(code=HttpStatus.CREATED)
	@PostMapping
	public UsuarioDTO adicionar(@RequestBody @Valid UsuarioComSenhaInputDTO usuarioInput) {
		
		Usuario usuario = usuarioInputDTODisassembler.toDomainObject(usuarioInput);
		return usuarioDTOAssembler.toDTO(usuarioService.salvar(usuario));
	}
	
	@PutMapping("/{usuarioId}")
	public UsuarioDTO actualizar(@PathVariable Long usuarioId, 
				@RequestBody @Valid UsuarioInputDTO usuarioInput)
			throws UsuarioNaoEncontradoException, NegocioException {

		Usuario usuarioActual = usuarioService.buscarOuFalhar(usuarioId);

		usuarioInputDTODisassembler.copyToDomainObject(usuarioInput, usuarioActual);

		return usuarioDTOAssembler.toDTO(usuarioService.salvar(usuarioActual));
	}
	
	@PutMapping("/{usuarioId}/senha")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void alterarSenha(@PathVariable Long usuarioId, @RequestBody @Valid SenhaInputDTO senha) {
		usuarioService.alterarSenha(usuarioId, senha.getSenhaAtual(), senha.getNovaSenha());
    }      
	
	@DeleteMapping("/{usuarioId}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long usuarioId) 
			throws EntidadeEmUsoException, UsuarioNaoEncontradoException {
		usuarioService.excluir(usuarioId);
	}
}
