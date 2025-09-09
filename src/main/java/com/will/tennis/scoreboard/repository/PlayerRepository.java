package com.will.tennis.scoreboard.repository;

import com.will.tennis.scoreboard.model.Player;

import java.util.Optional;

public interface PlayerRepository {

    Optional<Player> findPlayerByName(String name);

    void save(Player player);
}
