package com.irmamsantos.restaurantfood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.irmamsantos.restaurantfood.domain.model.Restaurante;
import com.irmamsantos.restaurantfood.domain.repository.RestauranteRepository;

@RestController
@RequestMapping(value = "/restaurantes") 
public class RestauranteController {
	
	@Autowired
	private RestauranteRepository restauranteRepository; 
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Restaurante> listar() {
		return restauranteRepository.todas();
	}
	
	@GetMapping("/{restauranteId}")
	public ResponseEntity<Restaurante> buscar(@PathVariable("restauranteId") Long id) {
		Restaurante restaurante = restauranteRepository.porId(id);
		
		if (restaurante != null) {
			//return ResponseEntity.status(HttpStatus.OK).body(restaurante);
			return ResponseEntity.ok(restaurante);
		}
		//return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		return ResponseEntity.notFound().build();
	}	
}
