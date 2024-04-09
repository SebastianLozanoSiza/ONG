package com.sebas.demo.repositories;

import org.springframework.data.repository.CrudRepository;

import com.sebas.demo.repositories.entities.Persona;

public interface RepositoryPersona extends CrudRepository<Persona, Long>{
    
    Persona findByEmail(String email);
}
