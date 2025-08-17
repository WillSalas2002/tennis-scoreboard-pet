package com.will.tennis.scoreboard.repository.impl;

import com.will.tennis.scoreboard.config.DbConfig;
import com.will.tennis.scoreboard.model.Player;
import com.will.tennis.scoreboard.repository.PlayerRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.Optional;

public class PlayerRepositoryImpl implements PlayerRepository {
    private static final SessionFactory SESSION_FACTORY = DbConfig.getSessionFactory();

    public static final String PLAYER_BY_NAME_QUERY = "SELECT p FROM Player p WHERE p.name = :name";

    @Override
    public Optional<Player> findPlayerByName(String name) {
        Session session = SESSION_FACTORY.getCurrentSession();
        session.beginTransaction();
        Optional<Player> playerOptional = session.createQuery(PLAYER_BY_NAME_QUERY, Player.class)
                .setParameter("name", name)
                .uniqueResultOptional();
        session.getTransaction().commit();
        return playerOptional;
    }

    @Override
    public void save(Player player) {
        Session session = SESSION_FACTORY.getCurrentSession();
        session.beginTransaction();
        session.persist(player);
        session.getTransaction().commit();
    }
}
