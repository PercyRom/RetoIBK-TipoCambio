package com.ibk.reto.tcambio.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public abstract class Auditable {

	@CreatedBy
	@Column(name = "creado_por", updatable = false)
	protected String creadoPor;

	@CreatedDate
	@Column(name = "fecha_creacion", updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	protected Date fechaCreacion;

	@LastModifiedBy
	@Column(name = "modificado_por")
	protected String modificadoPor;

	@LastModifiedDate
	@Column(name = "fecha_modificacion")
	@Temporal(TemporalType.TIMESTAMP)
	protected Date fechaModificacion;

}
