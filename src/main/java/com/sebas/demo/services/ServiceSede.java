package com.sebas.demo.services;

import java.util.List;

import com.sebas.demo.dto.SedeDTO;
import com.sebas.demo.repositories.entities.Sede;

public interface ServiceSede {
    
    List<SedeDTO> findAll();

    Sede findById(Long id);

    SedeDTO save(SedeDTO sede);

    Sede update(Long id, Sede sede);

    void delete(Long id);

}
