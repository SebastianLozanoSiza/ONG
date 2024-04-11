package com.sebas.demo.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sebas.demo.config.SocioDTOConverter;
import com.sebas.demo.dto.SocioDTO;
import com.sebas.demo.repositories.RepositoryPersona;
import com.sebas.demo.repositories.RepositorySocio;
import com.sebas.demo.repositories.entities.Persona;
import com.sebas.demo.repositories.entities.Socio;
import com.sebas.demo.services.ServiceSocio;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@Tag(name = "API de socios", description = "Esta api server tiene toda la funcionalidad de las socios")
@RestController
@RequestMapping("/socios")
@AllArgsConstructor
public class SocioController {

    private ServiceSocio serviceSocio;

    private RepositorySocio repositorySocio;

    private RepositoryPersona repositoryPersona;

    private SocioDTOConverter convert;

    @Operation(description = "Retorna todos los datos de los socios", summary = "Return 204 si no hay registros")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Exito"),
            @ApiResponse(responseCode = "500", description = "Internal error") })
    @GetMapping("/")
    public ResponseEntity<List<SocioDTO>> findAll() {
        List<SocioDTO> findAll = serviceSocio.findAll();
        if (findAll == null || findAll.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(findAll);
        }
    }

    @Operation(description = "Retorna todos los datos de los socios filtrados por su id", summary = "Return 204 si no hay registros")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Exito"),
            @ApiResponse(responseCode = "500", description = "Internal error") })
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> findAllById(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        Socio socio = serviceSocio.findById(id);
        response.put("socio", socio);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/tipo-cuota/{nombreTipoCuota}")
    public ResponseEntity<List<SocioDTO>> findByTipoCuota(@PathVariable String nombreTipoCuota) {
        List<SocioDTO> socios = serviceSocio.findByTipoCuota(nombreTipoCuota);
        if (socios.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(socios);
        }
    }

    @Operation(description = "Añade un nuevo socio", summary = "Return 204 si no hay registros")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Exito"),
            @ApiResponse(responseCode = "500", description = "Internal error") })
    @PostMapping("/")
    public ResponseEntity<Map<String, Object>> save(@Valid @RequestBody SocioDTO socioDTO, BindingResult result) {
        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> "El campo " + err.getField() + " " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("errors", errors);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        try {
            // Convertir el DTO de socio a una entidad
            Socio socio = convert.convertSocioEntity(socioDTO);

            // Crear una nueva persona
            Persona persona = new Persona();
            persona.setCedula(socioDTO.getCedula());
            persona.setNombre(socioDTO.getNombrePersona());
            persona.setEmail(socioDTO.getEmail());
            persona.setTelefono(socioDTO.getTelefono());

            // Guardar la persona en el repositorio
            persona = repositoryPersona.save(persona);

            // Asignar la persona al socio
            socio.setPersona(persona);

            // Guardar el socio en el repositorio
            socio = repositorySocio.save(socio);

            // Convertir la entidad de socio a DTO y retornarla
            SocioDTO savedSocioDTO = convert.convertSocioDTO(socio);
            response.put("mensaje", "El socio ha sido creado con éxito");
            response.put("socio", savedSocioDTO);
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar el insert en la base de datos");
            response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @Operation(description = "Actualiza los datos de un socio por su id", summary = "Return 204 si no hay registros")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Exito"),
            @ApiResponse(responseCode = "500", description = "Internal error") })
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> update(@Valid @RequestBody Socio socio, BindingResult result,
            @PathVariable Long id) {

        Socio socioUpdate = null;

        Map<String, Object> response = new HashMap<>();

        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> "Field " + err.getField() + " " + err.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("errors", errors);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        try {

            socioUpdate = serviceSocio.update(id, socio);

        } catch (DataAccessException e) {
            response.put("message", "Error al realizar los inserts en la base de datos");
            response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }

        response.put("mensaje", "Socio actualizado exitosamente");
        response.put("socio", socioUpdate);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(description = "Elimina un socio por su id", summary = "Return 204 si no hay registros")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Exito"),
            @ApiResponse(responseCode = "500", description = "Internal error") })
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Long id) {

        Map<String, Object> response = new HashMap<>();
        try {
            serviceSocio.delete(id);
        } catch (DataAccessException e) {
            response.put("message", "Error al realizar los inserts en la base de datos");
            response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("message", "Socio eliminado exitosamente");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
