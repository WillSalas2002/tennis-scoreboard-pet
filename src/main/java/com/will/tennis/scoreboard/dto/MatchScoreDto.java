package com.will.tennis.scoreboard.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MatchScoreDto {
    private List<MatchScoreModel> matchScoreModels;
    private boolean isTieBreak;
}
