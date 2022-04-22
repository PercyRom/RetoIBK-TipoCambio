package com.ibk.reto.tcambio.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibk.reto.tcambio.entity.TipoCambio;
import com.ibk.reto.tcambio.repository.TipoCambioRepository;

@Service
@Transactional
public class TipoCambioService {

	@Autowired
	TipoCambioRepository tipoCambioRepository;

	public List<TipoCambio> list() {
		return tipoCambioRepository.findAllByOrderByFechaCreacionDesc();
	}

	public Optional<TipoCambio> getOne(int id) {
		return tipoCambioRepository.findById(id);
	}

	public void save(TipoCambio tipoCambio) {
		tipoCambioRepository.save(tipoCambio);
	}

	public void delete(int id) {
		tipoCambioRepository.deleteById(id);
	}

	public boolean existsById(int id) {
		return tipoCambioRepository.existsById(id);
	}
}
