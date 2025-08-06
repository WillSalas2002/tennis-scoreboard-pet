package com.will.tennis.scoreboard.service;

import com.will.tennis.scoreboard.dto.MatchDto;

import java.util.List;

public interface MatchesService {

    List<MatchDto> findAll(String page);
}
