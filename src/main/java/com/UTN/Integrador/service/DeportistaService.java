package com.UTN.Integrador.service;

import com.UTN.Integrador.domain.Deportista;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface DeportistaService {
    ResponseEntity addDeportista(Deportista deportista);

    ResponseEntity<List<Deportista>> getAll();

    ResponseEntity<Deportista> getById(Integer deportistaId);
}
