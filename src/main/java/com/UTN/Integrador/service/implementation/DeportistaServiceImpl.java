package com.UTN.Integrador.service.implementation;


import com.UTN.Integrador.controller.DeportistaController;
import com.UTN.Integrador.domain.Deportista;
import com.UTN.Integrador.repository.DeportistaRepository;
import com.UTN.Integrador.service.DeportistaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import javax.xml.transform.OutputKeys;

import java.util.List;

import static com.UTN.Integrador.utils.EntityURLBuilder.buildURL;

@Service
public class DeportistaServiceImpl implements DeportistaService {
    private String path = "deportista";
    @Autowired
    private DeportistaRepository deportistaRepository;

    public ResponseEntity addDeportista(Deportista deportista){
        if(findByNameAndLastName(deportista.getName(), deportista.getLastName()).getBody() == null){
            Deportista D = deportistaRepository.save(deportista);
            return ResponseEntity.status(HttpStatus.CREATED).location(buildURL(path, D.getId().toString())).build();
        }else{
            throw new HttpClientErrorException(HttpStatus.CONFLICT, "deportist already exist");
        }

    }

    public ResponseEntity<List<Deportista>> getAll(){
        List<Deportista> deportistaList = deportistaRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(deportistaList);
    }

    public ResponseEntity<Deportista> getById(Integer deportistaId){
        Deportista deportista = deportistaRepository.findById(deportistaId).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NO_CONTENT, "This Deportista not exist"));

        return ResponseEntity.status(HttpStatus.OK).body(deportista);
    }

    public ResponseEntity<Deportista> findByNameAndLastName(String name, String lastName){
        Deportista deportista = deportistaRepository.findByNameAndLastName(name, lastName);

        if (deportista == null){
            return  ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }else{
            return ResponseEntity.status(HttpStatus.OK).body(deportista);
        }

    }


}
