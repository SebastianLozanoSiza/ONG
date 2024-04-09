package com.sebas.demo.config;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.sebas.demo.dto.AyudaHumanitariaDTO;
import com.sebas.demo.dto.AyudaMaterialDTO;
import com.sebas.demo.dto.EnvioDTO;
import com.sebas.demo.dto.SedeDTO;
import com.sebas.demo.repositories.entities.Envio;

@Component
public class EnvioDTOConverter {

    @Autowired
    private ModelMapper dbm;

    @Autowired
    public EnvioDTOConverter(ModelMapper modelMapper) {
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        this.dbm = modelMapper;
    }

    public EnvioDTO convertToDTO(Envio envio) {
        EnvioDTO envioDTO = dbm.map(envio, EnvioDTO.class);
        if (envio.getRefugio() != null) {
            envioDTO.setRefugioId(envio.getRefugio().getId());
            envioDTO.setNombreRefugio(envio.getRefugio().getNombre());
        }
        if (envio.getAyudas() != null) {
            List<AyudaHumanitariaDTO> ayudasHumanitariasDTO = envio.getAyudas().stream()
                    .map(ayuda -> new AyudaHumanitariaDTO(ayuda.getId(), ayuda.getOcupacion().getNombre(),
                            ayuda.getCantidad()))
                    .collect(Collectors.toList());
            envioDTO.setAyudasHumanitarias(ayudasHumanitariasDTO);
        }
        if (envio.getAyudasMateriales() != null) {
            List<AyudaMaterialDTO> ayudasMaterialesDTO = envio.getAyudasMateriales().stream()
                    .map(ayuda -> new AyudaMaterialDTO(ayuda.getId(), ayuda.getProducto().getNombre(),
                            ayuda.getProducto().getTipo().toString(), ayuda.getTipoMaterial(), ayuda.getCantidad()))
                    .collect(Collectors.toList());
            envioDTO.setAyudasMateriales(ayudasMaterialesDTO);
        }
        if (envio.getSedes() != null) {
            List<SedeDTO> sedesDTO = envio.getSedes().stream()
                    .map(sede -> new SedeDTO(
                            sede.getId(),
                            sede.getNombreSede(),
                            sede.getDireccion(),
                            sede.getCiudad().getId(),
                            sede.getCiudad().getNombre(),
                            sede.getDirector().getId(),
                            sede.getDirector().getNombre()))
                    .collect(Collectors.toList());
            envioDTO.setSedes(sedesDTO);
        }

        return envioDTO;
    }

    public Envio convertToEntity(EnvioDTO envioDTO) {
        return dbm.map(envioDTO, Envio.class);
    }

}
