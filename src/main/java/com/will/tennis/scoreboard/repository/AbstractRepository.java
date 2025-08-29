package com.will.tennis.scoreboard.repository;

import com.will.tennis.scoreboard.config.DbConfig;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.function.Consumer;
import java.util.function.Function;

public abstract class AbstractRepository {
    private final SessionFactory sessionFactory = DbConfig.getSessionFactory();

    protected <T> T execute(Function<Session, T> action) {
        Session session = sessionFactory.getCurrentSession();
        T result = action.apply(session);
        session.getTransaction().commit();
        return result;
    }

    protected void executeVoid(Consumer<Session> action) {
        execute(session -> {
            action.accept(session);
            return null;
        });
    }
}
