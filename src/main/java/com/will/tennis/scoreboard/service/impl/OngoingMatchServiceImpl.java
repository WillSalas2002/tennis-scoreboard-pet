package com.will.tennis.scoreboard.service.impl;

import com.will.tennis.scoreboard.dto.MatchScoreDto;
import com.will.tennis.scoreboard.dto.MatchScoreModel;
import com.will.tennis.scoreboard.service.OngoingMatchService;
import com.will.tennis.scoreboard.service.game.Point;
import com.will.tennis.scoreboard.storage.MatchStorage;

import java.util.List;
import java.util.UUID;

public class OngoingMatchServiceImpl implements OngoingMatchService {
    private final MatchStorage matchStorage = MatchStorage.getINSTANCE();

    @Override
    public UUID createMatch(String player1Name, String player2Name) {
        MatchScoreModel matchScoreModel1 = new MatchScoreModel(player1Name, Point.ZERO.name(), 0, 0);
        MatchScoreModel matchScoreModel2 = new MatchScoreModel(player2Name, Point.ZERO.name(), 0, 0);
        MatchScoreDto matchScoreDto = new MatchScoreDto(List.of(matchScoreModel1, matchScoreModel2), false);

        return matchStorage.saveMatch(matchScoreDto);
    }

    @Override
    public MatchScoreDto getMatchScoreDto(UUID matchId) {
        return matchStorage.getMatchScoreDtos(matchId);
    }
}
