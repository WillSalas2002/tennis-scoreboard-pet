package com.will.tennis.scoreboard.service.game;

import lombok.Getter;

@Getter
public abstract class AbstractGameScore {
    protected boolean finished;
    protected String winner;

    abstract void pointWonBy(String player, String player1, String player2);
    abstract String getScore();
    abstract String getPlayer1Point();
    abstract String getPlayer2Point();
}
