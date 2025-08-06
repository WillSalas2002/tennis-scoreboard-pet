package com.will.tennis.scoreboard.repository.impl;

import com.will.tennis.scoreboard.config.DbConfig;
import com.will.tennis.scoreboard.model.Match;
import com.will.tennis.scoreboard.repository.MatchRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class MatchesRepositoryImpl implements MatchRepository {
    private static final SessionFactory SESSION_FACTORY = DbConfig.getSessionFactory();

    @Override
    public List<Match> findAll() {
        Session session = SESSION_FACTORY.getCurrentSession();
        session.beginTransaction();
        List<Match> matches = session.createQuery("FROM Match m JOIN m.player1 JOIN m.player2 JOIN m.winner", Match.class).getResultList();
        session.getTransaction().commit();
        return matches;
    }

    @Override
    public long getMatchQuantity() {
        Session session = SESSION_FACTORY.getCurrentSession();
        session.beginTransaction();
        Long quantity = session.createQuery("SELECT COUNT(*) FROM Match m", Long.class).getSingleResult();
        session.getTransaction().commit();
        return quantity;
    }

    @Override
    public List<Match> findAll(int limit, int offset) {
        Session session = SESSION_FACTORY.getCurrentSession();
        session.beginTransaction();
        List<Match> matches = session.createQuery("SELECT m FROM Match m LIMIT :limit OFFSET :offset", Match.class)
                .setParameter("limit", limit)
                .setParameter("offset", offset)
                .getResultList();
        session.getTransaction().commit();
        return matches;
    }
}
