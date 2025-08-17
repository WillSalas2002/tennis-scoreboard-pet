package com.will.tennis.scoreboard.service.game;

import com.will.tennis.scoreboard.dto.MatchScoreModel;

public class GameScore extends Score {

    public GameScore(MatchScoreModel scorer, MatchScoreModel other) {
        super(scorer, other);
    }

    @Override
    public void updateScore() {
        scorer.setGames(scorer.getGames() + 1);

        int scorerGameScore = scorer.getGames();
        int otherGameScore = other.getGames();

        int subtraction = scorerGameScore - otherGameScore;

        if (((scorerGameScore == 6 || scorerGameScore == 7) && subtraction >= 2)) {
            resetScores();
            new SetScore(scorer, other).updateScore();
        }

        if (scorerGameScore == 6 && otherGameScore == 6) {
            isTieBreak = true;
            // play tie-break here
        }
    }

    @Override
    public void resetScores() {
        scorer.setGames(0);
        other.setGames(0);
    }
}
