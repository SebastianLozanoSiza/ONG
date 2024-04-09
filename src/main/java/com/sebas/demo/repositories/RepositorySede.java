package com.sebas.demo.repositories;

import org.springframework.data.repository.CrudRepository;

import com.sebas.demo.repositories.entities.Sede;

public interface RepositorySede extends CrudRepository<Sede, Long> {
    
}
