package com.irmamsantos.restaurantfood.core.validation;

import org.springframework.validation.BindingResult;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ValidacaoException extends RuntimeException {

	private static final long serialVersionUID = 2919532041532593696L;

	private BindingResult bindingResult;
}
