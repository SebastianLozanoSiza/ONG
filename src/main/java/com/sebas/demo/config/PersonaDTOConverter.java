package com.sebas.demo.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sebas.demo.dto.PersonaDTO;
import com.sebas.demo.repositories.entities.Persona;

@Component
public class PersonaDTOConverter {

    @Autowired
    private ModelMapper dbm;

    @Autowired
    public PersonaDTOConverter(ModelMapper modelMapper) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        this.dbm = modelMapper;
    }

    public PersonaDTO convertPersonaDTO(Persona persona){
        PersonaDTO personaDTO = dbm.map(persona, PersonaDTO.class);
        return personaDTO;
    }

    public Persona convertPersonaEntity(PersonaDTO personaDTO){
        return dbm.map(personaDTO, Persona.class);
    }
    
}
