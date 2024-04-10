package com.sebas.demo.services;

import java.util.List;

import com.sebas.demo.dto.EnvioDTO;
import com.sebas.demo.dto.SedeDTO;
import com.sebas.demo.dto.SocioDTO;
import com.sebas.demo.dto.VoluntarioDTO;

public interface ServiceReportes {

    List<SocioDTO> reportesSocios();

    List<SedeDTO> reportesSedes();

    List<VoluntarioDTO> reportesVoluntarios();

    List<EnvioDTO> reportesEnvios();
    
}
