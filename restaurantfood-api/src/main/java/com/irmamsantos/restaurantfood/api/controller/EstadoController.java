package com.irmamsantos.restaurantfood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.irmamsantos.restaurantfood.api.assembler.EstadoDTOAssembler;
import com.irmamsantos.restaurantfood.api.assembler.EstadoInputDTODisassembler;
import com.irmamsantos.restaurantfood.api.model.dto.input.EstadoInputDTO;
import com.irmamsantos.restaurantfood.api.model.dto.output.EstadoDTO;
import com.irmamsantos.restaurantfood.domain.exception.EntidadeEmUsoException;
import com.irmamsantos.restaurantfood.domain.exception.EntidadeNaoEncontradaException;
import com.irmamsantos.restaurantfood.domain.exception.EstadoNaoEncontradoException;
import com.irmamsantos.restaurantfood.domain.exception.NegocioException;
import com.irmamsantos.restaurantfood.domain.model.Estado;
import com.irmamsantos.restaurantfood.domain.repository.EstadoRepository;
import com.irmamsantos.restaurantfood.domain.service.EstadoService;

@RestController
@RequestMapping("/estados")
public class EstadoController {
	
	@Autowired
	private EstadoRepository estadoRepository; 
	
	@Autowired
	private EstadoService estadoService;
	
	@Autowired
	private EstadoDTOAssembler estadoDTOAssembler;
	
	@Autowired
	private EstadoInputDTODisassembler estadoInputDTODisassembler;	
	
	@GetMapping
	public List<EstadoDTO> listar() {
		return estadoDTOAssembler.toCollectionDTO(estadoRepository.findAll());
	}
	
	@GetMapping("/{estadoId}")
	public EstadoDTO buscar(@PathVariable("estadoId") Long id) 
			throws EntidadeNaoEncontradaException {
		
		return estadoDTOAssembler.toDTO(estadoService.buscarOuFalhar(id));
/*		
		Optional<Estado> estado = estadoRepository.findById(id);
		
		if (estado.isPresent()) {
			//return ResponseEntity.status(HttpStatus.OK).body(estado);
			return ResponseEntity.ok(estado.get());
		}
		//return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		return ResponseEntity.notFound().build();
*/		
	}
	
	@ResponseStatus(code=HttpStatus.CREATED)
	@PostMapping
	public EstadoDTO adicionar(@RequestBody @Valid EstadoInputDTO estadoInput) {
//		try {
			Estado estado = estadoInputDTODisassembler.toDomainObject(estadoInput);
		
			return estadoDTOAssembler.toDTO(estadoService.salvar(estado));
//		} catch (EstadoNaoEncontradoException e) {
//			throw new NegocioException(e.getMessage());
//		}
	}
	
	@PutMapping("/{estadoId}")
	public EstadoDTO actualizar(@PathVariable Long estadoId, 
				@RequestBody @Valid EstadoInputDTO estadoInput) 
			throws EntidadeNaoEncontradaException, NegocioException {
		try {
			Estado estadoActual = estadoService.buscarOuFalhar(estadoId);

//			BeanUtils.copyProperties(estado, estadoActual, "id");
			
			estadoInputDTODisassembler.copyToDomainObject(estadoInput, estadoActual);

			return estadoDTOAssembler.toDTO(estadoService.salvar(estadoActual));
			
		} catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage());
		}
		
/*		
		Optional<Estado> estadoActual = estadoRepository.findById(estadoId);
		if (estadoActual.isPresent()) {
			BeanUtils.copyProperties(estado, estadoActual.get(), "id");
			Estado estadoSalvo = estadoService.salvar(estadoActual.get());
			return ResponseEntity.ok(estadoSalvo);
		}
		return ResponseEntity.notFound().build();
*/		
	} 
	
	@DeleteMapping("/{estadoId}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long estadoId) 
			throws EntidadeNaoEncontradaException, EntidadeEmUsoException {
		
		estadoService.excluir(estadoId);
	}
	
/*	
	@DeleteMapping("/{estadoId}")
	public ResponseEntity<Estado> remover(@PathVariable Long estadoId) {
		
		try {
			estadoService.excluir(estadoId);
			return ResponseEntity.noContent().build();
			
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
			
		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
*/
}
