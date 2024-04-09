package com.sebas.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SedeDTO {

    private Long id;
    private String nombreSede;
    private String direccion;

    private Long ciudadId;
    private String nombreCiudad;
    
    private Long directorId;
    private String nombreDirector;
    
}
