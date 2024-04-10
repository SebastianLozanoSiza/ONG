package com.sebas.demo.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.sebas.demo.config.PersonaDTOConverter;
import com.sebas.demo.config.SocioDTOConverter;
import com.sebas.demo.dto.PersonaDTO;
import com.sebas.demo.dto.SocioDTO;
import com.sebas.demo.repositories.RepositoryPersona;
import com.sebas.demo.repositories.RepositorySocio;
import com.sebas.demo.repositories.entities.Persona;
import com.sebas.demo.repositories.entities.Socio;
import com.sebas.demo.services.ServiceSocio;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ServiceSocioImpl implements ServiceSocio {

    @Autowired
    private RepositorySocio repositorySocio;
    private RepositoryPersona repositoryPersona;

    @Autowired
    private SocioDTOConverter convert;

    @Autowired
    private PersonaDTOConverter convertP;

    @Override
    @Transactional(readOnly = true)
    public List<SocioDTO> findAll() {
        List<Socio> socios = (List<Socio>) repositorySocio.findAll();
        return socios.stream()
                .map(socio -> convert.convertSocioDTO(socio))
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Socio findById(Long id) {
        return repositorySocio.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.PRECONDITION_FAILED,
                        "Error! Socio no existente"));
    }

    @Override
    public SocioDTO save(PersonaDTO persona, SocioDTO socio) {
        // Convertir el DTO de persona a entidad y guardarla si no es nula
        Persona personaEntity = null;
        if (persona != null) {
            personaEntity = convertP.convertPersonaEntity(persona);
            personaEntity = repositoryPersona.save(personaEntity);
        }

        // Convertir el DTO de socio a entidad
        Socio socioEntity = convert.convertSocioEntity(socio);

        // Asignar la persona a la entidad de socio
        socioEntity.setPersona(personaEntity);

        // Guardar el socio en el repositorio
        socioEntity = repositorySocio.save(socioEntity);

        // Convertir la entidad de socio a DTO y retornarla
        return convert.convertSocioDTO(socioEntity);
    }

    @Override
    public Socio update(Long id, Socio socio) {
        Optional<Socio> socioCurrentOptional = repositorySocio.findById(id);

        if (socioCurrentOptional.isPresent()) {
            Socio socioCurrent = socioCurrentOptional.get();
            repositorySocio.save(socioCurrent);
            return socioCurrent;
        }

        return null;
    }

    @Override
    public void delete(Long id) {
        Optional<Socio> socioOptional = repositorySocio.findById(id);
        if (socioOptional.isPresent()) {
            repositorySocio.delete(socioOptional.get());
        }
    }

}
