package com.will.tennis.scoreboard.storage;

import com.will.tennis.scoreboard.dto.MatchScoreDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MatchStorage {
    @Getter
    private static final MatchStorage INSTANCE = new MatchStorage();
    private static final Map<UUID, List<MatchScoreDto>> storage = new HashMap<>();

    public List<MatchScoreDto> getMatchScoreDtos(UUID matchId) {
        List<MatchScoreDto> matchScoreDtos = storage.get(matchId);
        if (matchScoreDtos == null) {
            throw new IllegalStateException();
        }
        return matchScoreDtos;
    }

    public UUID saveMatch(List<MatchScoreDto> matchScoreDtos) {
        UUID matchId = UUID.randomUUID();
        storage.put(matchId, matchScoreDtos);
        return matchId;
    }
}
