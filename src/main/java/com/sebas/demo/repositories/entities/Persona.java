package com.sebas.demo.repositories.entities;


import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.sebas.demo.resources.TipoRol;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "personas")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Persona implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotEmpty(message = "La cedula no puede estar vacia")
    @Column(nullable = false, unique = true)
    private String cedula;

    @NotEmpty(message = "No puede estar vacio")
    @Column(nullable = false)
    private String nombre;

    @NotEmpty(message = "No puede estar vacio")
    @Column(nullable = false)
    private String apellido;

    @Email(message = "Formato incorrecto")
    @Column(nullable = false, unique = true)
    private String email;

    @NotEmpty(message = "No puede estar vacio")
    @Column(nullable = false)
    private String telefono;
    
    @Column(name = "pwd")
    @JsonIgnore
    private String contraseña;

    @Enumerated(EnumType.ORDINAL)
    @JsonIgnore
    private TipoRol rol;

    @JsonIgnore
    @OneToOne(mappedBy = "director", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Sede sede;

    @JsonIgnore
    @OneToOne(mappedBy = "persona", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Socio socio;

    @JsonIgnore
    @OneToOne(mappedBy = "persona", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Voluntario voluntario;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_persona")
    private List<Roles> roles;
}
