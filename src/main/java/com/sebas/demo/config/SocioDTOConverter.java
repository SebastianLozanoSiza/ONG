package com.sebas.demo.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sebas.demo.dto.SocioDTO;
import com.sebas.demo.repositories.entities.Socio;

@Component
public class SocioDTOConverter {
    
    @Autowired
    private ModelMapper dbm;

    @Autowired
    public SocioDTOConverter(ModelMapper modelMapper) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        this.dbm = modelMapper;
    }

    public SocioDTO convertSocioDTO(Socio socio) {
        SocioDTO socioDTO = dbm.map(socio, SocioDTO.class);
        if (socio.getTipoCuota() != null) {
            socioDTO.setNombreCuota(socio.getTipoCuota().getNombre());
            socioDTO.setPrecio(socio.getTipoCuota().getPrecio());
        }
        if (socio.getPersona() != null) {
            socioDTO.setCedula(socio.getPersona().getCedula());
            socioDTO.setNombrePersona(socio.getPersona().getNombre() + " " + socio.getPersona().getApellido());
            socioDTO.setEmail(socio.getPersona().getEmail());
            socioDTO.setTelefono(socio.getPersona().getTelefono());
        }
        if(socio.getSede() != null){
            socioDTO.setNombreSede(socio.getSede().getNombreSede());
        }
        return socioDTO;
    }

    public Socio convertSocioEntity(SocioDTO socioDTO){
        return dbm.map(socioDTO, Socio.class);

    }
}
