package com.sebas.demo.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.sebas.demo.config.PersonaDTOConverter;
import com.sebas.demo.config.VoluntarioDTOConverter;
import com.sebas.demo.dto.PersonaDTO;
import com.sebas.demo.dto.VoluntarioDTO;
import com.sebas.demo.repositories.RepositoryOcupacion;
import com.sebas.demo.repositories.RepositoryPersona;
import com.sebas.demo.repositories.RepositorySede;
import com.sebas.demo.repositories.RepositoryVoluntario;
import com.sebas.demo.repositories.entities.Ocupacion;
import com.sebas.demo.repositories.entities.Persona;
import com.sebas.demo.repositories.entities.Voluntario;
import com.sebas.demo.services.ServiceVoluntario;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ServiceVoluntarioImpl implements ServiceVoluntario {

    @Autowired
    private RepositoryVoluntario repositoryVoluntario;
    private RepositoryPersona repositoryPersona;
    private RepositorySede repositorySede;
    private RepositoryOcupacion repositoryOcupacion;

    @Autowired
    private VoluntarioDTOConverter convert;

    @Autowired
    private PersonaDTOConverter convertP;

    @Override
    @Transactional(readOnly = true)
    public List<VoluntarioDTO> findAll() {
        List<Voluntario> voluntarios = (List<Voluntario>) repositoryVoluntario.findAll();
        return voluntarios.stream()
                .map(voluntario -> convert.convertVoluntarioDTO(voluntario))
                .toList();
    }

    @Override
    public Voluntario findById(Long id) {
        return repositoryVoluntario.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.PRECONDITION_FAILED,
                        "Error! Voluntario no existente"));
    }

    @Override
    public VoluntarioDTO save(PersonaDTO persona, VoluntarioDTO voluntario) {

        // Convertir el DTO de persona a entidad y guardarla si no es nula
        Persona personaEntity = null;
        if (persona != null) {
            personaEntity = convertP.convertPersonaEntity(persona);
            personaEntity = repositoryPersona.save(personaEntity);
        }

        // Convertir el DTO de voluntario a entidad
        Voluntario voluntarioEntity = convert.convertVoluntarioEntity(voluntario);

        // Asignar la persona a la entidad de voluntario
        voluntarioEntity.setPersona(personaEntity);

        // Guardar el voluntario en el repositorio
        voluntarioEntity = repositoryVoluntario.save(voluntarioEntity);

        // Convertir la entidad de voluntario a DTO y retornarla
        return convert.convertVoluntarioDTO(voluntarioEntity);
    }

    @Override
    public Voluntario update(Long id, Voluntario voluntario) {
        Optional<Voluntario> voluntarioCurrentOptional = repositoryVoluntario.findById(id);

        if (voluntarioCurrentOptional.isPresent()) {
            Voluntario voluntarioCurrent = voluntarioCurrentOptional.get();
            repositoryVoluntario.save(voluntarioCurrent);
            return voluntarioCurrent;
        }

        return null;
    }

    @Override
    public void delete(Long id) {
        Optional<Voluntario> voluntarioOptional = repositoryVoluntario.findById(id);
        if (voluntarioOptional.isPresent()) {
            repositoryVoluntario.delete(voluntarioOptional.get());
        }
    }

}
