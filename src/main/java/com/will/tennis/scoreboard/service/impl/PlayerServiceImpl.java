package com.will.tennis.scoreboard.service.impl;

import com.will.tennis.scoreboard.model.Player;
import com.will.tennis.scoreboard.repository.PlayerRepository;
import com.will.tennis.scoreboard.repository.impl.PlayerRepositoryImpl;
import com.will.tennis.scoreboard.service.PlayerService;

public class PlayerServiceImpl implements PlayerService {
    private final PlayerRepository playerRepository = new PlayerRepositoryImpl();

    @Override
    public void createPlayersIfNotExist(String player1Name, String player2Name) {
        createIfNotPresent(player1Name);
        createIfNotPresent(player2Name);
    }

    private void createIfNotPresent(String player1Name) {
        if (playerRepository.findPlayerByName(player1Name).isEmpty()) {
            playerRepository.save(new Player(player1Name));
        }
    }
}
