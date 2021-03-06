package com.irmamsantos.restaurantfood.api.controller;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.irmamsantos.restaurantfood.api.assembler.RestauranteDTOAssembler;
import com.irmamsantos.restaurantfood.api.assembler.RestauranteInputDTODisassembler;
import com.irmamsantos.restaurantfood.api.model.dto.input.RestauranteInputDTO;
import com.irmamsantos.restaurantfood.api.model.dto.output.RestauranteDTO;
import com.irmamsantos.restaurantfood.api.model.view.RestauranteView;
import com.irmamsantos.restaurantfood.core.validation.ValidacaoException;
import com.irmamsantos.restaurantfood.domain.exception.CidadeNaoEncontradaException;
import com.irmamsantos.restaurantfood.domain.exception.CozinhaNaoEncontradaException;
import com.irmamsantos.restaurantfood.domain.exception.EntidadeEmUsoException;
import com.irmamsantos.restaurantfood.domain.exception.NegocioException;
import com.irmamsantos.restaurantfood.domain.exception.RestauranteNaoEncontradoException;
import com.irmamsantos.restaurantfood.domain.model.Restaurante;
import com.irmamsantos.restaurantfood.domain.repository.RestauranteRepository;
import com.irmamsantos.restaurantfood.domain.service.RestauranteService;

//@CrossOrigin(origins = "http://localhost:8000")
//@CrossOrigin(origins = "*")
// Passou para WebConfig
//@CrossOrigin
@RestController
@RequestMapping(value = "/restaurantes") 
public class RestauranteController {
	
	@Autowired
	private RestauranteRepository restauranteRepository; 
	
	@Autowired
	private RestauranteService restauranteService; 
	
	@Autowired
	private SmartValidator validator;
	
	@Autowired
	private RestauranteDTOAssembler restauranteDTOAssembler;
	
	@Autowired
	private RestauranteInputDTODisassembler restauranteInputDTODisassembler;	
	
	//CUIDADO projecao só têm um c, estava a ter um comportamento não esperado pelo paramentro 
	//não ser o mesmo...
	@JsonView(RestauranteView.ApenasNome.class)
	@GetMapping(params = "projecao=apenas-nome")
	public List<RestauranteDTO> listarApenasNomes(@RequestParam(required = false) String projecao) {
		return listar();
	}	
	
	@JsonView(RestauranteView.Resumo.class)
	@GetMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public List<RestauranteDTO> listar() {
		List<RestauranteDTO> restaurantes = restauranteDTOAssembler.toCollectionDTO(restauranteRepository.findAll());
		
//		System.out.println(restaurantes.get(0).getNome());
//		restaurantes.get(0).getFormasPagamento().forEach(System.out::println);
//		
//		System.out.println(restaurantes.get(1).getNome());
//		restaurantes.get(1).getFormasPagamento().forEach(System.out::println);
		
		System.out.println("O nome cozinha é:");
		System.out.println(restaurantes.get(0).getCozinha().getNome());
		
/* Exemplo de adicionar manualmente ao header a definição HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN 
 qual origem permitida 
 		
		return ResponseEntity.ok()
				//.header(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "http://localhost:8000")
				.header(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, "*")
				.body(restaurantes);
*/
		return restaurantes;
	}
	
//	@GetMapping
//	public MappingJacksonValue listar(@RequestParam(required = false) String projecao) {
//		List<Restaurante> restaurantes = restauranteRepository.findAll();
//		List<RestauranteModel> restaurantesModel = restauranteModelAssembler.toCollectionModel(restaurantes);
//		
//		MappingJacksonValue restaurantesWrapper = new MappingJacksonValue(restaurantesModel);
//		
//		restaurantesWrapper.setSerializationView(RestauranteView.Resumo.class);
//		
//		if ("apenas-nome".equals(projecao)) {
//			restaurantesWrapper.setSerializationView(RestauranteView.ApenasNome.class);
//		} else if ("completo".equals(projecao)) {
//			restaurantesWrapper.setSerializationView(null);
//		}
//		
//		return restaurantesWrapper;
//	}
	
//	@GetMapping
//	public List<RestauranteModel> listar() {
//		return restauranteModelAssembler.toCollectionModel(restauranteRepository.findAll());
//	}
//	
//	@JsonView(RestauranteView.Resumo.class)
//	@GetMapping(params = "projecao=resumo")
//	public List<RestauranteModel> listarResumido() {
//		return listar();
//	}
//
//	@JsonView(RestauranteView.ApenasNome.class)
//	@GetMapping(params = "projecao=apenas-nome")
//	public List<RestauranteModel> listarApenasNomes() {
//		return listar();
//	}
	
	
	@GetMapping("/{restauranteId}")
	public RestauranteDTO buscar(@PathVariable("restauranteId") Long id) 
			throws RestauranteNaoEncontradoException {
//		if (true) {
//			throw new IllegalStateException("teste");
//		}
		Restaurante restaurante = restauranteService.buscarOuFalhar(id);
		
		return restauranteDTOAssembler.toDTO(restaurante);
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
	public RestauranteDTO adicionar(
			@RequestBody @Valid RestauranteInputDTO restauranteInput)
			throws NegocioException {
		try {
			Restaurante restaurante = restauranteInputDTODisassembler.toDomainObject(restauranteInput);
			return restauranteDTOAssembler.toDTO(restauranteService.salvar(restaurante));
			//Alterei de EntidadeNaoEncontradaException para estes 2 para ficar igual ao leccionado 
		} catch (CozinhaNaoEncontradaException | CidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage(), e);
		}
	}
	
	@PutMapping("/{restauranteId}")
	public RestauranteDTO actualizar(@PathVariable Long restauranteId, 
			@RequestBody @Valid RestauranteInputDTO restauranteInput)
			throws RestauranteNaoEncontradoException, NegocioException {

		Restaurante restauranteActual = restauranteService.buscarOuFalhar(restauranteId);

		//depois de remover BeansUtils também removeu esta invocação
		//Restaurante restaurante = restauranteInputDTODisassembler.toDomainObject(restauranteInput);

//Para não estar identificar os campos que não quer copiar de forma explicita
//deixa de usar o BeansUtils e passa usar o ModelMapper em que o destino terá apenas os campos
//que pretende actualizar		
//		BeanUtils.copyProperties(restaurante, restauranteActual, "id", "formasPagamento", "endereco", 
//				"dataCadastro",	"dataAtualizacao", "produtos");
		
		restauranteInputDTODisassembler.copyToDomainObject(restauranteInput, restauranteActual);

		try {
			return restauranteDTOAssembler.toDTO(restauranteService.salvar(restauranteActual));
		//Alterei de EntidadeNaoEncontradaException para estes 2 para ficar igual ao leccionado 
		} catch (CozinhaNaoEncontradaException | CidadeNaoEncontradaException e) {
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
	
	//acho que este método já não está a ser usado...
	@PatchMapping("/{restauranteId}")
	public RestauranteDTO actualizarParcial(@PathVariable Long restauranteId, 
			@RequestBody Map<String, Object> campos, HttpServletRequest request) 
					throws RestauranteNaoEncontradoException, NegocioException {
		
		Restaurante restauranteActual = restauranteService.buscarOuFalhar(restauranteId);
		
		merge(campos, restauranteActual, request);
		
		validate(restauranteActual, "restaurante");
		
		return actualizar(restauranteId, 
				restauranteInputDTODisassembler.domainObjectToDTO(restauranteActual));
/*		
		Optional<Restaurante> restauranteActual = restauranteRepository.findById(restauranteId);
		if (restauranteActual.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		
		merge(campos, restauranteActual.get());
		
		return actualizar(restauranteId, restauranteActual.get());
*/		
	}

	private void validate(Restaurante restaurante, String objectName) {
		BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(restaurante, objectName);
		validator.validate(restaurante, bindingResult);
		
		if (bindingResult.hasErrors()) {
			throw new ValidacaoException(bindingResult);
		}
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
	
	//PUT /restaurantes/{1}/ativo
	//DELETE /restaurantes/{1}/ativo
	
	@PutMapping("/{restauranteId}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void ativar(@PathVariable Long restauranteId) {
		restauranteService.ativar(restauranteId);
	}

	@DeleteMapping("/{restauranteId}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void inativar(@PathVariable Long restauranteId) {
		restauranteService.inativar(restauranteId);
	}
	
	//PUT /restaurante/activacoes
	//[1,3,4]
	//DELETE /restaurante/activacoes
	@PutMapping("/ativacoes")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void ativarMultiplos(@RequestBody List<Long> restauranteIds) {
		restauranteService.ativar(restauranteIds);
	}
	
	@DeleteMapping("/ativacoes")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void inativarMultiplos(@RequestBody List<Long> restauranteIds) {
		restauranteService.inativar(restauranteIds);
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
	
	@PutMapping("/{restauranteId}/abertura")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void abrir(@PathVariable Long restauranteId) {
		restauranteService.abrir(restauranteId);
	}

	@PutMapping("/{restauranteId}/fechamento")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void fechar(@PathVariable Long restauranteId) {
		restauranteService.fechar(restauranteId);
	}	
}
