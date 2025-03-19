package com.sk.skala.myapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.sk.skala.myapp.model.Player;
import com.sk.skala.myapp.repository.PlayerRepository;

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
        playerRepository.loadPlayerList();
    }

    public List<Player> getAllPlayers() {
        List<Player> playerList = playerRepository.getPlayerList();
        return playerList;
    }
}
