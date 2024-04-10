package com.sebas.demo.services.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sebas.demo.config.EnvioDTOConverter;
import com.sebas.demo.config.SedeDTOConverter;
import com.sebas.demo.config.SocioDTOConverter;
import com.sebas.demo.config.VoluntarioDTOConverter;
import com.sebas.demo.dto.EnvioDTO;
import com.sebas.demo.dto.SedeDTO;
import com.sebas.demo.dto.SocioDTO;
import com.sebas.demo.dto.VoluntarioDTO;
import com.sebas.demo.repositories.RepositoryEnvio;
import com.sebas.demo.repositories.RepositorySede;
import com.sebas.demo.repositories.RepositorySocio;
import com.sebas.demo.repositories.RepositoryVoluntario;
import com.sebas.demo.repositories.entities.Envio;
import com.sebas.demo.repositories.entities.Sede;
import com.sebas.demo.repositories.entities.Socio;
import com.sebas.demo.repositories.entities.Voluntario;
import com.sebas.demo.services.ServiceReportes;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ServiceReportesImpl implements ServiceReportes{
    
    @Autowired
    private RepositorySocio repositorySocio;

    @Autowired
    private SocioDTOConverter convertSocio;

    @Autowired
    private RepositorySede repositorySede;

    @Autowired
    private SedeDTOConverter convertSede;

    @Autowired
    private RepositoryVoluntario repositoryVoluntario;

    @Autowired
    private VoluntarioDTOConverter convertVoluntario;

    @Autowired
    private RepositoryEnvio repositoryEnvio;

    @Autowired
    private EnvioDTOConverter convertEnvio;


    @Override
    @Transactional(readOnly = true)
    public List<SocioDTO> reportesSocios() {
        List<Socio> socios = (List<Socio>) repositorySocio.findAll();
        return socios.stream()
                .map(socio -> convertSocio.convertSocioDTO(socio))
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<SedeDTO> reportesSedes() {
        List<Sede> sedes = (List<Sede>) repositorySede.findAll();
        return sedes.stream()
                .map(sede -> convertSede.convertSedeDTO(sede))
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<VoluntarioDTO> reportesVoluntarios() {
        List<Voluntario> voluntarios = (List<Voluntario>) repositoryVoluntario.findAll();
        return voluntarios.stream()
                .map(voluntario -> convertVoluntario.convertVoluntarioDTO(voluntario))
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public List<EnvioDTO> reportesEnvios() {
        List<Envio> envios = (List<Envio>) repositoryEnvio.findAll();
        return envios.stream()
                .map(envio -> convertEnvio.convertToDTO(envio))
                .collect(Collectors.toList());
    }
    
}
