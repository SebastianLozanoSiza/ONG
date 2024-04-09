package com.sebas.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RefugioDTO {
    
    private Long id;
    private String nombreRefugio;
    private Long ciudadId;
    private String nombreCiudad;
}
