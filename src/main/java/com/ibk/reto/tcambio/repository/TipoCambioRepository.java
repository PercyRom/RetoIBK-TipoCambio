package com.ibk.reto.tcambio.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ibk.reto.tcambio.entity.TipoCambio;

@Repository
public interface TipoCambioRepository extends JpaRepository<TipoCambio, Integer> {

	public List<TipoCambio> findAllByOrderByFechaCreacionDesc();

}
