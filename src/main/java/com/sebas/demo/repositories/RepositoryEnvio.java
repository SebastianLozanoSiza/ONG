package com.sebas.demo.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.sebas.demo.repositories.entities.Envio;

public interface RepositoryEnvio extends CrudRepository<Envio, Long>{

    @Query("SELECT e FROM Envio e JOIN FETCH e.ayudas a JOIN FETCH a.ocupacion JOIN FETCH a.voluntarios JOIN FETCH e.refugio JOIN FETCH e.sedes WHERE e.id = :id")
    Optional<Envio> findByIdWithDetails(@Param("id") Long id);
    
}
