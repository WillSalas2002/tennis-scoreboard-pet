package com.will.tennis.scoreboard.service.game;

public class TieBreakScore extends AbstractGameScore {
    private int player1TieBreakPoint = 0;
    private int player2TieBreakPoint = 0;

    public void pointWonBy(String player, String player1, String player2) {
        if (finished) return;

        if (player.equals(player1)) {
            player1TieBreakPoint++;
        } else {
            player2TieBreakPoint++;
        }
        checkTieBreakOver(player1, player2);
    }

    private void checkTieBreakOver(String p1, String p2) {
        if (player1TieBreakPoint >= 6 && player1TieBreakPoint - player2TieBreakPoint >= 2) {
            finished = true;
            winner = p1;
        } else if (player2TieBreakPoint >= 6 && player2TieBreakPoint - player1TieBreakPoint >= 2) {
            finished = true;
            winner = p2;
        }
    }

    public String getPlayer1Point() {
        return String.valueOf(player1TieBreakPoint);
    }

    public String getPlayer2Point() {
        return String.valueOf(player2TieBreakPoint);
    }

    public String getScore() {
        return player1TieBreakPoint + " - " + player2TieBreakPoint;
    }
}
