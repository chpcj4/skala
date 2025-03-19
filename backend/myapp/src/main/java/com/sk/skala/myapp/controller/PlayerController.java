package com.sk.skala.myapp.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sk.skala.myapp.model.Player;
import com.sk.skala.myapp.service.PlayerService;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@RestController
@RequestMapping("/api")
public class PlayerController {
    private final PlayerService playerService;
    

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @GetMapping("/players")
    public List<Player> getAllPlayers() {
        log.info ("call api/players");
        return playerService.getAllPlayers();
    }
}
