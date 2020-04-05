package com.irmamsantos.restaurantfood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.irmamsantos.restaurantfood.api.assembler.GrupoDTOAssembler;
import com.irmamsantos.restaurantfood.api.model.dto.output.GrupoDTO;
import com.irmamsantos.restaurantfood.domain.model.Usuario;
import com.irmamsantos.restaurantfood.domain.service.UsuarioService;

@RestController
@RequestMapping(value = "/usuarios/{usuarioId}/grupos") 
public class UsuarioGrupoController {
	
	@Autowired
	private UsuarioService usuarioService; 
	
	@Autowired
	private GrupoDTOAssembler grupoDTOAssembler; 
	
	//Nesta implementação faz gestão de grupos apartir de usuarios...
	// GET /usuarios/{id}/grupos
	// PUT /usuarios/{id}/grupos/{id}
	// DELETE /usuarios/{id}/grupos/{id}

	@GetMapping
	public List<GrupoDTO> listar(@PathVariable Long usuarioId) {
		Usuario usuario = usuarioService.buscarOuFalhar(usuarioId);
		return grupoDTOAssembler.toCollectionDTO(usuario.getGrupos());
	}
	
	@DeleteMapping("/{grupoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void desassociar(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
		usuarioService.desassociarGrupo(usuarioId, grupoId);
	}
	
	@PutMapping("/{grupoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void associar(@PathVariable Long usuarioId, @PathVariable Long grupoId) {
		usuarioService.associarGrupo(usuarioId, grupoId);
	}
}
