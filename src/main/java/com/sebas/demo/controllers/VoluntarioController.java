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

import com.sebas.demo.config.VoluntarioDTOConverter;
import com.sebas.demo.dto.SocioDTO;
import com.sebas.demo.dto.VoluntarioDTO;
import com.sebas.demo.repositories.RepositoryPersona;
import com.sebas.demo.repositories.RepositoryVoluntario;
import com.sebas.demo.repositories.entities.Persona;
import com.sebas.demo.repositories.entities.Voluntario;
import com.sebas.demo.services.ServiceVoluntario;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@Tag(name = "API de voluntarios", description = "Esta api server tiene toda la funcionalidad de las voluntarios")
@RestController
@RequestMapping("/voluntarios")
@AllArgsConstructor
public class VoluntarioController {

    private ServiceVoluntario serviceVoluntario;

    private RepositoryVoluntario repositoryVoluntario;

    private RepositoryPersona repositoryPersona;

    private VoluntarioDTOConverter convert;

    @Operation(description = "Retorna todos los datos de los voluntarios", summary = "Return 204 si no hay registros")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Exito"),
            @ApiResponse(responseCode = "500", description = "Internal error") })
    @GetMapping("/")
    public ResponseEntity<List<VoluntarioDTO>> findAll() {
        List<VoluntarioDTO> findAll = serviceVoluntario.findAll();
        if (findAll == null || findAll.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(findAll);
        }
    }

    @Operation(description = "Retorna todos los datos de los voluntarios por su id", summary = "Return 204 si no hay registros")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Exito"),
    @ApiResponse(responseCode = "500", description = "Internal error") })
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> findAllById(@PathVariable Long id) {
        Map<String, Object> response = new HashMap<>();
        Voluntario voluntario = serviceVoluntario.findById(id);
        response.put("voluntario", voluntario);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(description = "Añade un nuevo voluntario", summary = "Return 204 si no hay registros")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Exito"),
            @ApiResponse(responseCode = "500", description = "Internal error") })
    @PostMapping("/")
    public ResponseEntity<Map<String, Object>> save(@Valid @RequestBody VoluntarioDTO voluntarioDTO,
            BindingResult result) {
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
            // Convertir el DTO de voluntario a una entidad
            Voluntario voluntario = convert.convertVoluntarioEntity(voluntarioDTO);

            // Crear una nueva persona
            Persona persona = new Persona();
            persona.setCedula(voluntarioDTO.getCedula());
            persona.setNombre(voluntarioDTO.getNombrePersona());
            persona.setEmail(voluntarioDTO.getEmail());
            persona.setTelefono(voluntarioDTO.getTelefono());

            // Guardar la persona en el repositorio
            persona = repositoryPersona.save(persona);

            // Asignar la persona al voluntario
            voluntario.setPersona(persona);

            // Guardar el voluntario en el repositorio
            voluntario = repositoryVoluntario.save(voluntario);

            // Convertir la entidad de voluntario a DTO y retornarla
            VoluntarioDTO savedVoluntarioDTO = convert.convertVoluntarioDTO(voluntario);
            response.put("mensaje", "El voluntario ha sido creado con éxito");
            response.put("voluntario", savedVoluntarioDTO);
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (DataAccessException e) {
            response.put("mensaje", "Error al realizar el insert en la base de datos");
            response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @Operation(description = "Actualiza los datos de un voluntario por su id", summary = "Return 204 si no hay registros")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Exito"),
            @ApiResponse(responseCode = "500", description = "Internal error") })
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> update(@Valid @RequestBody Voluntario voluntario, BindingResult result,
            @PathVariable Long id) {

        Voluntario voluntarioUpdate = null;

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

            voluntarioUpdate = serviceVoluntario.update(id, voluntario);

        } catch (DataAccessException e) {
            response.put("message", "Error al realizar los inserts en la base de datos");
            response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }

        response.put("mensaje", "Voluntario actualizado exitosamente");
        response.put("voluntario", voluntarioUpdate);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(description = "Elimina un voluntario por su id", summary = "Return 204 si no hay registros")
    @ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Exito"),
            @ApiResponse(responseCode = "500", description = "Internal error") })
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Long id) {

        Map<String, Object> response = new HashMap<>();
        try {
            serviceVoluntario.delete(id);
        } catch (DataAccessException e) {
            response.put("message", "Error al realizar los inserts en la base de datos");
            response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("message", "Voluntario eliminado exitosamente");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
