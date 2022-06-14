package com.UTN.Integrador.controller;

import com.UTN.Integrador.domain.Matches;
import com.UTN.Integrador.service.LiveMatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/LiveMatch")
public class LiveMatchesController {

        private LiveMatchService liveMatchService;

        @Autowired
        public LiveMatchesController (LiveMatchService liveMatchService){
                this.liveMatchService = liveMatchService;
        }

        @GetMapping("/")
        public ResponseEntity<Matches> getLiveMatchs() throws IOException, InterruptedException {
                return liveMatchService.getLiveMatch();
        }

}
