package com.will.tennis.scoreboard.service;

import com.will.tennis.scoreboard.dto.MatchDto;

import java.util.List;
import java.util.UUID;

public interface MatchService {

    List<MatchDto> findAll(String name, String pageStr);

    long getTotalPageCount();

    long getTotalPageCount(String name);

    UUID createMatch(String player1Name, String player2Name);
}
