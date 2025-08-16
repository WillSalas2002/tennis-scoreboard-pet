package com.will.tennis.scoreboard.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MatchScoreModel {
    private String name;
    private String points;
    private int games;
    private int sets;
}
