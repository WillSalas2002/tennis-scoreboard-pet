package com.will.tennis.scoreboard.service.game;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class MatchScore {
    private String player1;
    private String player2;
    private final List<SetScore> sets = new ArrayList<>();
    @Getter
    private int player1Sets = 0;
    @Getter
    private int player2Sets = 0;
    private final int MAX_SETS = 3;
    @Getter
    private boolean finished = false;
    @Getter
    private String winner;

    public MatchScore() {
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
        if (player1Sets == 2) {
            finished = true;
            winner = player1;
        } else if (player2Sets == 2) {
            finished = true;
            winner = player2;
        }
    }

    public void setPlayers(String p1, String p2) {
        this.player1 = p1;
        this.player2 = p2;
    }

    public SetScore getCurrentSet() {
        return sets.getLast();
    }

    public GameScore getCurrenGameScore() {
        return sets.getLast().getCurrentGame();
    }

    public String getScore() {
        return "Sets: " + player1Sets + " - " + player2Sets + ", " +
                "Games: " + getCurrentSet().getScore();
    }
}
