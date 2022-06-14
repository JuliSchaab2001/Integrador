package com.UTN.Integrador.service.implementation;

import com.UTN.Integrador.domain.LiveMatch;
import com.UTN.Integrador.domain.Matches;
import com.UTN.Integrador.service.LiveMatchService;
import com.UTN.Integrador.utils.JsonBodyHandler;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Service
public class LiveMatchServiceImpl implements LiveMatchService {


    @Override
    public ResponseEntity<Matches> getLiveMatch() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://sports-live-scores.p.rapidapi.com/football/live"))
                .header("X-RapidAPI-Host", "sports-live-scores.p.rapidapi.com")
                .header("X-RapidAPI-Key", "028277eb99mshbb8149750838a6dp1dd443jsn048c816a89ec")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<Matches> response = null;
        response = HttpClient.newHttpClient().send(request, new JsonBodyHandler<>(Matches.class));
        return ResponseEntity.status(HttpStatus.OK).body(response.body());

    }
}
