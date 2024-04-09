package com.sebas.demo.services;

import java.util.List;

import com.sebas.demo.repositories.entities.Ciudad;

public interface ServiceCiudad {
    
    List<Ciudad> findAll();

    Ciudad findById(Long id);

    Ciudad save(Ciudad ciudad);

    Ciudad update(Long id, Ciudad ciudad);

    void delete(Long id);
}
