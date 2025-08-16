package com.will.tennis.scoreboard.service.game;

import com.will.tennis.scoreboard.dto.MatchScoreModel;

public class SetScore extends Score {

    public SetScore(MatchScoreModel scorer, MatchScoreModel other) {
        super(scorer, other);
    }

    @Override
    public void updateScore() {
        scorer.setSets(scorer.getSets() + 1);

        int scorerSetScore = scorer.getSets();

        if (scorerSetScore == 2) {
            this.isGameFinished = true;
        }
    }

    @Override
    void resetScores() {
        // supposed to be empty
    }
}
