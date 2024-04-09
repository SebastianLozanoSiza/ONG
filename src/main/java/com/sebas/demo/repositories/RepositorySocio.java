package com.sebas.demo.repositories;

import org.springframework.data.repository.CrudRepository;

import com.sebas.demo.repositories.entities.Socio;

public interface RepositorySocio extends CrudRepository<Socio, Long>{
    
}
