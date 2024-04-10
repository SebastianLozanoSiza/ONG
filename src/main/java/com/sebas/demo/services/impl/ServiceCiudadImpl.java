package com.sebas.demo.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import com.sebas.demo.exception.BussinesRuleException;
import com.sebas.demo.repositories.RepositoryCiudad;
import com.sebas.demo.repositories.entities.Ciudad;
import com.sebas.demo.services.ServiceCiudad;
import com.sebas.demo.exception.BussinesRuleException;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ServiceCiudadImpl implements ServiceCiudad{
    
    private RepositoryCiudad repositoryCiudad;

    @Override
    @Transactional(readOnly = true)
    public List<Ciudad> findAll() {
        return (List<Ciudad>) repositoryCiudad.findAll();
    }

    @Override
    public Ciudad findById(Long id) throws BussinesRuleException{
        Optional<Ciudad> ciudadOptional = repositoryCiudad.findById(id);
        if(!ciudadOptional.isPresent()){
            BussinesRuleException exception= new BussinesRuleException("1001","Error! Ciudad no existente",HttpStatus.PRECONDITION_FAILED);
            throw exception; 
        }
        return ciudadOptional.get();
    }

    @Override
    public Ciudad save(Ciudad ciudad) {
        return repositoryCiudad.save(ciudad);
    }

    @Override
    public Ciudad update(Long id, Ciudad ciudad) {
        Optional<Ciudad> ciudadCurrentOptional = repositoryCiudad.findById(id);

        if(ciudadCurrentOptional.isPresent()){
            Ciudad ciudadCurrent = ciudadCurrentOptional.get();
            ciudadCurrent.setNombre(ciudad.getNombre());
            repositoryCiudad.save(ciudadCurrent);
            return ciudadCurrent;
        }

        return null;
    }

    @Override
    public void delete(Long id) {
        Optional<Ciudad> ciudadOptional=repositoryCiudad.findById(id);
        if(ciudadOptional.isPresent()){
            repositoryCiudad.delete(ciudadOptional.get());
        }   
    }
    
}
