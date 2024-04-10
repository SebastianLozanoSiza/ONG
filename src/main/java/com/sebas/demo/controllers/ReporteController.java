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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@Tag(name = "API de reportes", description = "Esta api server tiene toda la funcionalidad de los reportes")
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

    @Operation(description = "Retorna todos los datos de los socios", summary ="Return 204 si no hay registros")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",description = "Exito"),
    @ApiResponse(responseCode = "500", description = "Internal error")})
    @GetMapping("/socios")
    public ResponseEntity<List<SocioDTO>> reportesSocios() {
        List<SocioDTO> findAll = serviceSocio.findAll();
        if (findAll == null || findAll.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(findAll);
        }
    }

    @Operation(description = "Retorna todos los datos de las sedes", summary ="Return 204 si no hay registros")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",description = "Exito"),
    @ApiResponse(responseCode = "500", description = "Internal error")})
    @GetMapping("/sedes")
    public ResponseEntity<List<SedeDTO>> reportesSedes(){
        List<SedeDTO> findAll = serviceSede.findAll();
        if (findAll == null || findAll.isEmpty()) {
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(findAll);
        }
        
    }

    @Operation(description = "Retorna todos los datos de los voluntarios", summary ="Return 204 si no hay registros")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",description = "Exito"),
    @ApiResponse(responseCode = "500", description = "Internal error")})
    @GetMapping("/voluntarios")
    public ResponseEntity<List<VoluntarioDTO>> reportesVoluntarios() {
        List<VoluntarioDTO> findAll = serviceVoluntario.findAll();
        if (findAll == null || findAll.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(findAll);
        }
    }

    @Operation(description = "Retorna todos los datos de los envios", summary ="Return 204 si no hay registros")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",description = "Exito"),
    @ApiResponse(responseCode = "500", description = "Internal error")})
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
