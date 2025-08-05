package com.will.tennis.scoreboard.repository;

import com.will.tennis.scoreboard.model.Match;

import java.util.List;

public interface MatchRepository {

    List<Match> findAll();
}
