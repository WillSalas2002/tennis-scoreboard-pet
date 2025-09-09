package com.will.tennis.scoreboard.service.impl;

import com.will.tennis.scoreboard.dto.MatchScoreModel;
import com.will.tennis.scoreboard.service.OngoingMatchService;
import com.will.tennis.scoreboard.storage.MatchStorage;

import java.util.UUID;

public class OngoingMatchServiceImpl implements OngoingMatchService {
    private final MatchStorage matchStorage = MatchStorage.getINSTANCE();

    @Override
    public UUID createMatch(String player1Name, String player2Name) {
        MatchScoreModel matchScoreModel = new MatchScoreModel(player1Name, player2Name);
        return matchStorage.saveMatch(matchScoreModel);
    }

    @Override
    public MatchScoreModel getMatchScoreDto(UUID matchId) {
        return matchStorage.getMatchScoreDtos(matchId);
    }
}
