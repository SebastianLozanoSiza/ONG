package com.sebas.demo.repositories.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "envios")
public class Envio implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="fecha_salida")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date fechaSalida;

    @JsonIgnoreProperties(value={"envios", "hibernateLazyInitializer", "handler"}, allowSetters=true)
    @ManyToMany
    @JoinTable(
        name = "envio_sede", 
        joinColumns = @JoinColumn(name = "id_envio"), 
        inverseJoinColumns = @JoinColumn(name = "id_sede"))
    private List<Sede> sedes;

    @JsonIgnoreProperties(value={"envios", "hibernateLazyInitializer", "handler"}, allowSetters=true)
    @ManyToOne()
    @JoinColumn(name = "id_refugio")
    private Refugio refugio;

    @JsonIgnore
    @OneToMany(mappedBy = "envio", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AyudasHumanitarias> ayudas;

    @JsonIgnore
    @OneToMany(mappedBy = "envio", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AyudasMateriales> ayudasMateriales;
    
}
