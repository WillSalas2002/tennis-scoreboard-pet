package com.will.tennis.scoreboard.service.game;

import com.will.tennis.scoreboard.dto.MatchScoreModel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public abstract class Score {
    @Getter
    boolean isTieBreak = false;
    @Getter
    boolean isGameFinished = false;
    MatchScoreModel scorer;
    MatchScoreModel other;

    protected Score(MatchScoreModel scorer, MatchScoreModel other) {
        this.scorer = scorer;
        this.other = other;
    }

    abstract void updateScore();

    abstract void resetScores(  );
}
