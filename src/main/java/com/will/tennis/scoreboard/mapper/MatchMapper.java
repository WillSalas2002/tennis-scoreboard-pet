package com.will.tennis.scoreboard.mapper;

import com.will.tennis.scoreboard.dto.MatchDto;
import com.will.tennis.scoreboard.model.Match;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface MatchMapper {
    MatchMapper INSTANCE = Mappers.getMapper(MatchMapper.class);

    @Mapping(source = "player1.name", target = "player1Name")
    @Mapping(source = "player2.name", target = "player2Name")
    @Mapping(source = "winner.name", target = "winnerName")
    MatchDto toDto(Match match);

    List<MatchDto> toDtoList(List<Match> matches);
}
