package com.UTN.Integrador.controller;

import com.UTN.Integrador.domain.Matches;
import com.UTN.Integrador.service.LiveMatchService;
//import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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


        @ApiOperation(value = "Return live Matches", produces = "Matches")
        @GetMapping("/A")
        //@CircuitBreaker(name = "service1", fallbackMethod = "LiveMatchesFallback")
        public ResponseEntity<Matches> getLiveMatchs() throws IOException, InterruptedException {
                return liveMatchService.getLiveMatch();
        }



        /*public ResponseEntity<Matches> LiveMatchesFallback(final Throwable excep){
                return ResponseEntity.status(HttpStatus.NO_CONTENT).body(Matches.builder().build());
        }*/



}
