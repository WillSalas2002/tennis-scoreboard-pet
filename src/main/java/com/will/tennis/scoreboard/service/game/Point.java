package com.will.tennis.scoreboard.service.game;

import lombok.Getter;

@Getter
public enum Point {
    ZERO("00"),
    FIFTEEN("15"),
    THIRTY("30"),
    FORTY("40"),
    ADVANTAGE("AD"),
    AD_WIN("WIN");

    @Getter
    private final String label;
    Point(String point) {
        this.label = point;
    }

//    public Point fromString(String numericPoint) {
//        return Arrays.stream(values())
//                .filter(t -> t.numericPoint.equals(numericPoint))
//                .findFirst()
//                .orElseThrow();
//    }

    public Point next() {
        return Point.values()[this.ordinal() + 1];
    }
}
