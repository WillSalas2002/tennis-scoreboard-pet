package com.will.tennis.scoreboard.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatchScoreDto {
    private String name;
    private String points;
    private int games;
    private int sets;
}
