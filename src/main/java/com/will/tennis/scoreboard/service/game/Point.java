package com.will.tennis.scoreboard.service.game;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Point {
    ZERO("00"),
    FIFTEEN("15"),
    THIRTY("30"),
    FORTY("40"),
    ADVANTAGE("AD");

    private final String numericPoint;
    Point(String point) {
        this.numericPoint = point;
    }

//    public Point fromString(String numericPoint) {
//        return Arrays.stream(values())
//                .filter(t -> t.numericPoint.equals(numericPoint))
//                .findFirst()
//                .orElseThrow();
//    }

    public Point next() {
        int order = this.ordinal();
        if (order == 4) {
            return ZERO;
        }
        return Point.values()[order + 1];
    }
}
