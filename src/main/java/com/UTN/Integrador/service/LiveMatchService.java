package com.UTN.Integrador.service;

import com.UTN.Integrador.domain.Matches;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface LiveMatchService {

    ResponseEntity<Matches> getLiveMatch() throws IOException, InterruptedException;

}
