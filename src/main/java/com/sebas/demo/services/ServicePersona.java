package com.sebas.demo.services;

import java.util.List;

import com.sebas.demo.repositories.entities.Persona;

public interface ServicePersona {
    
    List<Persona> findAll();

    Persona findById(Long id);

    Persona save(Persona persona);

    Persona update(Long id, Persona persona);

    void delete(Long id);

    Persona findByEmail(String email);
}
