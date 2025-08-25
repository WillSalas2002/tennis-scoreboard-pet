package com.will.tennis.scoreboard.service.impl;

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
        MatchScoreModel matchScoreModel = ongoingMatchService.getMatchScoreDto(UUID.fromString(matchId));

        String player1Name = matchScoreModel.getPlayer1();
        String player2Name = matchScoreModel.getPlayer2();

        Player player1 = playerRepository.findPlayerByName(player1Name).get();
        Player player2 = playerRepository.findPlayerByName(player2Name).get();
        Player winner = matchScoreModel.getPlayer1Sets() == 2 ? player1 : player2;

        Match match = new Match(player1, player2, winner);
        matchRepository.save(match);
    }
}
