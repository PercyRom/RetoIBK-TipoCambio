package com.ibk.reto.tcambio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibk.reto.tcambio.dto.Mensaje;
import com.ibk.reto.tcambio.dto.TipoCambioDto;
import com.ibk.reto.tcambio.entity.TipoCambio;
import com.ibk.reto.tcambio.service.TipoCambioService;

@RestController
@RequestMapping("/api/v1/tipoCambio")
@CrossOrigin(origins = "*")
public class TipoCambioController {

	@Autowired
	TipoCambioService tipoCambioService;

	@Autowired
	Environment env;

	@GetMapping("/lista")
	public ResponseEntity<List<TipoCambio>> list() {
		List<TipoCambio> list = tipoCambioService.list();
		return new ResponseEntity<List<TipoCambio>>(list, HttpStatus.OK);
	}

	@GetMapping("/detail/{id}")
	public ResponseEntity<?> getById(@PathVariable("id") int id) {
		TipoCambio tipoCambio;

		if (!tipoCambioService.existsById(id)) {
			return message("app.msj.no.existe", HttpStatus.NOT_FOUND);
		} else {
			tipoCambio = tipoCambioService.getOne(id).get();
		}
		return new ResponseEntity<TipoCambio>(tipoCambio, HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/create")
	public ResponseEntity<?> create(@RequestBody TipoCambioDto tipoCambioDto) {
		TipoCambio tipoCambio;

		if ((tipoCambioDto.getMonto() <= 0)) {
			return message("app.msj.monto.obligatorio", HttpStatus.BAD_REQUEST);
			
		} else if (tipoCambioDto.getIdOrigen() <= 0) {
			return message("app.msj.origen.invalido", HttpStatus.BAD_REQUEST);
			
		} else if (tipoCambioDto.getIdDestino() <= 0) {
			return message("app.msj.destino.invalido", HttpStatus.BAD_REQUEST);
			
		} else {
			
			tipoCambio = new TipoCambio();
			tipoCambio.setMonto(tipoCambioDto.getMonto());
			tipoCambio.setIdOrigen(tipoCambioDto.getIdOrigen());
			tipoCambio.setNombreOrigen(tipoCambioDto.getNombreOrigen());
			tipoCambio.setValorOrigen(tipoCambioDto.getValorOrigen());
			tipoCambio.setIdDestino(tipoCambioDto.getIdDestino());
			tipoCambio.setNombreDestino(tipoCambioDto.getNombreDestino());
			tipoCambio.setValorDestino(tipoCambioDto.getValorDestino());

			tipoCambioService.save(tipoCambio);
		}

		return message("app.msj.registro.creado", HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@PutMapping("/update/{id}")
	public ResponseEntity<?> update(@PathVariable("id") int id, @RequestBody TipoCambioDto tipoCambioDto) {

		TipoCambio tipoCambio;

		if (!tipoCambioService.existsById(id)) {
			return message("app.msj.no.existe", HttpStatus.NOT_FOUND);
			
		} else {

			tipoCambio = new TipoCambio();
			tipoCambio.setId(id);
			tipoCambio.setMonto(tipoCambioDto.getMonto());
			tipoCambio.setIdOrigen(tipoCambioDto.getIdOrigen());
			tipoCambio.setNombreOrigen(tipoCambioDto.getNombreOrigen());
			tipoCambio.setValorOrigen(tipoCambioDto.getValorOrigen());
			tipoCambio.setIdDestino(tipoCambioDto.getIdDestino());
			tipoCambio.setNombreDestino(tipoCambioDto.getNombreDestino());
			tipoCambio.setValorDestino(tipoCambioDto.getValorDestino());
			tipoCambioService.save(tipoCambio);
		}

		return message("app.msj.registro.editado", HttpStatus.OK);
	}

	@PreAuthorize("hasRole('ADMIN')")
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> delete(@PathVariable("id") int id) {

		if (!tipoCambioService.existsById(id)) {
			return message("app.msj.no.existe", HttpStatus.NOT_FOUND);
		} else {
			tipoCambioService.delete(id);
		}
		return message("app.msj.registro.eliminado", HttpStatus.OK);

	}

	private ResponseEntity<Object> message(String key, HttpStatus status) {
		return new ResponseEntity<Object>(new Mensaje(env.getProperty(key)), status);
	}

}
