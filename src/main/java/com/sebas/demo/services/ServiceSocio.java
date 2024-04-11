package com.sebas.demo.services;

import java.util.List;

import com.sebas.demo.dto.PersonaDTO;
import com.sebas.demo.dto.SocioDTO;
import com.sebas.demo.repositories.entities.Socio;

public interface ServiceSocio {
    
    List<SocioDTO> findAll();

    Socio findById(Long id);

    List<SocioDTO> findByTipoCuota(String nombreTipoCuota);

    SocioDTO save(PersonaDTO persona, SocioDTO socio);

    Socio update(Long id, Socio socio);

    void delete(Long id);
}
