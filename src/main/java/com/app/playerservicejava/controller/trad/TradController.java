package com.app.playerservicejava.controller.trad;

import com.app.playerservicejava.model.request.GenerateTeamRequest;
import com.app.playerservicejava.model.response.GeneratedTeamResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Controller
@RequestMapping(value = "v1/trad/team", produces = { MediaType.APPLICATION_JSON_VALUE })
public class TradController {
    private static final Logger LOGGER = LoggerFactory.getLogger(TradController.class);

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/generate")
    public ResponseEntity<GeneratedTeamResponse> generateTeam(@RequestBody GenerateTeamRequest request) throws IOException {
        ResponseEntity<String> response = restTemplate.postForEntity("http://127.0.0.1:5001/team/generate", request, String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        return ResponseEntity.ok(objectMapper.readValue(response.getBody(), GeneratedTeamResponse.class));
    }
}
