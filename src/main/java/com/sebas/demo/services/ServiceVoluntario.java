package com.sebas.demo.services;

import java.util.List;

import com.sebas.demo.dto.PersonaDTO;
import com.sebas.demo.dto.VoluntarioDTO;
import com.sebas.demo.repositories.entities.Voluntario;

public interface ServiceVoluntario {
    
    List<VoluntarioDTO> findAll();

    Voluntario findById(Long id);

    VoluntarioDTO save(PersonaDTO persona, VoluntarioDTO voluntario);

    Voluntario update(Long id, Voluntario voluntario);

    void delete(Long id);
}
