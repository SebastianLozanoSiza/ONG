package com.sebas.demo.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.sebas.demo.config.EnvioDTOConverter;
import com.sebas.demo.dto.EnvioDTO;
import com.sebas.demo.dto.SedeDTO;
import com.sebas.demo.exception.BussinesRuleException;
import com.sebas.demo.repositories.RepositoryEnvio;
import com.sebas.demo.repositories.RepositoryRefugio;
import com.sebas.demo.repositories.RepositorySede;
import com.sebas.demo.repositories.entities.Envio;
import com.sebas.demo.repositories.entities.Refugio;
import com.sebas.demo.repositories.entities.Sede;
import com.sebas.demo.services.ServiceEnvio;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ServiceEnvioImpl implements ServiceEnvio {

    @Autowired
    private RepositoryEnvio repositoryEnvio;

    @Autowired
    private RepositoryRefugio repositoryRefugio;

    @Autowired
    private RepositorySede repositorySede;

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
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.PRECONDITION_FAILED,
                        "Error! Envío no encontrado"));
    }

    @Override
    public Envio save(EnvioDTO envioDTO) throws BussinesRuleException {
        Optional<Refugio> refugioOptional = repositoryRefugio.findById(envioDTO.getRefugioId());
        List<Sede> sedes = new ArrayList<>();
        for (SedeDTO sedeDTO : envioDTO.getSedes()) {
            Optional<Sede> sedeOptional = repositorySede.findById(sedeDTO.getId());
            if (sedeOptional.isPresent()) {
                sedes.add(sedeOptional.get());
            } else {
                BussinesRuleException exception = new BussinesRuleException("1058",
                        "Error! Sede no existente" + sedeDTO.getId(), HttpStatus.PRECONDITION_FAILED);
                throw exception;
            }
        }
        if (refugioOptional.isPresent() && !sedes.isEmpty()) {
            Envio envioEntity = envioDTOConverter.convertToEntity(envioDTO);
            envioEntity.setSedes(sedes);
            envioEntity.setRefugio(refugioOptional.get());
            return repositoryEnvio.save(envioEntity);
        }
        return null;
    }

}
