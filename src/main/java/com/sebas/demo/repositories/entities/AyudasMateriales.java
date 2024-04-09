package com.sebas.demo.repositories.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
@Table(name = "ayudas_materiales")
public class AyudasMateriales implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne()
    @JoinColumn(name = "id_envio")
    private Envio envio;

    @ManyToOne()
    @JoinColumn(name = "id_producto")
    private Producto producto;

    @NotEmpty(message = "El tipo de material no puede estar vacio")
    @Column(name = "tipo_material")
    private String tipoMaterial;

    @NotEmpty(message = "Cantidad no puede estar vacia")
    @Column(nullable = false)
    private Float cantidad;
}
