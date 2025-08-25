package com.will.tennis.scoreboard.service;

import com.will.tennis.scoreboard.dto.MatchScoreModel;

import java.util.UUID;

public interface OngoingMatchService {

    MatchScoreModel getMatchScoreDto(UUID matchId);

    UUID createMatch(String player1Name, String player2Name);
}
