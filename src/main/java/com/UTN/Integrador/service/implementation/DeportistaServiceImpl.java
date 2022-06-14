package com.UTN.Integrador.service.implementation;


import com.UTN.Integrador.controller.DeportistaController;
import com.UTN.Integrador.domain.Basketbolista;
import com.UTN.Integrador.domain.Deportista;
import com.UTN.Integrador.domain.Futbolista;
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

    public ResponseEntity addDeportista(Deportista deportista) {
        if (findByNameAndLastName(deportista.getName(), deportista.getLastName()).getBody() == null) {
            Deportista D = deportistaRepository.save(deportista);
            return ResponseEntity.status(HttpStatus.CREATED).location(buildURL(path, D.getId().toString())).build();
        } else {
            throw new HttpClientErrorException(HttpStatus.CONFLICT, "deportist already exist");
        }

    }

    public ResponseEntity<List<Deportista>> getAll() {
        List<Deportista> deportistaList = deportistaRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(deportistaList);
    }

    public ResponseEntity<Deportista> getById(Integer deportistaId) {
        Deportista deportista = deportistaRepository.findById(deportistaId).orElseThrow(() -> new HttpClientErrorException(HttpStatus.CONFLICT, "This Deportista not exist"));

        return ResponseEntity.status(HttpStatus.OK).body(deportista);
    }

    public ResponseEntity<Deportista> findByNameAndLastName(String name, String lastName) {
        Deportista deportista = deportistaRepository.findByNameAndLastName(name, lastName);

        if (deportista == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        } else {
            return ResponseEntity.status(HttpStatus.OK).body(deportista);
        }

    }

    public ResponseEntity<Integer> getPointsByDeportistaId(Integer deportistaId) {
        Deportista deportista = getById(deportistaId).getBody();
        if (deportista.getClass() == Futbolista.class) {
            return ResponseEntity.status(HttpStatus.OK).body(((Futbolista) deportista).getTotalGoals());
        } else if (deportista.getClass() == Basketbolista.class) {
            return ResponseEntity.status(HttpStatus.OK).body(((Basketbolista) deportista).getPoints());
        }

        return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
    }

    public ResponseEntity addDeportistaStats(Integer deportistaId, String type, Integer quantity) {
        Deportista deportista = getById(deportistaId).getBody();
        deportista = addStats(deportista, type, quantity);
        deportista = deportistaRepository.save(deportista);
        return ResponseEntity.status(HttpStatus.OK).location(buildURL(path, deportista.getId().toString())).build();
    }

    private Deportista addStats(Deportista deportista, String type, Integer quantity) {
        if (deportista.getClass() == Futbolista.class) {
            switch (type) {
                case "penalty":
                    ((Futbolista) deportista).setPenaltyGoals(((Futbolista) deportista).getPenaltyGoals() + quantity);
                    ((Futbolista) deportista).setTotalGoals(((Futbolista) deportista).getTotalGoals() + quantity);
                    break;
                case "head":
                    ((Futbolista) deportista).setHeadGoals(((Futbolista) deportista).getHeadGoals() + quantity);
                    ((Futbolista) deportista).setTotalGoals(((Futbolista) deportista).getTotalGoals() + quantity);
                    break;
                case "free kick":
                    ((Futbolista) deportista).setFreeKickGoals(((Futbolista) deportista).getFreeKickGoals() + quantity);
                    ((Futbolista) deportista).setTotalGoals(((Futbolista) deportista).getTotalGoals() + quantity);
                    break;
                case "general":
                    ((Futbolista) deportista).setTotalGoals(((Futbolista) deportista).getTotalGoals() + quantity);
                    break;
                default:
                    throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Invalid stats type");
            }
        } else if (deportista.getClass() == Basketbolista.class) {
            switch (type) {
                case "point":
                    ((Basketbolista) deportista).setPoints(((Basketbolista) deportista).getPoints() + quantity);
                    break;
                case "rebound":
                    ((Basketbolista) deportista).setRebounds(((Basketbolista) deportista).getRebounds() + quantity);
                    break;
                default:
                    throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Invalid stats type");
            }
        }
        return deportista;
    }
}
