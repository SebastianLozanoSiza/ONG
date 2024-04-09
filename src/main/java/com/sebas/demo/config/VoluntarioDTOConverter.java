package com.sebas.demo.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sebas.demo.dto.VoluntarioDTO;
import com.sebas.demo.repositories.entities.Voluntario;

@Component
public class VoluntarioDTOConverter {

    @Autowired
    private ModelMapper dbm;

    @Autowired
    public VoluntarioDTOConverter(ModelMapper modelMapper) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        this.dbm = modelMapper;
    }

    public VoluntarioDTO convertVoluntarioDTO(Voluntario voluntario) {
        VoluntarioDTO voluntarioDTO = dbm.map(voluntario, VoluntarioDTO.class);
        if (voluntario.getPersona() != null) {
            voluntarioDTO.setPersonaId(voluntario.getPersona().getId());
            voluntarioDTO.setCedula(voluntario.getPersona().getCedula());
            voluntarioDTO.setNombrePersona(voluntario.getPersona().getNombre() + " " + voluntario.getPersona().getApellido());
            voluntarioDTO.setEmail(voluntario.getPersona().getEmail());
            voluntarioDTO.setTelefono(voluntario.getPersona().getTelefono());
        }
        if (voluntario.getVoluntario() != null) {
            voluntarioDTO.setVoluntarioHId(voluntario.getVoluntario().getId());
            voluntarioDTO.setDisponibilidad(voluntario.getVoluntario().isDisponibilidad());
            voluntarioDTO.setNumTrabajos(voluntario.getVoluntario().getNumTrabajos());
            voluntarioDTO.setOcupacionId(voluntario.getVoluntario().getOcupacion().getId());
            voluntarioDTO.setOcupacion(voluntario.getVoluntario().getOcupacion().getNombre());
        }
        if(voluntario.getSedes() != null){
            voluntarioDTO.setSedeId(voluntario.getSedes().getId());
            voluntarioDTO.setNombreSede(voluntario.getSedes().getNombreSede());
        }
        return voluntarioDTO;
    }

    public Voluntario convertVoluntarioEntity(VoluntarioDTO voluntarioDTO){
        return dbm.map(voluntarioDTO, Voluntario.class);
    }
    
}
