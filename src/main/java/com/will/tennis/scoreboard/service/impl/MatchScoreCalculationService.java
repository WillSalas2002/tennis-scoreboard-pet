package com.will.tennis.scoreboard.service.impl;

import com.will.tennis.scoreboard.dto.MatchScoreDto;
import com.will.tennis.scoreboard.dto.MatchScoreModel;
import com.will.tennis.scoreboard.service.OngoingMatchService;
import com.will.tennis.scoreboard.service.game.PointScore;

import java.util.UUID;

public class MatchScoreCalculationService {
    private final OngoingMatchService ongoingMatchService = new OngoingMatchServiceImpl();

    public boolean updateScore(String matchId, String scoredPlayerName) {
        MatchScoreDto matchScoreDtos = ongoingMatchService.getMatchScoreDto(UUID.fromString(matchId));
        MatchScoreModel player1Score = matchScoreDtos.getMatchScoreModels().get(0);
        MatchScoreModel player2Score = matchScoreDtos.getMatchScoreModels().get(1);

        MatchScoreModel scorer = player1Score.getName().equals(scoredPlayerName) ? player1Score : player2Score;
        MatchScoreModel other = scorer == player1Score ? player2Score : player1Score;

        PointScore pointScore = new PointScore(scorer, other);
        pointScore.updateScore();
        return pointScore.isGameFinished();
    }
}
