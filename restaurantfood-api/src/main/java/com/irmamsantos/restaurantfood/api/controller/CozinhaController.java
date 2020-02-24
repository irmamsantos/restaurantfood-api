package com.irmamsantos.restaurantfood.api.controller;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.irmamsantos.restaurantfood.api.model.CozinhasXmlWrapper;
import com.irmamsantos.restaurantfood.domain.exception.EntidadeEmUsoException;
import com.irmamsantos.restaurantfood.domain.exception.EntidadeNaoEncontradaException;
import com.irmamsantos.restaurantfood.domain.model.Cozinha;
import com.irmamsantos.restaurantfood.domain.repository.CozinhaRepository;
import com.irmamsantos.restaurantfood.domain.service.CozinhaService;

@RestController
@RequestMapping(value = "/cozinhas") //, produces = MediaType.APPLICATION_JSON_VALUE)
public class CozinhaController {
	
	@Autowired
	private CozinhaRepository cozinhaRepository; 
	
	@Autowired
	private CozinhaService cozinhaService;
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Cozinha> listar1() {
		return cozinhaRepository.findAll();
	}
	
	@GetMapping(produces=MediaType.APPLICATION_XML_VALUE)
	public CozinhasXmlWrapper listarXML() {
		return new CozinhasXmlWrapper(listar1());
	}
	
	@GetMapping("/{cozinhaId}")
	public ResponseEntity<Cozinha> buscar(@PathVariable("cozinhaId") Long id) {
		Optional<Cozinha> cozinha = cozinhaRepository.findById(id);
		
		if (cozinha.isPresent()) {
			//return ResponseEntity.status(HttpStatus.OK).body(cozinha);
			return ResponseEntity.ok(cozinha.get());
		}
		//return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		return ResponseEntity.notFound().build();
	}
	
	@ResponseStatus(code=HttpStatus.CREATED)
	@PostMapping
	public Cozinha adicionar(@RequestBody Cozinha cozinha) {
		return cozinhaService.salvar(cozinha);
	}
	
	@PutMapping("/{cozinhaId}")
	public ResponseEntity<Cozinha> actualizar(@PathVariable Long cozinhaId, @RequestBody Cozinha cozinha) {
		Optional<Cozinha> cozinhaActual = cozinhaRepository.findById(cozinhaId);
		if (cozinhaActual != null) {
			BeanUtils.copyProperties(cozinha, cozinhaActual.get(), "id");
			Cozinha cozinhaSalva = cozinhaService.salvar(cozinhaActual.get());
			return ResponseEntity.ok(cozinhaSalva);
		}
		return ResponseEntity.notFound().build();
	} 

/*
	@DeleteMapping("/{cozinhaId}")
	public ResponseEntity<?> remover(@PathVariable Long cozinhaId) {
		
		try {
			cozinhaService.excluir(cozinhaId);
			return ResponseEntity.noContent().build();
			
		} catch (EntidadeNaoEncontradaException e) {
			//return ResponseEntity.notFound().build();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Não encontrado");
			
		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}
*/
	
	@DeleteMapping("/{cozinhaId}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long cozinhaId) 
			throws EntidadeEmUsoException {
//		try {
			cozinhaService.excluir(cozinhaId);
//		} catch (EntidadeNaoEncontradaException e) {
//			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
//		}
	}
}
