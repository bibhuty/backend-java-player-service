package com.app.playerservicejava;

import com.app.playerservicejava.model.Player;
import com.app.playerservicejava.repository.PlayerRepository;
import com.app.playerservicejava.service.PlayerService;
import com.app.playerservicejava.service.chat.ChatClientService;
import io.github.ollama4j.exceptions.OllamaBaseException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@SpringBootTest
class PlayerServiceJavaApplicationTests {

    @Autowired
    private PlayerService playerService;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private ChatClientService chatClientService;

    @Test
    void contextLoads() {
    }

    @Test
    void getPlayerById(){
        playerService.getPlayerById("aaronto01");
    }

    @Test
    void getActiveRetiredPlayers(){
        System.out.println(playerRepository.findActiveRetiredPlayers());
    }
    @Test
    void getActiveRetiredPlayersNative(){
        System.out.println(playerRepository.findActiveRetiredPlayersNative());
    }

    @Test
    void findPlayersByNameGivenNative(){
        System.out.println(playerRepository.findPlayersByNameGivenNative("Tommie Lee"));
    }

    @Test
    void findPlayersByNameGivenNativeNamedParam(){
        System.out.println(ResponseEntity.ok(playerRepository.findPlayersByNameGivenNativeNamedParam("Tommie Lee")));
    }


    @Test
    void deletePlayersByNameGiven(){
        System.out.println(playerRepository.deletePlayersByNameGiven("Tommie Lee"));;
    }

    @Test
    void getPlayerByIdAndAskLLMToTalkAboutIt() throws OllamaBaseException, IOException, InterruptedException {
        Player aaronto01 = playerService.getPlayerById("aaronto01").orElse(null);
        assert aaronto01 != null;
        System.out.println(chatClientService.getPromptResponse("What do you think about this player?"+ aaronto01));;
    }

}
