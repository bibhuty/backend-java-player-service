package com.app.playerservicejava.controller;

import com.app.playerservicejava.model.Player;
import com.app.playerservicejava.model.Players;
import com.app.playerservicejava.model.request.PlayerIdsRequest;
import com.app.playerservicejava.service.PlayerService;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping(value = "v1/players", produces = { MediaType.APPLICATION_JSON_VALUE })
public class PlayerController {
    @Resource
    private PlayerService playerService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<Players> getPlayers() {
        Players players = playerService.getPlayers();
        return ok(players);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Player> getPlayerById(@PathVariable("id") String id) {
        Optional<Player> player = playerService.getPlayerById(id);

        if (player.isPresent()) {
            return new ResponseEntity<>(player.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/ids")
    public ResponseEntity<Players> getPlayerById(@ModelAttribute PlayerIdsRequest request) {
        Players players = playerService.findPlayerByIds(request.getPlayerIds());
        return ok(players);
    }

    @GetMapping("/findByBirthMonthAndYear")
    public ResponseEntity<List<Player>> getPlayersByBirthMonthAndYear(@RequestParam String month, @RequestParam String year) {
        List<Player> players = playerService.findAllPlayersByBirthMonthAndYear(month,year);
        return ok(players);
    }

    @GetMapping("/findByBirthMonthAndYearPaginated")
    public ResponseEntity<Page<Player>> findByBirthMonthAndYearPaginated(@RequestParam String month, @RequestParam String year, @RequestParam int page, @RequestParam int size, @RequestParam(required = false) String sort) {
        return ok(playerService.findAllPlayersByBirthMonthAndYear(month,year, PageRequest.of(page,size,Sort.Direction.ASC,sort)));
    }
}
