package com.will.tennis.scoreboard.service.game;

public class TennisMatchService {
    private MatchScore match;

    public TennisMatchService() {
        this.match = new MatchScore();
    }

    public void pointWonBy(String player) {
        match.pointWonBy(player);
    }

    public int getPlayer1Sets() {
        return match.getPlayer1Sets();
    }

    public int getPlayer2Sets() {
        return match.getPlayer2Sets();
    }

    public int getPlayer1GameScore() {
        return match.getCurrentSet().getPlayer1GameScore();
    }

    public int getPlayer2GameScore() {
        return match.getCurrentSet().getPlayer2GameScore();
    }

    public String getPlayer1PointScore() {
        return match.getCurrentSet().getCurrentGame().getPlayer1Point();
    }

    public String getPlayer2PointScore() {
        return match.getCurrentSet().getCurrentGame().getPlayer2Point();
    }

    public boolean isFinished() {
        return match.isFinished();
    }

    public String getWinner() {
        return match.getWinner();
    }

    public void setPlayers(String p1, String p2) {
        this.match.setPlayers(p1, p2);
    }
}
