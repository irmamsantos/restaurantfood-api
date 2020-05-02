package com.irmamsantos.restaurantfood.api.model.dto.input;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FotoProdutoInputDTO {
	
	private MultipartFile arquivo;
	private String descricao;
}
