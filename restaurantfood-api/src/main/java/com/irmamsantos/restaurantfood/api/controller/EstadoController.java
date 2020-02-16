package com.irmamsantos.restaurantfood.api.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.irmamsantos.restaurantfood.domain.exception.EntidadeEmUsoException;
import com.irmamsantos.restaurantfood.domain.exception.EntidadeNaoEncontradaException;
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
	
	@GetMapping
	public List<Estado> listar() {
		return estadoRepository.todas();
	}
	
	@GetMapping("/{estadoId}")
	public ResponseEntity<Estado> buscar(@PathVariable("estadoId") Long id) {
		Estado estado = estadoRepository.porId(id);
		
		if (estado != null) {
			//return ResponseEntity.status(HttpStatus.OK).body(estado);
			return ResponseEntity.ok(estado);
		}
		//return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		return ResponseEntity.notFound().build();
	}
	
	@ResponseStatus(code=HttpStatus.CREATED)
	@PostMapping
	public Estado adicionar(@RequestBody Estado estado) {
		return estadoService.salvar(estado);
	}
	
	@PutMapping("/{estadoId}")
	public ResponseEntity<Estado> actualizar(@PathVariable Long estadoId, @RequestBody Estado estado) {
		Estado estadoActual = estadoRepository.porId(estadoId);
		if (estadoActual != null) {
			BeanUtils.copyProperties(estado, estadoActual, "id");
			estadoService.salvar(estadoActual);
			return ResponseEntity.ok(estadoActual);
		}
		return ResponseEntity.notFound().build();
	} 
	
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

}