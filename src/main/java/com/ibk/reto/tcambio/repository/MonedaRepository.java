package com.ibk.reto.tcambio.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ibk.reto.tcambio.entity.Moneda;

@Repository
public interface MonedaRepository extends JpaRepository<Moneda, Integer> {
    
//    public List<Moneda> findAllByOrderByFechaCreacionDesc();
    
}
