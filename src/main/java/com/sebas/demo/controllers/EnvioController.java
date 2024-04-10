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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;

@Tag(name = "API de envios", description = "Esta api server tiene toda la funcionalidad de los envios")
@RestController
@RequestMapping("/envios")
@AllArgsConstructor
public class EnvioController {

    @Autowired
    private ServiceEnvio serviceEnvio;

    @Autowired
    private EnvioDTOConverter convert;

    @Operation(description = "Retorna todos los datos de los envios", summary ="Return 204 si no hay registros")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",description = "Exito"),
    @ApiResponse(responseCode = "500", description = "Internal error")})
    @GetMapping("/")
    public ResponseEntity<List<EnvioDTO>> findAll() {
        List<EnvioDTO> envios = serviceEnvio.findAll();
        if (envios.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(envios);
        }
    }

    @Operation(description = "Retorna todos los datos de los envios filtrados por su id", summary ="Return 204 si no hay registros")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",description = "Exito"),
    @ApiResponse(responseCode = "500", description = "Internal error")})
    @GetMapping("/{id}")
    public ResponseEntity<EnvioDTO> findById(@PathVariable Long id) {
        Envio envio = serviceEnvio.findById(id);
        return ResponseEntity.ok(convert.convertToDTO(envio));
    }
    
}
