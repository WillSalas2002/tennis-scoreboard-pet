package com.will.tennis.scoreboard.repository;

import com.will.tennis.scoreboard.config.DbConfig;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.function.Consumer;
import java.util.function.Function;

@Slf4j
public abstract class AbstractRepository {
    private final SessionFactory sessionFactory = DbConfig.getSessionFactory();

    protected <T> T execute(Function<Session, T> action) {
        T result = null;
        Transaction transaction = null;
        try (Session session = sessionFactory.getCurrentSession()) {
            transaction = session.beginTransaction();
            result = action.apply(session);
            transaction.commit();
        } catch (Exception e) {
            log.error(e.getMessage());
            if (transaction != null) {
                transaction.rollback();
            }
        }
        return result;
    }

    protected void executeVoid(Consumer<Session> action) {
        execute(session -> {
            action.accept(session);
            return null;
        });
    }
}
