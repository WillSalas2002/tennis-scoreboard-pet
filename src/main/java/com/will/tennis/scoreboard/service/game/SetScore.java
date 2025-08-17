package com.will.tennis.scoreboard.service.game;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class SetScore {
    private final List<GameScore> games = new ArrayList<>();
    private int player1Games = 0;
    private int player2Games = 0;
    @Getter
    private boolean finished = false;
    @Getter
    private String winner;

    public SetScore() {
        games.add(new GameScore());
    }

    public void pointWonBy(String player, String p1, String p2) {
        if (finished) return;

        GameScore currentGame = getCurrentGame();
        currentGame.pointWonBy(player, p1, p2);

        if (currentGame.isFinished()) {
            if (currentGame.getWinner().equals(p1)) {
                player1Games++;
            } else {
                player2Games++;
            }
            checkSetOver(p1, p2);
            if (!finished) {
                games.add(new GameScore());
            }
        }
    }

    private void checkSetOver(String p1, String p2) {
        if (player1Games >= 6 && player1Games - player2Games >= 2) {
            finished = true;
            winner = p1;
        } else if (player2Games >= 6 && player2Games - player1Games >= 2) {
            finished = true;
            winner = p2;
        }
    }

    public GameScore getCurrentGame() {
        return games.getLast();
    }

    public int getPlayer1GameScore() {
        return player1Games;
    }

    public int getPlayer2GameScore() {
        return player2Games;
    }

    public String getScore() {
        return player1Games + " - " + player2Games +
                " (" + getCurrentGame().getScore() + ")";
    }
}
