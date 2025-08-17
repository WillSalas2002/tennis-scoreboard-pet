package com.will.tennis.scoreboard.service.impl;

import com.will.tennis.scoreboard.dto.MatchScoreDto;
import com.will.tennis.scoreboard.service.OngoingMatchService;
import com.will.tennis.scoreboard.service.game.TennisMatchService;

import java.util.UUID;

public class MatchScoreCalculationService {
    private final OngoingMatchService ongoingMatchService = new OngoingMatchServiceImpl();
    TennisMatchService tennisMatchService = new TennisMatchService();

    public boolean updateScore(String matchId, String scoredPlayerName) {
        MatchScoreDto matchScoreDto = ongoingMatchService.getMatchScoreDto(UUID.fromString(matchId));

        tennisMatchService.setPlayers(matchScoreDto.getPlayer1(), matchScoreDto.getPlayer2());
        tennisMatchService.pointWonBy(scoredPlayerName);
        mapToMatchScoreDto(matchScoreDto, tennisMatchService);
        if (tennisMatchService.isFinished()) {
            tennisMatchService.getWinner();
            return true;
        }
        return false;
    }

    private void mapToMatchScoreDto(MatchScoreDto matchScoreDtos, TennisMatchService tennisMatchService) {
        matchScoreDtos.setPlayer1Points(tennisMatchService.getPlayer1PointScore());
        matchScoreDtos.setPlayer1Games(tennisMatchService.getPlayer1GameScore());
        matchScoreDtos.setPlayer1Sets(tennisMatchService.getPlayer1Sets());
        matchScoreDtos.setPlayer2Points(tennisMatchService.getPlayer2PointScore());
        matchScoreDtos.setPlayer2Games(tennisMatchService.getPlayer2GameScore());
        matchScoreDtos.setPlayer2Sets(tennisMatchService.getPlayer2Sets());
        matchScoreDtos.setFinished(tennisMatchService.isFinished());
    }
}
