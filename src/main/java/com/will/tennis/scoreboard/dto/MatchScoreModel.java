package com.will.tennis.scoreboard.dto;

import com.will.tennis.scoreboard.service.game.Point;
import com.will.tennis.scoreboard.service.game.TennisScoreCalculator;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
public class MatchScoreModel {
    private String player1;
    private String player1Points;
    private int player1Games;
    private int player1Sets;
    private String player2;
    private String player2Points;
    private int player2Games;
    private int player2Sets;
    private boolean isFinished;
    @Setter(AccessLevel.NONE)
    private final TennisScoreCalculator scoreCalculator;

    public MatchScoreModel(String player1, String player2) {
        this.player1 = player1;
        this.player2 = player2;
        player1Points = Point.ZERO.getLabel();
        player2Points = Point.ZERO.getLabel();
        scoreCalculator = new TennisScoreCalculator();
    }
}
