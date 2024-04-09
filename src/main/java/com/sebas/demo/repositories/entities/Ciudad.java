package com.sebas.demo.repositories.entities;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="ciudades")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Ciudad implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "No puede estar vacio")
    @Column(nullable = false, unique = true)
    private String nombre;

    @JsonIgnore
    @OneToMany(mappedBy = "ciudad", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Refugio> refugios;

    @JsonIgnore
    @OneToOne(mappedBy = "ciudad", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Sede sede;
}
