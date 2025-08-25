package com.will.tennis.scoreboard.service.impl;

import com.will.tennis.scoreboard.dto.MatchScoreModel;
import com.will.tennis.scoreboard.service.OngoingMatchService;
import com.will.tennis.scoreboard.service.game.TennisScoreCalculator;

import java.util.UUID;

public class MatchScoreCalculationService {
    private final OngoingMatchService ongoingMatchService = new OngoingMatchServiceImpl();

    public boolean updateScore(String matchId, String scoredPlayerName) {
        MatchScoreModel matchScoreModel = ongoingMatchService.getMatchScoreDto(UUID.fromString(matchId));
        TennisScoreCalculator scoreCalculator = matchScoreModel.getScoreCalculator();

        scoreCalculator.setPlayers(matchScoreModel.getPlayer1(), matchScoreModel.getPlayer2());
        scoreCalculator.pointWonBy(scoredPlayerName);

        mapToMatchScoreDto(matchScoreModel, scoreCalculator);
        return scoreCalculator.isFinished();
    }

    private void mapToMatchScoreDto(MatchScoreModel matchScoreDtos, TennisScoreCalculator tennisScoreCalculator) {
        matchScoreDtos.setPlayer1Points(tennisScoreCalculator.getPlayer1PointScore());
        matchScoreDtos.setPlayer1Games(tennisScoreCalculator.getPlayer1GameScore());
        matchScoreDtos.setPlayer1Sets(tennisScoreCalculator.getPlayer1Sets());
        matchScoreDtos.setPlayer2Points(tennisScoreCalculator.getPlayer2PointScore());
        matchScoreDtos.setPlayer2Games(tennisScoreCalculator.getPlayer2GameScore());
        matchScoreDtos.setPlayer2Sets(tennisScoreCalculator.getPlayer2Sets());
        matchScoreDtos.setFinished(tennisScoreCalculator.isFinished());
    }
}
