package com.sebas.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoluntarioDTO {

    private Long id;

    private Long personaId;
    private String cedula;
    private String nombrePersona;
    private String email;
    private String telefono;

    private Long voluntarioHId;
    private boolean disponibilidad;
    private int numTrabajos;
    
    private Long ocupacionId;
    private String ocupacion;

    private Long sedeId;
    private String nombreSede;
}
