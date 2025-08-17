package com.will.tennis.scoreboard.service.impl;

import com.will.tennis.scoreboard.dto.MatchScoreDto;
import com.will.tennis.scoreboard.service.OngoingMatchService;
import com.will.tennis.scoreboard.storage.MatchStorage;

import java.util.UUID;

public class OngoingMatchServiceImpl implements OngoingMatchService {
    private final MatchStorage matchStorage = MatchStorage.getINSTANCE();

    @Override
    public UUID createMatch(String player1Name, String player2Name) {
        MatchScoreDto matchScoreDto = new MatchScoreDto(player1Name, player2Name);
        return matchStorage.saveMatch(matchScoreDto);
    }

    @Override
    public MatchScoreDto getMatchScoreDto(UUID matchId) {
        return matchStorage.getMatchScoreDtos(matchId);
    }
}
