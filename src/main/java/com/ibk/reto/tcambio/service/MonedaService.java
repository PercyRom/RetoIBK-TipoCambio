package com.ibk.reto.tcambio.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibk.reto.tcambio.entity.Moneda;
import com.ibk.reto.tcambio.repository.MonedaRepository;

@Service
@Transactional
public class MonedaService {

	@Autowired
	MonedaRepository monedaRepository;

	public List<Moneda> lista() {
		return monedaRepository.findAll();
	}

	public void save(Moneda moneda) {
		monedaRepository.save(moneda);
	}
}
