package com.sebas.demo.repositories.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "tipo_cuota")
public class TipoCuota {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "No puede estar vacio")
    @Column(nullable = false)
    private String nombre;

    @NotEmpty(message = "No puede estar vacio")
    @Column(nullable = false)
    private Float precio;

    @JsonIgnore
    @OneToMany(mappedBy = "tipoCuota", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Socio> socios;
    
}
