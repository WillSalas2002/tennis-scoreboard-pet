package com.will.tennis.scoreboard.mapper;

import com.will.tennis.scoreboard.dto.PlayerDto;
import com.will.tennis.scoreboard.model.Player;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PlayerMapper {
    PlayerMapper INSTANCE = Mappers.getMapper(PlayerMapper.class);

    @Mapping(source = "name", target = "name")
    PlayerDto toDto(Player player);
}
