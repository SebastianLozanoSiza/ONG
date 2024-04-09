package com.sebas.demo.repositories;

import org.springframework.data.repository.CrudRepository;

import com.sebas.demo.repositories.entities.Ciudad;

public interface RepositoryCiudad extends CrudRepository<Ciudad, Long>{
    
}
