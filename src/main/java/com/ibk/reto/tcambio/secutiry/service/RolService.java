package com.ibk.reto.tcambio.secutiry.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ibk.reto.tcambio.secutiry.entity.Rol;
import com.ibk.reto.tcambio.secutiry.enums.RolNombre;
import com.ibk.reto.tcambio.secutiry.repository.RolRepository;

@Service
@Transactional
public class RolService {

    @Autowired
    RolRepository rolRepository;

    public Optional<Rol> getByRolNombre(RolNombre rolNombre){
        return rolRepository.findByRolNombre(rolNombre);
    }
    
    public List<Rol> lista(){
        return rolRepository.findAll();
    }

    public void save(Rol rol){
        rolRepository.save(rol);
    }
}
