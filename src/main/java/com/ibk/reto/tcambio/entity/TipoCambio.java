package com.ibk.reto.tcambio.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class TipoCambio extends Auditable implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private float monto;
	private int idOrigen;
	private String nombreOrigen;
	private float valorOrigen;
	private int idDestino;
	private String nombreDestino;
	private float valorDestino;

	public TipoCambio() {
	}

	public TipoCambio(float monto, int idOrigen, String nombreOrigen, float valorOrigen,
			int idDestino, String nombreDestino, float valorDestino) {
		this.monto = monto;
		this.idOrigen = idOrigen;
		this.nombreOrigen = nombreOrigen;
		this.valorOrigen = valorOrigen;
		this.idDestino = idDestino;
		this.nombreDestino = nombreDestino;
		this.valorDestino = valorDestino;
	}
		
}
