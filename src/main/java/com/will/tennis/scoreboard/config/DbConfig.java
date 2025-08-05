package com.will.tennis.scoreboard.config;

import com.will.tennis.scoreboard.model.Match;
import com.will.tennis.scoreboard.model.Player;
import lombok.experimental.UtilityClass;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

@UtilityClass
public class DbConfig {
    private SessionFactory sessionFactory;

    public static synchronized SessionFactory getSessionFactory() {
        return sessionFactory == null ? createSessionFactory() : sessionFactory;
    }
    private SessionFactory createSessionFactory() {

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(dbSettings())
                .build();

        Metadata metadata = new MetadataSources(serviceRegistry)
                .addAnnotatedClass(Player.class)
                .addAnnotatedClass(Match.class)
                .buildMetadata();

        sessionFactory = metadata.buildSessionFactory();
        return sessionFactory;
    }

    private static Properties dbSettings() {
        Properties settings = new Properties();
        settings.put("hibernate.connection.driver_class", "org.h2.Driver");
        settings.put("hibernate.connection.url", "jdbc:h2:mem:tennisdb");
        settings.put("hibernate.connection.username", "sa");
        settings.put("hibernate.connection.password", "password");
        settings.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        settings.put("hibernate.show_sql", "true");
        settings.put("hibernate.hbm2ddl.auto", "create");
        settings.put("hibernate.current_session_context_class", "thread");
        return settings;
    }
}
