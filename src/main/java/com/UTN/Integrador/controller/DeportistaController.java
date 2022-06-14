package com.UTN.Integrador.controller;

import com.UTN.Integrador.domain.Deportista;
import com.UTN.Integrador.service.DeportistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/deportista")
public class DeportistaController {

    private DeportistaService deportistaService;

    @Autowired
    public DeportistaController (DeportistaService deportistaService){
        this.deportistaService = deportistaService;
    }

    @PostMapping("/") //Save
    public ResponseEntity addDeportista(@RequestBody Deportista deportista){
        return deportistaService.addDeportista(deportista);
    }

    @GetMapping("/")
    public ResponseEntity<List<Deportista>> getAll(){
        return deportistaService.getAll();
    }

    @GetMapping("/{deportistaId}")
    public ResponseEntity<Deportista> getById(@PathVariable Integer deportistaId){
        return deportistaService.getById(deportistaId);
    }


    @GetMapping("/findByName/")
    public ResponseEntity<Deportista> getById(@RequestParam String name, @RequestParam String lastName){
        return deportistaService.findByNameAndLastName(name,lastName);
    }

    @GetMapping("/{deportistaId}/points")
    public ResponseEntity<Integer> getPointsByDeportistaId(@PathVariable Integer deportistaId){
        return deportistaService.getPointsByDeportistaId(deportistaId);
    }

    @PostMapping("/{deportistaId}/addStats/")
    public ResponseEntity<Integer> addDeportistStats(@PathVariable Integer deportistaId, @RequestParam String type, @RequestParam Integer quantity){
        return deportistaService.addDeportistaStats(deportistaId, type, quantity);
    }




}
