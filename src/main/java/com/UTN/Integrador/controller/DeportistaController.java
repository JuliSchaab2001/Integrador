package com.UTN.Integrador.controller;

import com.UTN.Integrador.domain.Deportista;
import com.UTN.Integrador.service.DeportistaService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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

    @ApiOperation(value = "Add Deportista", notes = "Add @type, it can be futbolista or basketbolista")
    @PostMapping("/") //Save
    public ResponseEntity addDeportista(@RequestBody Deportista deportista){
        return deportistaService.addDeportista(deportista);
    }

    @GetMapping("/")
    @ApiOperation(value = "Return all Deportistas", produces = "application/json")
    public ResponseEntity<List<Deportista>> getAll(){
        return deportistaService.getAll();
    }

    @ApiOperation(value = "Return Deportista By id ", produces = "application/json")
    @GetMapping("/{deportistaId}")
    public ResponseEntity<Deportista> getById(@PathVariable @ApiParam(name = "deportistaId", value = "Deportista id", example = "1")  Integer deportistaId){
        return deportistaService.getById(deportistaId);
    }

    @ApiOperation(value = "Return deportista by Name and LastName", produces = "application/json")
    @GetMapping("/findByName/")
    public ResponseEntity<Deportista> getById(
            @ApiParam(name = "name", value= "Deportista name", example = "Lionel") @RequestParam String name
            ,@ApiParam(name = "lastName", value= "Deportista lastName", example = "Messi") @RequestParam String lastName){
        return deportistaService.findByNameAndLastName(name,lastName);
    }

    @ApiOperation(value = "returns points or goals from Deportista", produces = "Integer")
    @GetMapping("/{deportistaId}/points")
    public ResponseEntity<Integer> getPointsByDeportistaId( @ApiParam(name = "deportistaId", value = "Deportista id", example = "1") @PathVariable Integer deportistaId){
        return deportistaService.getPointsByDeportistaId(deportistaId);
    }

    @ApiOperation(value = "Add deportista Stast", notes = "type must be: {rebound,point,general,free kick,head,penalty}")
    @PostMapping("/{deportistaId}/addStats/")
    public ResponseEntity<Integer> addDeportistStats(
            @ApiParam(name = "deportistaId", value = "Deportista id", example = "1") @PathVariable Integer deportistaId,
            @ApiParam(name = "type", value = "point type", example = "Head") @RequestParam String type,
            @ApiParam(name = "quantity", value = "Points Quantity", example = "10") @RequestParam Integer quantity){
        return deportistaService.addDeportistaStats(deportistaId, type, quantity);
    }




}
