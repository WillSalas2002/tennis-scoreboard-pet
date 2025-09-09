package com.will.tennis.scoreboard.listener;

import com.will.tennis.scoreboard.config.DbConfig;
import com.will.tennis.scoreboard.model.Match;
import com.will.tennis.scoreboard.model.Player;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

@WebListener
public class InitListener implements ServletContextListener {
    private static final SessionFactory SESSION_FACTORY = DbConfig.getSessionFactory();

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        Session session = SESSION_FACTORY.getCurrentSession();
        Player player1 = new Player("Ilkham");
        Player player2 = new Player("Atabek");
        Player player3 = new Player("Miyirbek");
        Player player4 = new Player("Malik");
        Player player5 = new Player("Ulug'bek");

        Match match1 = new Match(player1, player2, player2);
        Match match2 = new Match(player3, player4, player3);
        Match match3 = new Match(player2, player3, player2);
        Match match4 = new Match(player1, player4, player1);
        Match match5 = new Match(player4, player5, player4);
        Match match6 = new Match(player2, player5, player5);

        session.beginTransaction();

        session.persist(player1);
        session.persist(player2);
        session.persist(player3);
        session.persist(player4);
        session.persist(player5);

        session.persist(match1);
        session.persist(match2);
        session.persist(match3);
        session.persist(match4);
        session.persist(match5);
        session.persist(match6);

        session.getTransaction().commit();
    }
}
