package com.sebas.demo.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.sebas.demo.repositories.RepositoryPersona;
import com.sebas.demo.repositories.entities.Persona;
import com.sebas.demo.services.ServicePersona;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ServicePersonaImpl implements ServicePersona{

    private RepositoryPersona repositoryPersona;

    @Override
    @Transactional(readOnly = true)
    public List<Persona> findAll() {
        return (List<Persona>) repositoryPersona.findAll();
    }

    @Override
    public Persona save(Persona persona) {
        return repositoryPersona.save(persona);
    }

    @Override
    public Persona update(Long id, Persona persona) {
        Optional<Persona> personaCurrentOptional = repositoryPersona.findById(id);

        if(personaCurrentOptional.isPresent()){
            Persona personaCurrent = personaCurrentOptional.get();
            personaCurrent.setNombre(persona.getNombre());
            repositoryPersona.save(personaCurrent);
            return personaCurrent;
        }

        return null;
    }

    @Override
    public void delete(Long id) {
        Optional<Persona> personaOptional=repositoryPersona.findById(id);
        if(personaOptional.isPresent()){
            repositoryPersona.delete(personaOptional.get());
        }   
    }

    @Override
    public Persona findById(Long id)  {
        return repositoryPersona.findById(id)
        .orElseThrow(() -> new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, "Error! Persona no existente"));
    }

    @Override
    public Persona findByEmail(String email) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findByEmail'");
    }
    
}
