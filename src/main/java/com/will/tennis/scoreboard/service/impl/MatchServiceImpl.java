package com.will.tennis.scoreboard.service.impl;

import com.will.tennis.scoreboard.dto.MatchDto;
import com.will.tennis.scoreboard.dto.MatchScoreDto;
import com.will.tennis.scoreboard.mapper.MatchMapper;
import com.will.tennis.scoreboard.model.Match;
import com.will.tennis.scoreboard.repository.MatchRepository;
import com.will.tennis.scoreboard.repository.impl.MatchRepositoryImpl;
import com.will.tennis.scoreboard.service.MatchService;
import com.will.tennis.scoreboard.storage.MatchStorage;

import java.util.List;
import java.util.UUID;

import static com.will.tennis.scoreboard.Constants.RECORDS_PER_PAGE;

public class MatchServiceImpl implements MatchService {

    private static final MatchMapper matchMapper = MatchMapper.INSTANCE;
    public static final String NUMBER_PATTERN = "^\\d$";
    private final MatchRepository matchesRepository = new MatchRepositoryImpl();
    private final MatchStorage matchStorage = MatchStorage.getINSTANCE();

    @Override
    public List<MatchDto> findAll(String name, String pageStr) {
        List<Match> matches;

        if (pageStr != null) {
            int page = Integer.parseInt(pageStr);
            int offset = (page - 1) * RECORDS_PER_PAGE;
            matches = matchesRepository.findAll(offset, name);
        } else if (name != null) {
            matches = matchesRepository.findByName(name);
        } else {
            matches = matchesRepository.findAll();
        }

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

    @Override
    public UUID createMatch(String player1Name, String player2Name) {
        MatchScoreDto matchScoreDto1 = new MatchScoreDto(player1Name, "00", 0, 0);
        MatchScoreDto matchScoreDto2 = new MatchScoreDto(player2Name, "00", 0, 0);
        List<MatchScoreDto> matchScoreDtos = List.of(matchScoreDto1, matchScoreDto2);
        return matchStorage.saveMatch(matchScoreDtos);
    }

    @Override
    public List<MatchScoreDto> getMatchScoreDtos(UUID matchId) {
        return matchStorage.getMatchScoreDtos(matchId);
    }

    private static long calculatePageCount(long matchQuantity) {
        return (matchQuantity % RECORDS_PER_PAGE == 0) ?
                matchQuantity / RECORDS_PER_PAGE : (matchQuantity / RECORDS_PER_PAGE) + 1L;
    }

    private static boolean isValid(String pageStr) {
        return pageStr != null && pageStr.matches(NUMBER_PATTERN);
    }
}
