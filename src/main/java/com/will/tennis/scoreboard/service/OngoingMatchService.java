package com.will.tennis.scoreboard.service;

import com.will.tennis.scoreboard.dto.MatchScoreDto;

import java.util.UUID;

public interface OngoingMatchService {

    MatchScoreDto getMatchScoreDto(UUID matchId);

    UUID createMatch(String player1Name, String player2Name);
}
