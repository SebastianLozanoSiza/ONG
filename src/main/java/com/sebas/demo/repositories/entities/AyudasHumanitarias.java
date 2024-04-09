package com.sebas.demo.repositories.entities;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "ayudas_humanitarias")
public class AyudasHumanitarias implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "id_envio")
    private Envio envio;

    @ManyToOne()
    @JoinColumn(name = "id_ocupacion")
    private Ocupacion ocupacion;

    @NotEmpty(message = "No puede estar vacio")
    @Column(nullable = false)
    private Long cantidad;

    @ManyToMany
    @JoinTable(
        name = "ayuda_voluntario", 
        joinColumns = @JoinColumn(name = "id_ayuda"), 
        inverseJoinColumns = @JoinColumn(name = "id_voluntarioh"))
    private List<VoluntarioH> voluntarios;

}
