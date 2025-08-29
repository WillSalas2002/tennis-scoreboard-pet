package com.will.tennis.scoreboard.repository.impl;

import com.will.tennis.scoreboard.model.Player;
import com.will.tennis.scoreboard.repository.AbstractRepository;
import com.will.tennis.scoreboard.repository.PlayerRepository;

import java.util.Optional;

public class PlayerRepositoryImpl extends AbstractRepository implements PlayerRepository {

    public static final String PLAYER_BY_NAME_QUERY = "SELECT p FROM Player p WHERE p.name = :name";

    @Override
    public Optional<Player> findPlayerByName(String name) {
        return execute(session -> session.createQuery(PLAYER_BY_NAME_QUERY, Player.class)
                .setParameter("name", name)
                .uniqueResultOptional());
    }

    @Override
    public void save(Player player) {
        executeVoid(session -> session.persist(player));
    }
}
