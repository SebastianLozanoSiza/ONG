package com.sebas.demo.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sebas.demo.repositories.entities.Socio;

public interface RepositorySocio extends CrudRepository<Socio, Long>{
    
    List<Socio> findByTipoCuotaNombre(String nombreTipoCuota);

}
