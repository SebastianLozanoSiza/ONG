package com.sebas.demo.dto;

import java.util.Date;
import java.util.List;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnvioDTO {

    private Long id;
    private Date fechaSalida;
    private Long refugioId;
    private String nombreRefugio;
    private List<AyudaHumanitariaDTO> ayudasHumanitarias;
    private List<AyudaMaterialDTO> ayudasMateriales;
    private List<SedeDTO> sedes;
    
}
