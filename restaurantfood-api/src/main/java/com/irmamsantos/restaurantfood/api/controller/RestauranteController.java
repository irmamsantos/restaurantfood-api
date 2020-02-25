package com.irmamsantos.restaurantfood.api.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.irmamsantos.restaurantfood.domain.exception.CozinhaNaoEncontradaException;
import com.irmamsantos.restaurantfood.domain.exception.EntidadeEmUsoException;
import com.irmamsantos.restaurantfood.domain.exception.EntidadeNaoEncontradaException;
import com.irmamsantos.restaurantfood.domain.exception.NegocioException;
import com.irmamsantos.restaurantfood.domain.exception.RestauranteNaoEncontradoException;
import com.irmamsantos.restaurantfood.domain.model.Restaurante;
import com.irmamsantos.restaurantfood.domain.repository.RestauranteRepository;
import com.irmamsantos.restaurantfood.domain.service.RestauranteService;

@RestController
@RequestMapping(value = "/restaurantes") 
public class RestauranteController {
	
	@Autowired
	private RestauranteRepository restauranteRepository; 
	
	@Autowired
	private RestauranteService restauranteService; 
	
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public List<Restaurante> listar() {
		List<Restaurante> restaurantes = restauranteRepository.findAll();
		
//		System.out.println(restaurantes.get(0).getNome());
//		restaurantes.get(0).getFormasPagamento().forEach(System.out::println);
//		
//		System.out.println(restaurantes.get(1).getNome());
//		restaurantes.get(1).getFormasPagamento().forEach(System.out::println);
		
		System.out.println("O nome cozinha é:");
		System.out.println(restaurantes.get(0).getCozinha().getNome());

		return restaurantes;
	}
	
	@GetMapping("/{restauranteId}")
	public Restaurante buscar(@PathVariable("restauranteId") Long id) 
			throws RestauranteNaoEncontradoException {
		return restauranteService.buscarOuFalhar(id);
/*		
		Optional<Restaurante> restaurante = restauranteRepository.findById(id);
		
		if (restaurante.isPresent()) {
			//return ResponseEntity.status(HttpStatus.OK).body(restaurante);
			return ResponseEntity.ok(restaurante.get());
		}
		//return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		return ResponseEntity.notFound().build();
*/		
	}
	
	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public Restaurante adicionar(@RequestBody Restaurante restaurante)
			throws NegocioException {
		try {
			return restauranteService.salvar(restaurante);
		} catch (CozinhaNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}
	
	@PutMapping("/{restauranteId}")
	public Restaurante actualizar(@PathVariable Long restauranteId, @RequestBody Restaurante restaurante)
			throws RestauranteNaoEncontradoException, NegocioException {

		Restaurante restauranteActual = restauranteService.buscarOuFalhar(restauranteId);

		BeanUtils.copyProperties(restaurante, restauranteActual, "id", "formasPagamento", "endereco", 
				"dataCadastro",	"dataAtualizacao", "produtos");

		try {
			return restauranteService.salvar(restauranteActual);
		} catch (EntidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e);
		}
/*		
		Optional<Restaurante> restauranteActual = restauranteRepository.findById(restauranteId);
		if (restauranteActual.isPresent()) {
			BeanUtils.copyProperties(restaurante, restauranteActual.get(), 
					"id", "formasPagamento", "endereco", "dataCadastro", "dataAtualizacao", "produtos");
			try {
				restauranteService.salvar(restauranteActual.get());
				return ResponseEntity.ok(restauranteActual);
			} catch (EntidadeNaoEncontradaException e) {
				return ResponseEntity.badRequest().body(e.getMessage());
			}
		}
		return ResponseEntity.notFound().build();
*/		
	} 
	
	@PatchMapping("/{restauranteId}")
	public Restaurante actualizarParcial(@PathVariable Long restauranteId, 
			@RequestBody Map<String, Object> campos, HttpServletRequest request) 
					throws RestauranteNaoEncontradoException, NegocioException {
		
		Restaurante restauranteActual = restauranteService.buscarOuFalhar(restauranteId);
		
		merge(campos, restauranteActual, request);
		
		return actualizar(restauranteId, restauranteActual);
/*		
		Optional<Restaurante> restauranteActual = restauranteRepository.findById(restauranteId);
		if (restauranteActual.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		merge(campos, restauranteActual.get());
		
		return actualizar(restauranteId, restauranteActual.get());
*/		
	}

	@DeleteMapping("/{restauranteId}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long restauranteId) 
			throws RestauranteNaoEncontradoException, EntidadeEmUsoException {
		restauranteService.excluir(restauranteId);
/*		
		try {
			restauranteService.excluir(restauranteId);
			return ResponseEntity.noContent().build();
			
		} catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.notFound().build();
			
		} catch (EntidadeEmUsoException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
*/		
	}
	
	private void merge(Map<String, Object> dadosOrigem, Restaurante restauranteDestino, HttpServletRequest request) {

		ServletServerHttpRequest httpRequest = new ServletServerHttpRequest(request);

		try {
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);

			Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class);

			dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {

				Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
				field.setAccessible(true);

				Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);

				System.out.println(nomePropriedade + " = " + valorPropriedade + " = " + novoValor);

				ReflectionUtils.setField(field, restauranteDestino, novoValor);
			});
		} catch (IllegalArgumentException ex) {
			Throwable rootCause = ExceptionUtils.getRootCause(ex);
			throw new HttpMessageNotReadableException(ex.getMessage(), rootCause, httpRequest);
		}
	}
}
