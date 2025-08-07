package com.will.tennis.scoreboard.repository.impl;

import com.will.tennis.scoreboard.config.DbConfig;
import com.will.tennis.scoreboard.model.Match;
import com.will.tennis.scoreboard.repository.MatchRepository;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.List;

public class MatchRepositoryImpl implements MatchRepository {
    private static final SessionFactory SESSION_FACTORY = DbConfig.getSessionFactory();

    @Override
    public List<Match> findAll() {
        Session session = SESSION_FACTORY.getCurrentSession();
        session.beginTransaction();
        List<Match> matches = session
                .createQuery("""
                SELECT m
                FROM Match m
                JOIN FETCH m.player1
                JOIN FETCH m.player2
                JOIN FETCH m.winner
                """, Match.class)
                .getResultList();
        session.getTransaction().commit();
        return matches;
    }

    @Override
    public long getMatchQuantity() {
        Session session = SESSION_FACTORY.getCurrentSession();
        session.beginTransaction();
        Long quantity = session
                .createQuery("""
                SELECT COUNT(m.id)
                FROM Match m
                """, Long.class)
                .getSingleResult();
        session.getTransaction().commit();
        return quantity;
    }

    @Override
    public long getMatchQuantity(String name) {
        Session session = SESSION_FACTORY.getCurrentSession();
        session.beginTransaction();
        Long quantity = session.createQuery("""
                SELECT COUNT(m.id)
                FROM Match m
                WHERE m.player1.name = :name OR
                      m.player2.name = :name
                """, Long.class)
                .setParameter("name", name)
                .getSingleResult();
        session.getTransaction().commit();
        return quantity;
    }

    @Override
    public List<Match> findAll(int limit, int offset) {
        Session session = SESSION_FACTORY.getCurrentSession();
        session.beginTransaction();
        List<Match> matches = session
                .createQuery("""
                        SELECT m
                        FROM Match m
                        JOIN FETCH m.player1
                        JOIN FETCH m.player2
                        JOIN FETCH m.winner
                        LIMIT :limit
                        OFFSET :offset
                        """, Match.class)
                .setParameter("limit", limit)
                .setParameter("offset", offset)
                .getResultList();
        session.getTransaction().commit();
        return matches;
    }

    @Override
    public List<Match> findByName(String name) {
        Session session = SESSION_FACTORY.getCurrentSession();
        session.beginTransaction();
        List<Match> matches = session
                .createQuery("""
                        SELECT m
                        FROM Match m
                        JOIN FETCH m.player1
                        JOIN FETCH m.player2
                        JOIN FETCH m.winner
                        WHERE m.player1.name = :name OR
                              m.player2.name = :name
                        """, Match.class)
                .setParameter("name", name)
                .getResultList();
        session.getTransaction().commit();
        return matches;
    }
}
