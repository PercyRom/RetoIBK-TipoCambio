package com.ibk.reto.tcambio.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TipoCambioDto {

	@Min(0)
	private float monto;

	@Min(0)
	private int idOrigen;

	@NotBlank
	private String nombreOrigen;

	@Min(0)
	private float valorOrigen;

	@Min(0)
	private int idDestino;

	@NotBlank
	private String nombreDestino;

	@Min(0)
	private float valorDestino;

	public TipoCambioDto(float monto, int idOrigen, String nombreOrigen, float valorOrigen, String nombreDestino,
			float valorDestino) {
		this.monto = monto;
		this.idOrigen = idOrigen;
		this.nombreOrigen = nombreOrigen;
		this.valorOrigen = valorOrigen;
		this.nombreDestino = nombreDestino;
		this.valorDestino = valorDestino;
	}

}
