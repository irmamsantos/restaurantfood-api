package com.irmamsantos.restaurantfood.api.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.irmamsantos.restaurantfood.api.exceptionhandler.Problema;
import com.irmamsantos.restaurantfood.domain.exception.CidadeNaoEncontradaException;
import com.irmamsantos.restaurantfood.domain.exception.EntidadeEmUsoException;
import com.irmamsantos.restaurantfood.domain.exception.EntidadeNaoEncontradaException;
import com.irmamsantos.restaurantfood.domain.exception.EstadoNaoEncontradoException;
import com.irmamsantos.restaurantfood.domain.exception.NegocioException;
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
		return cidadeRepository.findAll();
	}
	
	@GetMapping("/{cidadeId}")
	public Cidade buscar(@PathVariable("cidadeId") Long id) 
			throws CidadeNaoEncontradaException {
		
		return cidadeService.buscarOuFalhar(id);
	}
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Cidade adicionar(@RequestBody Cidade cidade) throws NegocioException {
		try {
			return cidadeService.salvar(cidade);
		} catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}
	
	@PutMapping("/{cidadeId}")
	public Cidade actualizar(@PathVariable Long cidadeId, @RequestBody Cidade cidade) 
			throws CidadeNaoEncontradaException, NegocioException {
		
		Cidade cidadeActual = cidadeService.buscarOuFalhar(cidadeId);
		
		BeanUtils.copyProperties(cidade, cidadeActual, "id");
		
		try {
			return cidadeService.salvar(cidadeActual);
		} catch (EstadoNaoEncontradoException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	} 
	
	@DeleteMapping("/{cidadeId}")
	@ResponseStatus(value=HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long cidadeId) 
			throws CidadeNaoEncontradaException, EntidadeEmUsoException {
		cidadeService.excluir(cidadeId);
	}
	
/*	
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
*/

}
