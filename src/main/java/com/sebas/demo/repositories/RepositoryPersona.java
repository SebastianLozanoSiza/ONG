package com.sebas.demo.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.sebas.demo.repositories.entities.Persona;

public interface RepositoryPersona extends CrudRepository<Persona, Long>{

    Optional<Persona> findByEmail(String email);
}
