package com.sebas.demo.dto;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SocioDTO {

    private Long id;
    private Date fechaPago;
    private String cuentaBancaria;
    private String nombreCuota;
    private Float precio;
    private String cedula;
    private String nombrePersona;
    private String email;
    private String telefono;
    private String nombreSede;   
}
