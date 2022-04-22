package com.ibk.reto.tcambio.secutiry.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NuevoUsuario implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotBlank
	private String nombre;

	@NotBlank
	private String nombreUsuario;

	@Email
	private String email;

	@NotBlank
	private String password;

	private Set<String> roles = new HashSet<>();

}
