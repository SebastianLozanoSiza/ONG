package com.sebas.demo.services;

import java.util.List;

import com.sebas.demo.dto.EnvioDTO;
import com.sebas.demo.exception.BussinesRuleException;
import com.sebas.demo.repositories.entities.Envio;

public interface ServiceEnvio {

    List<EnvioDTO> findAll();

    Envio findById(Long id) ;

    Envio save(EnvioDTO envio) throws BussinesRuleException;
    
}
