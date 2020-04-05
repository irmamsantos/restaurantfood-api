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

import com.irmamsantos.restaurantfood.api.assembler.PermissaoDTOAssembler;
import com.irmamsantos.restaurantfood.api.model.dto.output.PermissaoDTO;
import com.irmamsantos.restaurantfood.domain.model.Grupo;
import com.irmamsantos.restaurantfood.domain.service.GrupoService;

@RestController
@RequestMapping(value = "/grupos/{grupoId}/permissoes") 
public class GrupoPermissaoController {
	
	@Autowired
	private GrupoService grupoService; 
	
	@Autowired
	private PermissaoDTOAssembler permissaoDTOAssembler; 
	
	//Nesta implementação faz gestão de permissoes apartir de grupos...
	// GET /grupos/{id}/permissoes
	// PUT /grupos/{id}/permissoes/{id}
	// DELETE /grupos/{id}/permissoes/{id}

	@GetMapping
	public List<PermissaoDTO> listar(@PathVariable Long grupoId) {
		Grupo grupo = grupoService.buscarOuFalhar(grupoId);
		return permissaoDTOAssembler.toCollectionDTO(grupo.getPermissoes());
	}
	
	@DeleteMapping("/{permissaoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void desassociar(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
		grupoService.desassociarPermissao(grupoId, permissaoId);
	}
	
	@PutMapping("/{permissaoId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void associar(@PathVariable Long grupoId, @PathVariable Long permissaoId) {
		grupoService.associarPermissao(grupoId, permissaoId);
	}
	
}
