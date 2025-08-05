package com.will.tennis.scoreboard.service.impl;

import com.will.tennis.scoreboard.dto.MatchDto;
import com.will.tennis.scoreboard.mapper.MatchMapper;
import com.will.tennis.scoreboard.model.Match;
import com.will.tennis.scoreboard.repository.impl.MatchesRepositoryImpl;
import com.will.tennis.scoreboard.service.MatchesService;

import java.util.List;

public class MatchesServiceImpl implements MatchesService {
    private final MatchMapper matchMapper = MatchMapper.INSTANCE;
    private final MatchesRepositoryImpl matchesRepository = new MatchesRepositoryImpl();

    @Override
    public List<MatchDto> findAll() {
        List<Match> matches = matchesRepository.findAll();
        return matchMapper.toDtoList(matches);
    }
}
