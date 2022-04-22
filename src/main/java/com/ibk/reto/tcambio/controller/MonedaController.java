package com.ibk.reto.tcambio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibk.reto.tcambio.entity.Moneda;
import com.ibk.reto.tcambio.service.MonedaService;

@RestController
@RequestMapping("/api/v1/moneda")
@CrossOrigin(origins = "*")
public class MonedaController {

	@Autowired
	MonedaService monedaService;

	@Autowired
	Environment env;

	@GetMapping("/lista")
	public ResponseEntity<List<Moneda>> lista() {
		List<Moneda> list = monedaService.lista();
		return new ResponseEntity<List<Moneda>>(list, HttpStatus.OK);
	}
	
}
