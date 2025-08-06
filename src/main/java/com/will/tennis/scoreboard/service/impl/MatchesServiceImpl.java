package com.will.tennis.scoreboard.service.impl;

import com.will.tennis.scoreboard.dto.MatchDto;
import com.will.tennis.scoreboard.mapper.MatchMapper;
import com.will.tennis.scoreboard.model.Match;
import com.will.tennis.scoreboard.repository.impl.MatchesRepositoryImpl;
import com.will.tennis.scoreboard.service.MatchesService;

import java.util.List;

public class MatchesServiceImpl implements MatchesService {
    private static final int RECORDS_PER_PAGE = 5;

    private static final MatchMapper matchMapper = MatchMapper.INSTANCE;
    public static final String NUMBER_PATTERN = "^\\d$";
    private final MatchesRepositoryImpl matchesRepository = new MatchesRepositoryImpl();

    @Override
    public List<MatchDto> findAll(String pageStr) {
        if (pageStr != null && pageStr.matches(NUMBER_PATTERN)) {
            int page = Integer.parseInt(pageStr);
            int offset = (page - 1) * RECORDS_PER_PAGE;
            List<Match> matches = matchesRepository.findAll(RECORDS_PER_PAGE, offset);
            return matchMapper.toDtoList(matches);
        }
        List<Match> matches = matchesRepository.findAll();
        return matchMapper.toDtoList(matches);
    }

    public long getTotalPageCount() {
        long matchQuantity = matchesRepository.getMatchQuantity();
        return (matchQuantity % RECORDS_PER_PAGE == 0) ?
                matchQuantity / RECORDS_PER_PAGE : (matchQuantity / RECORDS_PER_PAGE) + 1L;
    }
}
