package com.sebas.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sebas.demo.config.EnvioDTOConverter;
import com.sebas.demo.dto.EnvioDTO;
import com.sebas.demo.repositories.entities.Envio;
import com.sebas.demo.services.ServiceEnvio;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/envios")
@AllArgsConstructor
public class EnvioController {

    @Autowired
    private ServiceEnvio serviceEnvio;

    @Autowired
    private EnvioDTOConverter convert;

    @GetMapping("/")
    public ResponseEntity<List<EnvioDTO>> findAll() {
        List<EnvioDTO> envios = serviceEnvio.findAll();
        if (envios.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(envios);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<EnvioDTO> findById(@PathVariable Long id) {
        Envio envio = serviceEnvio.findById(id);
        return ResponseEntity.ok(convert.convertToDTO(envio));
    }
    
}
