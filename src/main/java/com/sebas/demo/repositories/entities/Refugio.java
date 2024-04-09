package com.sebas.demo.repositories.entities;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "refugios")
public class Refugio implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "No puede estar vacio")
    @Column(nullable = false) 
    private String nombre;

    @JsonIgnoreProperties(value={"refugios", "hibernateLazyInitializer", "handler"}, allowSetters=true)
    @JoinColumn(name = "id_ciudad")
    @ManyToOne(fetch = FetchType.LAZY)
    private Ciudad ciudad;

    @JsonIgnore
    @OneToMany(mappedBy = "refugio", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Envio> envios;
    
}
