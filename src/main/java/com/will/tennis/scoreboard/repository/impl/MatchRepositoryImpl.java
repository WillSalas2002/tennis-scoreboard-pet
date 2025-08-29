package com.will.tennis.scoreboard.repository.impl;

import com.will.tennis.scoreboard.model.Match;
import com.will.tennis.scoreboard.repository.AbstractRepository;
import com.will.tennis.scoreboard.repository.MatchRepository;

import java.util.List;

import static com.will.tennis.scoreboard.Constants.RECORDS_PER_PAGE;

public class MatchRepositoryImpl extends AbstractRepository implements MatchRepository {

    private static final String BASE_SELECT_QUERY = " SELECT m FROM Match m JOIN FETCH m.player1 JOIN FETCH m.player2 JOIN FETCH m.winner";
    private static final String COUNT_QUERY = "SELECT COUNT(m.id) FROM Match m";
    private static final String WHERE_QUERY = " WHERE m.player1.name = :name OR m.player2.name = :name ";
    private static final String LIMIT_SUFFIX = " LIMIT :limit ";
    private static final String OFFSET_SUFFIX = " OFFSET :offset ";

    private static final String LIMIT_PARAMETER = "limit";
    private static final String NAME_PARAMETER = "name";
    private static final String OFFSET_PARAMETER = "offset";

    @Override
    public List<Match> findAll() {
        String query = BASE_SELECT_QUERY + LIMIT_SUFFIX;
        return execute(session -> session
                .createQuery(query, Match.class)
                .setParameter(LIMIT_PARAMETER, RECORDS_PER_PAGE)
                .getResultList());
    }

    @Override
    public long getMatchQuantity() {
        return execute(session -> session
                .createQuery(COUNT_QUERY, Long.class)
                .getSingleResult());
    }

    @Override
    public long getMatchQuantity(String name) {
        String query = COUNT_QUERY + WHERE_QUERY;
        return execute(session -> session.createQuery(query, Long.class)
                .setParameter(NAME_PARAMETER, name)
                .getSingleResult());
    }

    @Override
    public void save(Match match) {
        executeVoid(session -> session.persist(match));
    }

    @Override
    public List<Match> findAll(int offset, String name) {
        List<Match> matches;
        if (name == null) {
            String query = BASE_SELECT_QUERY + LIMIT_SUFFIX + OFFSET_SUFFIX;
            matches = execute(session -> session
                    .createQuery(query, Match.class)
                    .setParameter(LIMIT_PARAMETER, RECORDS_PER_PAGE)
                    .setParameter(OFFSET_PARAMETER, offset)
                    .getResultList());

        } else {
            String query = BASE_SELECT_QUERY + WHERE_QUERY + LIMIT_SUFFIX + OFFSET_SUFFIX;
            matches = execute(session -> session
                    .createQuery(query, Match.class)
                    .setParameter(LIMIT_PARAMETER, RECORDS_PER_PAGE)
                    .setParameter(OFFSET_PARAMETER, offset)
                    .setParameter(NAME_PARAMETER, name)
                    .getResultList());
        }
        return matches;
    }

    @Override
    public List<Match> findByName(String name) {
        String query = BASE_SELECT_QUERY + WHERE_QUERY + LIMIT_SUFFIX;
        return execute(session -> session
                .createQuery(query, Match.class)
                .setParameter(NAME_PARAMETER, name)
                .setParameter(LIMIT_PARAMETER, RECORDS_PER_PAGE)
                .getResultList());
    }
}
