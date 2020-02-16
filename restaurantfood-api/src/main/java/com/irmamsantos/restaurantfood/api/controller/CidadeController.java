package com.irmamsantos.restaurantfood.api.controller;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.irmamsantos.restaurantfood.domain.exception.EntidadeEmUsoException;
import com.irmamsantos.restaurantfood.domain.exception.EntidadeNaoEncontradaException;
import com.irmamsantos.restaurantfood.domain.model.Cidade;
import com.irmamsantos.restaurantfood.domain.model.Cidade;
import com.irmamsantos.restaurantfood.domain.repository.CidadeRepository;
import com.irmamsantos.restaurantfood.domain.service.CidadeService;

@RestController
@RequestMapping(value = "/cidades") 
public class CidadeController {
	
	@Autowired
	private CidadeRepository cidadeRepository; 
	
	@Autowired
	private CidadeService cidadeService; 
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Cidade> listar() {
		return cidadeRepository.todas();
	}
	
	@GetMapping("/{cidadeId}")
	public ResponseEntity<Cidade> buscar(@PathVariable("cidadeId") Long id) {
		Cidade cidade = cidadeRepository.porId(id);
		
		if (cidade != null) {
			//return ResponseEntity.status(HttpStatus.OK).body(cidade);
			return ResponseEntity.ok(cidade);
		}
		//return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	public ResponseEntity<?> adicionar(@RequestBody Cidade cidade) {
		try {
			cidade = cidadeService.salvar(cidade);
			return ResponseEntity.status(HttpStatus.CREATED).body(cidade);
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
	
	@PutMapping("/{cidadeId}")
	public ResponseEntity<?> actualizar(@PathVariable Long cidadeId, @RequestBody Cidade cidade) {
		Cidade cidadeActual = cidadeRepository.porId(cidadeId);
		if (cidadeActual != null) {
			BeanUtils.copyProperties(cidade, cidadeActual, "id");
			try {
				cidadeService.salvar(cidadeActual);
				return ResponseEntity.ok(cidadeActual);
			} catch (EntidadeNaoEncontradaException e) {
				return ResponseEntity.badRequest().body(e.getMessage());
			}
		}
		return ResponseEntity.notFound().build();
	} 
	
	@DeleteMapping("/{cidadeId}")
	public ResponseEntity<Cidade> remover(@PathVariable Long cidadeId) {
		
		try {
			cidadeService.excluir(cidadeId);
			return ResponseEntity.noContent().build();
			
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
			
		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}	
}
