package com.sebas.demo.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sebas.demo.dto.SedeDTO;
import com.sebas.demo.repositories.entities.Sede;

@Component
public class SedeDTOConverter {

    @Autowired
    private ModelMapper dbm;

    @Autowired
    public SedeDTOConverter(ModelMapper modelMapper) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        this.dbm = modelMapper;
    }

    public SedeDTO convertSedeDTO(Sede sede){
        SedeDTO sedeDTO = dbm.map(sede, SedeDTO.class);
        if(sede.getDirector() != null){
            sedeDTO.setDirectorId(sede.getDirector().getId());
            sedeDTO.setNombreDirector(sede.getDirector().getNombre() + " " + sede.getDirector().getApellido());
        }
        if(sede.getCiudad() != null){
            sedeDTO.setCiudadId(sede.getCiudad().getId());
            sedeDTO.setNombreCiudad(sede.getCiudad().getNombre());
        }
        return sedeDTO;
    }

    public Sede convertSedeEntity(SedeDTO sedeDTO){
        return dbm.map(sedeDTO, Sede.class);   
    }


    
}
