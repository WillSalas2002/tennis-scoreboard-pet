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
        List<Match> matches = session.createQuery("FROM Match", Match.class).getResultList();
        session.getTransaction().commit();
        return matches;
    }
}
