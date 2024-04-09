package com.sebas.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AyudaHumanitariaDTO {
    
    private Long id;
    private String ocupacion;
    private Long cantidadVoluntarios;
}
