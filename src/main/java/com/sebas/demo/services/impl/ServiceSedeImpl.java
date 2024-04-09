package com.sebas.demo.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.sebas.demo.config.SedeDTOConverter;
import com.sebas.demo.dto.SedeDTO;
import com.sebas.demo.repositories.RepositoryCiudad;
import com.sebas.demo.repositories.RepositoryPersona;
import com.sebas.demo.repositories.RepositorySede;
import com.sebas.demo.repositories.entities.Ciudad;
import com.sebas.demo.repositories.entities.Persona;
import com.sebas.demo.repositories.entities.Sede;
import com.sebas.demo.services.ServiceSede;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ServiceSedeImpl implements ServiceSede {

    @Autowired
    private RepositorySede repositorySede;
    private RepositoryPersona repositoryPersona;
    private RepositoryCiudad repositoryCiudad;

    @Autowired
    private SedeDTOConverter convert;

    @Override
    @Transactional(readOnly = true)
    public List<SedeDTO> findAll() {
        List<Sede> sedes = (List<Sede>) repositorySede.findAll();
        return sedes.stream()
                .map(sede -> convert.convertSedeDTO(sede))
                .toList();
    }

    @Override
    public Sede findById(Long id) {
        return repositorySede.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, "Error! Sede no existente"));
    }
    

    @Override
    public SedeDTO save(SedeDTO sede) {
        Optional<Persona> persOptional = repositoryPersona.findById(sede.getDirectorId());
        Optional<Ciudad> ciudadOptional = repositoryCiudad.findById(sede.getCiudadId());

        if (persOptional.isPresent() && ciudadOptional.isPresent()) {
            Sede sedeEntity = convert.convertSedeEntity(sede);
            sedeEntity.setDirector(persOptional.get());
            sedeEntity.setCiudad(ciudadOptional.get());
            repositorySede.save(sedeEntity);
            return convert.convertSedeDTO(sedeEntity);
        }
        return null;
    }

    @Override
    public Sede update(Long id, Sede sede) {
        Optional<Sede> sedeCurrentOptional = repositorySede.findById(id);

        if (sedeCurrentOptional.isPresent()) {
            Sede sedeCurrent = sedeCurrentOptional.get();
            sedeCurrent.setDireccion(sede.getDireccion());
            repositorySede.save(sedeCurrent);
            return sedeCurrent;
        }

        return null;
    }

    @Override
    public void delete(Long id) {
        Optional<Sede> sedeOptional = repositorySede.findById(id);
        if (sedeOptional.isPresent()) {
            repositorySede.delete(sedeOptional.get());
        }
    }

}
