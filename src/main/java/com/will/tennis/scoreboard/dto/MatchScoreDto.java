package com.will.tennis.scoreboard.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatchScoreDto {
    private String player1;
    private String player1Points;
    private int player1Games;
    private int player1Sets;
    private String player2;
    private String player2Points;
    private int player2Games;
    private int player2Sets;
    private boolean isFinished;

    public MatchScoreDto(String player1, String player2) {
        this.player1 = player1;
        this.player2 = player2;
    }
}
