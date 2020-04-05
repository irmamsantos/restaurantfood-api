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

import com.irmamsantos.restaurantfood.api.assembler.UsuarioDTOAssembler;
import com.irmamsantos.restaurantfood.api.model.dto.output.UsuarioDTO;
import com.irmamsantos.restaurantfood.domain.model.Restaurante;
import com.irmamsantos.restaurantfood.domain.service.RestauranteService;

@RestController
@RequestMapping(value = "/restaurantes/{restauranteId}/responsaveis") 
public class RestauranteUsuarioResponsavelController {
	
	@Autowired
	private RestauranteService restauranteService; 
	
	@Autowired
	private UsuarioDTOAssembler usuarioDTOAssembler; 
	
	//Nesta implementação faz gestão de responsaveis apartir de restaurantes...
	// GET /restaurantes/{id}/responsaveis
	// PUT /restaurantes/{id}/responsaveis/{id}
	// DELETE /restaurantes/{id}/responsaveis/{id}

	@GetMapping
	public List<UsuarioDTO> listar(@PathVariable Long restauranteId) {
		Restaurante restaurante = restauranteService.buscarOuFalhar(restauranteId);
		return usuarioDTOAssembler.toCollectionDTO(restaurante.getResponsaveis());
	}
	
	@DeleteMapping("/{usuarioId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void desassociar(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {
		restauranteService.desassociarResponsavel(restauranteId, usuarioId);
	}
	
	@PutMapping("/{usuarioId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void associar(@PathVariable Long restauranteId, @PathVariable Long usuarioId) {
		restauranteService.associarResponsavel(restauranteId, usuarioId);
	}
}
