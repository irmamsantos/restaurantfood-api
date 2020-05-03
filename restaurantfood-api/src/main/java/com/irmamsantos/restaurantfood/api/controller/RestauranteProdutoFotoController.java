package com.irmamsantos.restaurantfood.api.controller;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.irmamsantos.restaurantfood.api.assembler.FotoProdutoDTOAssembler;
import com.irmamsantos.restaurantfood.api.model.dto.input.FotoProdutoInputDTO;
import com.irmamsantos.restaurantfood.api.model.dto.output.FotoProdutoDTO;
import com.irmamsantos.restaurantfood.domain.model.FotoProduto;
import com.irmamsantos.restaurantfood.domain.model.Produto;
import com.irmamsantos.restaurantfood.domain.service.CatalogoFotoProdutoService;
import com.irmamsantos.restaurantfood.domain.service.ProdutoService;

@RestController
@RequestMapping(value = "/restaurantes/{restauranteId}/produtos/{produtoId}/foto") 
public class RestauranteProdutoFotoController {
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private CatalogoFotoProdutoService catalogoFotoProduto;
	
	@Autowired
	private FotoProdutoDTOAssembler fotoProdutoDTOAssembler;

	@PutMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public FotoProdutoDTO atualizarFoto(@PathVariable Long restauranteId,
			@PathVariable Long produtoId, /*@RequestParam MultipartFile arquivo*/
			@Valid FotoProdutoInputDTO fotoProdutoInput) throws IOException {		
/*		
		String nomeArquivo = UUID.randomUUID().toString() 
				+ "_" + fotoProdutoInput.getArquivo().getOriginalFilename();
		
		var arquivoFoto = Path.of("C:\\3_TRAINNING\\ALGAWORKS\\CURSO_ESPECIALISTA_SPRING_REST\\catalogo"
				, nomeArquivo);
		
		System.out.println(fotoProdutoInput.getDescricao());
		System.out.println(arquivoFoto);
		System.out.println(fotoProdutoInput.getArquivo().getContentType());
		
		try {
			fotoProdutoInput.getArquivo().transferTo(arquivoFoto);
		} catch (Exception e) {
			new Exception(e);
		}
*/
		/*
		 * Como FotoProduto têm muitos mais atributos do que FotoProdutoInputDTO considerou
		 * que não devia usar um assembler, por isso instancia manualmente...
		 */
		MultipartFile arquivo = fotoProdutoInput.getArquivo();
		
		Produto produto = produtoService.buscarOuFalhar(restauranteId, produtoId);
		
		FotoProduto foto = new FotoProduto();
		foto.setProduto(produto);
		foto.setDescricao(fotoProdutoInput.getDescricao());
		foto.setContentType(arquivo.getContentType());
		foto.setTamanho(arquivo.getSize());
		foto.setNomeArquivo(arquivo.getOriginalFilename());
		
		FotoProduto fotoSalva = catalogoFotoProduto.salvar(foto, arquivo.getInputStream());
		
		return fotoProdutoDTOAssembler.toDTO(fotoSalva);
	}
}
