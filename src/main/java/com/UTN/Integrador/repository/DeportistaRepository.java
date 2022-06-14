package com.UTN.Integrador.repository;

import com.UTN.Integrador.domain.Deportista;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

public interface DeportistaRepository extends JpaRepository<Deportista, Integer> {


    Deportista findByNameAndLastName(String name, String lastName);
}
