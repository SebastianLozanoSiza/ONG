package com.sebas.demo.repositories.entities;


import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sedes")
@Schema(name = "Sede", description = "Representa la sede en el modelo de base datos")
public class Sede implements Serializable {

    @Schema(name = "id", required = true, example = "1", defaultValue = "1", description = "Representa el indicador de la sede") 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "No puede estar vacio")
    @Column(nullable = false, name = "nombre_sede")
    @Schema(name = "nombre", required = true, example = "Refugio de Inmigrantes", defaultValue = "Refugio", description = "Representa el nombre que se le da a la sede") 
    private String nombreSede;

    @NotEmpty(message = "No puede estar vacio")
    @Column(nullable = false)
    @Schema(name = "direccion", required = true, example = "Transversal 22", defaultValue = "Transversal", description = "Representa la direccion de la sede") 
    private String direccion;

    @JsonIgnore
    @JoinColumn(name = "id_ciudad")
    @OneToOne(fetch = FetchType.LAZY)
    private Ciudad ciudad;

    @Schema(name = "director", required = true, example = "Fabrizio", defaultValue = "Fabrizio", description = "Representa el nombre del director de la sede") 
    @JsonIgnoreProperties(value={"sedes", "hibernateLazyInitializer", "handler"}, allowSetters=true)
    @JoinColumn(name = "id_director", unique = true)
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Persona director;

    @JsonIgnore
    @OneToMany(mappedBy = "sede", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Socio> socios;

    @JsonIgnore
    @OneToMany(mappedBy = "sedes", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Voluntario> voluntarios;

    @JsonIgnore
    @ManyToMany(mappedBy = "sedes", cascade = CascadeType.ALL)
    private List<Envio> envios;
}
