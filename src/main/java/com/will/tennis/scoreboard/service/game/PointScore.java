package com.will.tennis.scoreboard.service.game;

import com.will.tennis.scoreboard.dto.MatchScoreModel;

import static com.will.tennis.scoreboard.service.game.Point.ADVANTAGE;
import static com.will.tennis.scoreboard.service.game.Point.FORTY;
import static com.will.tennis.scoreboard.service.game.Point.ZERO;
import static com.will.tennis.scoreboard.service.game.Point.valueOf;

public class PointScore extends Score {

    public PointScore(MatchScoreModel scorer, MatchScoreModel other) {
        super(scorer, other);
    }

    @Override
    public void updateScore() {
        Point scorerPoint = valueOf(scorer.getPoints());
        Point otherPoint = valueOf(other.getPoints());

        scorer.setPoints(scorerPoint.next().name());

        if ((scorerPoint.equals(ADVANTAGE) && otherPoint.ordinal() < ADVANTAGE.ordinal()) ||
                (scorerPoint.equals(FORTY) && otherPoint.ordinal() < FORTY.ordinal())) {
            resetScores();
            new GameScore(scorer, other).updateScore();
        }

        if (scorerPoint.equals(FORTY) && otherPoint.equals(ADVANTAGE)) {
            other.setPoints(FORTY.name());
            scorer.setPoints(FORTY.name());
        }
    }

    @Override
    public void resetScores() {
        scorer.setPoints(ZERO.name());
        other.setPoints(ZERO.name());
    }
}
