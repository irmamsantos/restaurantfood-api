package com.irmamsantos.restaurantfood.api.model.dto.output;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UsuarioDTO /*UsuarioModel*/ {

    private Long id;
    private String nome;
    private String email;            
}
