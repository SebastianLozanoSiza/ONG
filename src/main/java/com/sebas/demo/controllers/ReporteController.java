package com.sebas.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sebas.demo.dto.EnvioDTO;
import com.sebas.demo.dto.SedeDTO;
import com.sebas.demo.dto.SocioDTO;
import com.sebas.demo.dto.VoluntarioDTO;
import com.sebas.demo.services.ServiceEnvio;
import com.sebas.demo.services.ServiceSede;
import com.sebas.demo.services.ServiceSocio;
import com.sebas.demo.services.ServiceVoluntario;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("/reportes")
@AllArgsConstructor
public class ReporteController {
    
    @Autowired
    private ServiceSocio serviceSocio;

    @Autowired
    private ServiceSede serviceSede;

    @Autowired
    private ServiceVoluntario serviceVoluntario;

    @Autowired
    private ServiceEnvio serviceEnvio;

    @GetMapping("/socios")
    public ResponseEntity<List<SocioDTO>> reportesSocios() {
        List<SocioDTO> findAll = serviceSocio.findAll();
        if (findAll == null || findAll.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(findAll);
        }
    }

    @GetMapping("/sedes")
    public ResponseEntity<List<SedeDTO>> reportesSedes(){
        List<SedeDTO> findAll = serviceSede.findAll();
        if (findAll == null || findAll.isEmpty()) {
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(findAll);
        }
        
    }

    @GetMapping("/voluntarios")
    public ResponseEntity<List<VoluntarioDTO>> reportesVoluntarios() {
        List<VoluntarioDTO> findAll = serviceVoluntario.findAll();
        if (findAll == null || findAll.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(findAll);
        }
    }

    @GetMapping("/envios")
    public ResponseEntity<List<EnvioDTO>> reportesEnvios() {
        List<EnvioDTO> envios = serviceEnvio.findAll();
        if (envios.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(envios);
        }
    }

}
