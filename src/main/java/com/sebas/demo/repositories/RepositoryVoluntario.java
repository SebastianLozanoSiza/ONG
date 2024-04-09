package com.sebas.demo.repositories;

import org.springframework.data.repository.CrudRepository;

import com.sebas.demo.repositories.entities.Voluntario;

public interface RepositoryVoluntario extends CrudRepository<Voluntario,Long>{
    
}
