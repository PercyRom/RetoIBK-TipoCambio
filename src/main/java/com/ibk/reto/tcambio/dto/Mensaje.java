package com.ibk.reto.tcambio.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Mensaje {
	
	private String mensaje;

	public Mensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public Mensaje() {
	}
	
	
}
