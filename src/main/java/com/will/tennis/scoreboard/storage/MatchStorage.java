package com.will.tennis.scoreboard.storage;

import com.will.tennis.scoreboard.dto.MatchScoreDto;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MatchStorage {
    @Getter
    private static final MatchStorage INSTANCE = new MatchStorage();
    private static final Map<UUID, MatchScoreDto> storage = new HashMap<>();

    public synchronized MatchScoreDto getMatchScoreDtos(UUID matchId) {
        MatchScoreDto matchScoreDtos = storage.get(matchId);
        if (matchScoreDtos == null) {
            throw new IllegalStateException();
        }
        return matchScoreDtos;
    }

    public synchronized UUID saveMatch(MatchScoreDto matchScoreDtos) {
        UUID matchId = UUID.randomUUID();
        storage.put(matchId, matchScoreDtos);
        return matchId;
    }
}
