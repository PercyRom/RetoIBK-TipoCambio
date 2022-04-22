package com.ibk.reto.tcambio.secutiry.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginUsuario implements Serializable{
	
    private static final long serialVersionUID = 1L;

	@NotBlank
    private String nombreUsuario;
    
    @NotBlank
    private String password;

}
