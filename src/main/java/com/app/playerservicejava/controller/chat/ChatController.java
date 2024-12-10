package com.app.playerservicejava.controller.chat;

import com.app.playerservicejava.model.request.GenerateTeamRequest;
import com.app.playerservicejava.model.response.GeneratedTeamResponse;
import com.app.playerservicejava.service.chat.ChatClientService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.ollama4j.exceptions.OllamaBaseException;
import io.github.ollama4j.models.Model;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@Controller
@RequestMapping(value = "v1/chat", produces = { MediaType.APPLICATION_JSON_VALUE })
public class ChatController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ChatController.class);

    @Autowired
    private ChatClientService chatClientService;

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping
    public @ResponseBody String chat() throws OllamaBaseException, IOException, InterruptedException {
        return chatClientService.chat();
    }

    @GetMapping("/list-models")
    public ResponseEntity<List<Model>> listModels() throws OllamaBaseException, IOException, URISyntaxException, InterruptedException {
        List<Model> models = chatClientService.listModels();
        return ResponseEntity.ok(models);
    }

    @GetMapping("/ping")
    public ResponseEntity<String> ping() throws OllamaBaseException, IOException, InterruptedException {
        chatClientService.ping();
        return ResponseEntity.ok("pong");
    }

    @PostMapping("/generate-team")
    public ResponseEntity<GeneratedTeamResponse> generateTeam(@RequestBody GenerateTeamRequest request) throws IOException {
        ResponseEntity<String> response = restTemplate.postForEntity("http://127.0.0.1:5001/team/generate", request, String.class);
        ObjectMapper objectMapper = new ObjectMapper();
        return ResponseEntity.ok(objectMapper.readValue(response.getBody(), GeneratedTeamResponse.class));
    }
}
