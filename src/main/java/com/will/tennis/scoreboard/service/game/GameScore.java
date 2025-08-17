package com.will.tennis.scoreboard.service.game;

import lombok.Getter;

import static com.will.tennis.scoreboard.service.game.Point.ADVANTAGE;
import static com.will.tennis.scoreboard.service.game.Point.FORTY;

public class GameScore {
    private Point player1Points = Point.ZERO;
    private Point player2Points = Point.ZERO;
    @Getter
    private boolean finished = false;
    @Getter
    private String winner;

    public void pointWonBy(String player, String player1, String player2) {
        if (finished) return;

        if (player.equals(player1)) {
            if (player2Points == ADVANTAGE) player2Points = FORTY;
            else player1Points = player1Points.next();
        } else {
            if (player1Points == ADVANTAGE) player1Points = FORTY;
            else player2Points = player2Points.next();
        }
        checkGameOver(player1, player2);
    }

    private void checkGameOver(String player1, String player2) {
         if (player1Points.ordinal() >= 4 && player1Points.ordinal() - player2Points.ordinal() >= 2) {
            finished = true;
            winner = player1;
        } else if (player2Points.ordinal() >= 4 && player2Points.ordinal() - player1Points.ordinal() >= 2) {
            finished = true;
            winner = player2;
        }
    }

    public String getScore() {
        if (finished) return winner + " won game";

        if (player1Points.ordinal() >= 3 && player2Points.ordinal() >= 3) {
            if (player1Points == player2Points) {
                return "Deuce";
            } else if (player1Points.ordinal() > player2Points.ordinal()) {
                return "Advantage P1";
            } else {
                return "Advantage P2";
            }
        }

        return player1Points.getLabel() + " - " + player2Points.getLabel();
    }

    public String getPlayer1Point() {
        return player1Points.getLabel();
    }

    public String getPlayer2Point() {
        return player2Points.getLabel();
    }
}
