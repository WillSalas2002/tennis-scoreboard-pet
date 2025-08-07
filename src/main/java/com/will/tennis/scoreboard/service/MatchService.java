package com.will.tennis.scoreboard.service;

import com.will.tennis.scoreboard.dto.MatchDto;

import java.util.List;

public interface MatchService {

    List<MatchDto> findAll(String name, String pageStr);

    long getTotalPageCount();

    long getTotalPageCount(String name);
}
