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

import com.sebas.demo.dto.SedeDTO;
import com.sebas.demo.repositories.entities.Sede;
import com.sebas.demo.services.ServiceSede;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

@RestController
@Tag(name = "API de sedes", description = "Esta api server tiene toda la funcionalidad de las sedes")
@RequestMapping("/sedes")
@AllArgsConstructor
public class SedeController {
    
    private ServiceSede serviceSede;

    @Operation(description = "Retorna todos los datos de las sedes", summary ="Return 204 si no hay registros")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",description = "Exito"),
    @ApiResponse(responseCode = "500", description = "Internal error")})
    @GetMapping("/")
    public ResponseEntity<List<SedeDTO>> findAll(){
        List<SedeDTO> findAll = serviceSede.findAll();
        if (findAll == null || findAll.isEmpty()) {
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(findAll);
        }
        
    }

    @Operation(description = "Retorna todos los datos de las sedes filtrados por su id", summary ="Return 204 si no hay registros")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",description = "Exito"),
    @ApiResponse(responseCode = "500", description = "Internal error")})
    @GetMapping("/{id}")
    public ResponseEntity<Map<String,Object>> findAllById(@PathVariable Long id){
        Map<String,Object> response = new HashMap<>();
        Sede sede = serviceSede.findById(id);
        response.put("sede", sede);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(description = "Añade las sedes", summary ="Return 204 si no hay registros")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",description = "Exito"),
    @ApiResponse(responseCode = "500", description = "Internal error")})
    @PostMapping("/")
    public ResponseEntity<Map<String, Object>> save(@Valid @RequestBody SedeDTO sede, BindingResult result){
        SedeDTO sedeNew = null;

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
            sedeNew = serviceSede.save(sede);
        } catch (DataAccessException e) {
            response.put("message", "Error al realizar los inserts en la base de datos de SedeDTO");
            response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("message", "Sede creada exitosamente");
        response.put("sede", sedeNew);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(description = "Actualiza los datos de cada sede filtrada por su id", summary ="Return 204 si no hay registros")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",description = "Exito"),
    @ApiResponse(responseCode = "500", description = "Internal error")})
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> update(@Valid @RequestBody Sede sede, BindingResult result,
            @PathVariable Long id) {

        Sede sedeUpdate = null;

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

            sedeUpdate = serviceSede.update(id, sede);

        } catch (DataAccessException e) {
            response.put("message", "Error al realizar los inserts en la base de datos");
            response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);

        }

        response.put("mensaje", "Sede actualizada existosamente");
        response.put("sede", sedeUpdate);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(description = "Elimina la sede seleccionada por su id", summary ="Return 204 si no hay registros")
    @ApiResponses(value = {@ApiResponse(responseCode = "200",description = "Exito"),
    @ApiResponse(responseCode = "500", description = "Internal error")})
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> delete(@PathVariable Long id) {

        Map<String, Object> response = new HashMap<>();
        try {
            serviceSede.delete(id);
        } catch (DataAccessException e) {
            response.put("message", "Error al realizar los inserts en la base de datos");
            response.put("error", e.getMessage().concat(":").concat(e.getMostSpecificCause().getMessage()));
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("message", "Sede elimnada exitosamente");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
