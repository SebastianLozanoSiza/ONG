package com.sebas.demo.repositories.entities;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
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
@Schema(name = "Socio", description = "Representa al socio en el modelo de base datos")
public class Socio implements Serializable{
    
    @Schema(name = "id", required = true, example = "1", defaultValue = "1", description = "Representa el indicador del socio") 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(name = "Fecha_Pago", required = true, example = "2024-03-28", defaultValue = "2024-03-28", description = "Representa la fecha de pago del socio") 
    @Column(name="fecha_Pago")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date fechaPago;
    
    @Schema(name = "cuenta_bancaria", required = true, example = "Bancolombia", defaultValue = "Bancolombia", description = "Representa la cuenta bancaria del socio") 
    @NotEmpty(message = "No puede estar vacia")
    @Column(nullable = false, name = "cuenta_bancaria")
    private String cuentaBancaria;

    @Schema(name = "Tipo_cuota", required = true, example = "Minima", defaultValue = "Minima", description = "Representa el tipo de cuota del socio") 
    @ManyToOne()
    @JoinColumn(name = "id_tipo_cuota")
    private TipoCuota tipoCuota;

    @Schema(name = "Nombre_Socio", required = true, example = "Pedro", defaultValue = "Pedro", description = "Representa el nombre del socio") 
    @JoinColumn(name = "id_persona")
    @OneToOne(fetch = FetchType.LAZY)
    private Persona persona;

    @Schema(name = "Nombre_Sede", required = true, example = "Refugio de Inmigrantes", defaultValue = "Refugio de Inmigrantes", description = "Representa la sede que tiene el socio") 
    @ManyToOne()
    @JoinColumn(name = "id_sede")
    private Sede sede;
}
