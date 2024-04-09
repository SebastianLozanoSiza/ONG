package com.sebas.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonaDTO {

    private Long id;
    private String cedula;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;

}
