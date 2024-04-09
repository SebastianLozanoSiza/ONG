package com.sebas.demo.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.sebas.demo.config.EnvioDTOConverter;
import com.sebas.demo.dto.EnvioDTO;
import com.sebas.demo.repositories.RepositoryEnvio;
import com.sebas.demo.repositories.entities.Envio;
import com.sebas.demo.services.ServiceEnvio;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ServiceEnvioImpl implements ServiceEnvio{
    
    @Autowired
    private RepositoryEnvio repositoryEnvio;

    @Autowired
    private EnvioDTOConverter envioDTOConverter;

    @Override
    @Transactional(readOnly = true)
    public List<EnvioDTO> findAll() {
        List<Envio> envios = (List<Envio>) repositoryEnvio.findAll();
        return envios.stream()
                .map(envio -> envioDTOConverter.convertToDTO(envio))
                .collect(Collectors.toList());
    }

    @Override
    public Envio findById(Long id) {
        return repositoryEnvio.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.PRECONDITION_FAILED, "Error! Envío no encontrado"));
    }
    
}
