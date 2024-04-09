package com.sebas.demo.repositories.entities;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
@Table(name = "socios")
public class Socio implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="fecha_Pago")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date fechaPago;
    
    @NotEmpty(message = "No puede estar vacia")
    @Column(nullable = false, name = "cuenta_bancaria")
    private String cuentaBancaria;

    @ManyToOne()
    @JoinColumn(name = "id_tipo_cuota")
    private TipoCuota tipoCuota;

    @JoinColumn(name = "id_persona")
    @OneToOne(fetch = FetchType.LAZY)
    private Persona persona;

    @ManyToOne()
    @JoinColumn(name = "id_sede")
    private Sede sede;
}
