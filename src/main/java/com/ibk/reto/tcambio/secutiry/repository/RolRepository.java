package com.ibk.reto.tcambio.secutiry.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ibk.reto.tcambio.secutiry.entity.Rol;
import com.ibk.reto.tcambio.secutiry.enums.RolNombre;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {
    Optional<Rol> findByRolNombre(RolNombre rolNombre);
}
