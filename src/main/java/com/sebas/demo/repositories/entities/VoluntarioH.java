package com.sebas.demo.repositories.entities;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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
@Table(name = "voluntarios_h")
public class VoluntarioH implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "No puede estar vacio")
    @Column(nullable = false)
    private boolean disponibilidad;

    @Column(nullable = false, name = "num_trabajos")
    private int numTrabajos;

    @JoinColumn(name = "id_ocupacion")
    @ManyToOne(fetch = FetchType.LAZY)
    private Ocupacion ocupacion;

    @JoinColumn(name = "id_voluntario")
    @OneToOne(fetch = FetchType.LAZY)
    private Voluntario voluntario;

    @ManyToMany(mappedBy = "voluntarios", cascade = CascadeType.ALL)
    private List<AyudasHumanitarias> ayudas_h;
}
