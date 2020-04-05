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

import com.irmamsantos.restaurantfood.api.assembler.GrupoDTOAssembler;
import com.irmamsantos.restaurantfood.api.assembler.GrupoInputDTODisassembler;
import com.irmamsantos.restaurantfood.api.model.dto.input.GrupoInputDTO;
import com.irmamsantos.restaurantfood.api.model.dto.output.GrupoDTO;
import com.irmamsantos.restaurantfood.domain.exception.EntidadeEmUsoException;
import com.irmamsantos.restaurantfood.domain.exception.GrupoNaoEncontradoException;
import com.irmamsantos.restaurantfood.domain.exception.NegocioException;
import com.irmamsantos.restaurantfood.domain.model.Grupo;
import com.irmamsantos.restaurantfood.domain.repository.GrupoRepository;
import com.irmamsantos.restaurantfood.domain.service.GrupoService;

@RestController
@RequestMapping(value = "/grupos") //, produces = MediaType.APPLICATION_JSON_VALUE)
public class GrupoController {
	
	@Autowired
	private GrupoRepository grupoRepository; 
	
	@Autowired
	private GrupoService grupoService;
	
	@Autowired
	private GrupoDTOAssembler grupoDTOAssembler;
	
	@Autowired
	private GrupoInputDTODisassembler grupoInputDTODisassembler;
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public List<GrupoDTO> listar() {
		return grupoDTOAssembler.toCollectionDTO(grupoRepository.findAll());
	}
	
	@GetMapping("/{grupoId}")
	public GrupoDTO buscar(@PathVariable("grupoId") Long id) throws GrupoNaoEncontradoException {
		return grupoDTOAssembler.toDTO(grupoService.buscarOuFalhar(id));
	}
	
	@ResponseStatus(code=HttpStatus.CREATED)
	@PostMapping
	public GrupoDTO adicionar(@RequestBody @Valid GrupoInputDTO grupoInput) {
		
		Grupo grupo = grupoInputDTODisassembler.toDomainObject(grupoInput);
		return grupoDTOAssembler.toDTO(grupoService.salvar(grupo));
	}
	
	@PutMapping("/{grupoId}")
	public GrupoDTO actualizar(@PathVariable Long grupoId, 
				@RequestBody @Valid GrupoInputDTO grupoInput)
			throws GrupoNaoEncontradoException, NegocioException {

		Grupo grupoActual = grupoService.buscarOuFalhar(grupoId);

		grupoInputDTODisassembler.copyToDomainObject(grupoInput, grupoActual);

		return grupoDTOAssembler.toDTO(grupoService.salvar(grupoActual));
	}

	@DeleteMapping("/{grupoId}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long grupoId) 
			throws EntidadeEmUsoException, GrupoNaoEncontradoException {
		grupoService.excluir(grupoId);
	}
}
