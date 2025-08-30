package com.will.tennis.scoreboard.service.calculator;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class MatchScore {
    private final String player1;
    private final String player2;
    private final List<SetScore> sets = new ArrayList<>();
    @Getter
    private int player1Sets = 0;
    @Getter
    private int player2Sets = 0;
    private int maxSets;
    @Getter
    private boolean finished = false;
    @Getter
    private String winner;

    public MatchScore(String player1, String player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.maxSets = 3;
        sets.add(new SetScore());
    }

    public MatchScore(String player1, String player2, int maxSets) {
        this.player1 = player1;
        this.player2 = player2;
        this.maxSets = maxSets;
        sets.add(new SetScore());
    }

    public void pointWonBy(String player) {
        if (finished) return;

        SetScore currentSet = getCurrentSet();
        currentSet.pointWonBy(player, player1, player2);

        if (currentSet.isFinished()) {
            if (currentSet.getWinner().equals(player1)) {
                player1Sets++;
            } else {
                player2Sets++;
            }
            checkMatchOver();
            if (!finished) {
                sets.add(new SetScore());
            }
        }
    }

    private void checkMatchOver() {
        int matchOverCondition = (maxSets / 2) + 1;
        if (player1Sets == matchOverCondition) {
            finished = true;
            winner = player1;
        } else if (player2Sets == matchOverCondition) {
            finished = true;
            winner = player2;
        }
    }

    public SetScore getCurrentSet() {
        return sets.getLast();
    }

    public AbstractGameScore getCurrentGameScore() {
        return sets.getLast().getCurrentGame();
    }

    public String getScore() {
        return "Sets: " + player1Sets + " - " + player2Sets + ", " +
                "Games: " + getCurrentSet().getScore();
    }
}
