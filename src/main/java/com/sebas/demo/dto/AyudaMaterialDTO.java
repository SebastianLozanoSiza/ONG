package com.sebas.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AyudaMaterialDTO {
    
    private Long id;
    private String nombreProducto;
    private String tipoProducto;
    private String tipoMaterial;
    private Float cantidad;
}
