package com.sebas.demo.repositories;

import org.springframework.data.repository.CrudRepository;

import com.sebas.demo.repositories.entities.Ocupacion;

public interface RepositoryOcupacion extends CrudRepository<Ocupacion,Long>{

    Ocupacion findByNombre(String nombre);
}
