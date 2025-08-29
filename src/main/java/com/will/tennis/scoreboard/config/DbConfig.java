package com.will.tennis.scoreboard.config;

import com.will.tennis.scoreboard.model.Match;
import com.will.tennis.scoreboard.model.Player;
import com.will.tennis.scoreboard.util.PropertiesUtil;
import lombok.experimental.UtilityClass;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

@UtilityClass
public class DbConfig {
    private static final String KEY_DRIVER_CLASS = "hibernate.connection.driver_class";
    private static final String KEY_URL = "hibernate.connection.url";
    private static final String KEY_USERNAME = "hibernate.connection.username";
    private static final String KEY_PASSWORD = "hibernate.connection.password";
    private static final String KEY_HBM_2_DDL = "hibernate.hbm2ddl.auto";
    private static final String KEY_DIALECT = "hibernate.dialect";
    private static final String KEY_SHOW_SQL = "hibernate.show_sql";
    private static final String KEY_CURRENT_SESSION_CONTEXT = "hibernate.current_session_context_class";
    private static final String KEY_CONNECTION_POOL_MIN_SIZE = "hibernate.c3p0.min_size";
    private static final String KEY_CONNECTION_POOL_MAX_SIZE = "hibernate.c3p0.max_size";
    private static final String KEY_CONNECTION_TIMEOUT = "hibernate.c3p0.timeout";
    private static final String KEY_CONNECTION_MAX_STATEMENTS = "hibernate.c3p0.max_statements";
    private static final String KEY_CONNECTION_PROVIDER_CLASS = "hibernate.connection.provider_class";

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
        settings.put(KEY_DRIVER_CLASS, PropertiesUtil.get(KEY_DRIVER_CLASS));
        settings.put(KEY_URL, PropertiesUtil.get(KEY_URL));
        settings.put(KEY_USERNAME, PropertiesUtil.get(KEY_URL));
        settings.put(KEY_PASSWORD, PropertiesUtil.get(KEY_PASSWORD));
        settings.put(KEY_HBM_2_DDL, PropertiesUtil.get(KEY_HBM_2_DDL));
        settings.put(KEY_DIALECT, PropertiesUtil.get(KEY_DIALECT));
        settings.put(KEY_SHOW_SQL, PropertiesUtil.get(KEY_SHOW_SQL));
        settings.put(KEY_CURRENT_SESSION_CONTEXT, PropertiesUtil.get(KEY_CURRENT_SESSION_CONTEXT));
//        settings.put(KEY_CONNECTION_POOL_MIN_SIZE, PropertiesUtil.get(KEY_CONNECTION_POOL_MIN_SIZE));
//        settings.put(KEY_CONNECTION_POOL_MAX_SIZE, PropertiesUtil.get(KEY_CONNECTION_POOL_MAX_SIZE));
//        settings.put(KEY_CONNECTION_TIMEOUT, PropertiesUtil.get(KEY_CONNECTION_TIMEOUT));
//        settings.put(KEY_CONNECTION_MAX_STATEMENTS, PropertiesUtil.get(KEY_CONNECTION_MAX_STATEMENTS));
//        settings.put(KEY_CONNECTION_PROVIDER_CLASS, PropertiesUtil.get(KEY_CONNECTION_PROVIDER_CLASS));
        return settings;
    }
}
