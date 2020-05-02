package com.irmamsantos.restaurantfood.api.model.dto.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import com.irmamsantos.restaurantfood.core.validation.FileSize;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FotoProdutoInputDTO {
	
	@NotNull
	@FileSize(max = "50KB")
	private MultipartFile arquivo;
	
	@NotBlank
	private String descricao;
}
