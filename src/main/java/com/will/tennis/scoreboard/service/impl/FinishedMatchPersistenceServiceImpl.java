package com.will.tennis.scoreboard.service.impl;

import com.will.tennis.scoreboard.dto.MatchScoreDto;
import com.will.tennis.scoreboard.dto.MatchScoreModel;
import com.will.tennis.scoreboard.model.Match;
import com.will.tennis.scoreboard.model.Player;
import com.will.tennis.scoreboard.repository.MatchRepository;
import com.will.tennis.scoreboard.repository.PlayerRepository;
import com.will.tennis.scoreboard.repository.impl.MatchRepositoryImpl;
import com.will.tennis.scoreboard.repository.impl.PlayerRepositoryImpl;
import com.will.tennis.scoreboard.service.FinishedMatchPersistenceService;
import com.will.tennis.scoreboard.service.OngoingMatchService;

import java.util.UUID;

public class FinishedMatchPersistenceServiceImpl implements FinishedMatchPersistenceService {
    private final OngoingMatchService ongoingMatchService = new OngoingMatchServiceImpl();
    private final MatchRepository matchRepository = new MatchRepositoryImpl();
    private final PlayerRepository playerRepository = new PlayerRepositoryImpl();

    @Override
    public void saveMatch(String matchId) {
        MatchScoreDto matchScoreDto = ongoingMatchService.getMatchScoreDto(UUID.fromString(matchId));
        MatchScoreModel matchScoreModel1 = matchScoreDto.getMatchScoreModels().get(0);
        MatchScoreModel matchScoreModel2 = matchScoreDto.getMatchScoreModels().get(1);

        String player1Name = matchScoreModel1.getName();
        String player2Name = matchScoreModel2.getName();

        Player player1 = playerRepository.findPlayerByName(player1Name).get();
        Player player2 = playerRepository.findPlayerByName(player2Name).get();
        Player winner = matchScoreModel1.getGames() == 2 ? player1 : player2;

        Match match = new Match(player1, player2, winner);
        matchRepository.save(match);
    }
}
