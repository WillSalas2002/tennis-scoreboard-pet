package com.will.tennis.scoreboard.service.impl;

import com.will.tennis.scoreboard.dto.MatchDto;
import com.will.tennis.scoreboard.mapper.MatchMapper;
import com.will.tennis.scoreboard.model.Match;
import com.will.tennis.scoreboard.repository.MatchRepository;
import com.will.tennis.scoreboard.repository.impl.MatchRepositoryImpl;
import com.will.tennis.scoreboard.service.MatchService;

import java.util.List;

public class MatchServiceImpl implements MatchService {
    private static final int RECORDS_PER_PAGE = 5;

    private static final MatchMapper matchMapper = MatchMapper.INSTANCE;
    public static final String NUMBER_PATTERN = "^\\d$";
    private final MatchRepository matchesRepository = new MatchRepositoryImpl();

    @Override
    public List<MatchDto> findAll(String name, String pageStr) {

        if (name != null) {
            return matchMapper.toDtoList(matchesRepository.findByName(name));
        }

        if (isValid(pageStr)) {
            int page = Integer.parseInt(pageStr);
            int offset = (page - 1) * RECORDS_PER_PAGE;
            List<Match> matches = matchesRepository.findAll(RECORDS_PER_PAGE, offset);
            return matchMapper.toDtoList(matches);
        }
        List<Match> matches = matchesRepository.findAll();
        return matchMapper.toDtoList(matches);
    }

    @Override
    public long getTotalPageCount() {
        long matchQuantity = matchesRepository.getMatchQuantity();
        return calculatePageCount(matchQuantity);
    }

    @Override
    public long getTotalPageCount(String name) {
        long matchQuantity = matchesRepository.getMatchQuantity(name);
        return calculatePageCount(matchQuantity);
    }

    private static long calculatePageCount(long matchQuantity) {
        return (matchQuantity % RECORDS_PER_PAGE == 0) ?
                matchQuantity / RECORDS_PER_PAGE : (matchQuantity / RECORDS_PER_PAGE) + 1L;
    }

    private static boolean isValid(String pageStr) {
        return pageStr != null && pageStr.matches(NUMBER_PATTERN);
    }
}
