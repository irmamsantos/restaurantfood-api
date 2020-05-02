package com.irmamsantos.restaurantfood.api.model.dto.input;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import com.irmamsantos.restaurantfood.core.validation.FileContentType;
import com.irmamsantos.restaurantfood.core.validation.FileSize;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FotoProdutoInputDTO {
	
	@NotNull
	@FileSize(max = "50KB")
	@FileContentType(allowed = { MediaType.IMAGE_JPEG_VALUE, MediaType.IMAGE_PNG_VALUE })
	private MultipartFile arquivo;
	
	@NotBlank
	private String descricao;
}
