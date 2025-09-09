package com.will.tennis.scoreboard.service.calculator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class TennisScoreCalculatorTest {

    private TennisScoreCalculator tennisScoreCalculator;
    private final String player1 = "Dawletmurat";
    private final String player2 = "Alpamis";

    @BeforeEach
    void setUp() {
        tennisScoreCalculator = new TennisScoreCalculator(player1, player2);
    }

    @Test
    @DisplayName("Should win game when a player wins 4 points")
    void shouldWinGameWhenGot4Points() {
        winGame(player1);

        assertEquals(Point.ZERO.getLabel(), tennisScoreCalculator.getPlayer1PointScore());
        assertEquals(Point.ZERO.getLabel(), tennisScoreCalculator.getPlayer2PointScore());
        assertEquals(1, tennisScoreCalculator.getPlayer1GameScore());
        assertEquals(0, tennisScoreCalculator.getPlayer2GameScore());
    }

    @Test
    @DisplayName("When the points are 40 vs 40 the next point doesn't win game, but gives ADVANTAGE")
    void when40vs40NoOneWonGame() {
        get40Vs40();

        tennisScoreCalculator.pointWonBy(player2);

        assertEquals(Point.FORTY.getLabel(), tennisScoreCalculator.getPlayer1PointScore());
        assertEquals(Point.ADVANTAGE.getLabel(), tennisScoreCalculator.getPlayer2PointScore());
        assertEquals(0, tennisScoreCalculator.getPlayer1GameScore());
        assertEquals(0, tennisScoreCalculator.getPlayer2GameScore());
        assertEquals(0, tennisScoreCalculator.getPlayer1Sets());
        assertEquals(0, tennisScoreCalculator.getPlayer2Sets());
    }

    @Test
    @DisplayName("When the points are 40 vs 40, some player needs to win 2 consecutive points in order to win game")
    void when40vs40NeedToWinTwoConsecutivePoints() {
        get40Vs40();
        // two consecutive points
        tennisScoreCalculator.pointWonBy(player2);
        tennisScoreCalculator.pointWonBy(player2);

        assertEquals(Point.ZERO.getLabel(), tennisScoreCalculator.getPlayer1PointScore());
        assertEquals(Point.ZERO.getLabel(), tennisScoreCalculator.getPlayer2PointScore());
        assertEquals(0, tennisScoreCalculator.getPlayer1GameScore());
        assertEquals(1, tennisScoreCalculator.getPlayer2GameScore());
        assertEquals(0, tennisScoreCalculator.getPlayer1Sets());
        assertEquals(0, tennisScoreCalculator.getPlayer2Sets());
    }

    @Test
    @DisplayName("Should win set when a player wins 6 games")
    void shouldWinSetWhenWon6Games() {
        winSet(player2);

        assertEquals(Point.ZERO.getLabel(), tennisScoreCalculator.getPlayer1PointScore());
        assertEquals(Point.ZERO.getLabel(), tennisScoreCalculator.getPlayer2PointScore());
        assertEquals(0, tennisScoreCalculator.getPlayer1GameScore());
        assertEquals(0, tennisScoreCalculator.getPlayer2GameScore());
        assertEquals(0, tennisScoreCalculator.getPlayer1Sets());
        assertEquals(1, tennisScoreCalculator.getPlayer2Sets());
    }

    @Test
    @DisplayName("Should win set when a player wins 6 games")
    void shouldWinMatchWhenWon6Sets() {
        winMatch(player1);

        assertEquals(Point.ADVANTAGE.getLabel(), tennisScoreCalculator.getPlayer1PointScore());
        assertEquals(Point.ZERO.getLabel(), tennisScoreCalculator.getPlayer2PointScore());
        assertEquals(6, tennisScoreCalculator.getPlayer1GameScore());
        assertEquals(0, tennisScoreCalculator.getPlayer2GameScore());
        assertEquals(2, tennisScoreCalculator.getPlayer1Sets());
        assertEquals(0, tennisScoreCalculator.getPlayer2Sets());
        assertEquals(player1, tennisScoreCalculator.getWinner());
    }

    @Test
    @DisplayName("Should play tie break when set scores are 6 vs 6")
    void shouldPlayTieBreakWhenSetScores6Vs6() {
        getTieBreakCondition();

        assertEquals("0", tennisScoreCalculator.getPlayer1PointScore());
        assertEquals("0", tennisScoreCalculator.getPlayer2PointScore());
        assertEquals(6, tennisScoreCalculator.getPlayer1GameScore());
        assertEquals(6, tennisScoreCalculator.getPlayer2GameScore());
        assertEquals(0, tennisScoreCalculator.getPlayer1Sets());
        assertEquals(0, tennisScoreCalculator.getPlayer2Sets());
        assertNull(tennisScoreCalculator.getWinner());
    }

    @Test
    @DisplayName("Should win set when scored >= 6 and the difference in scores is 2, in tie-break condition")
    void shouldWinTieBreak() {
        getTieBreakCondition();
        getTieBreakWinCondition(player1);

        assertEquals(Point.ZERO.getLabel(), tennisScoreCalculator.getPlayer1PointScore());
        assertEquals(Point.ZERO.getLabel(), tennisScoreCalculator.getPlayer2PointScore());
        assertEquals(0, tennisScoreCalculator.getPlayer1GameScore());
        assertEquals(0, tennisScoreCalculator.getPlayer2GameScore());
        assertEquals(1, tennisScoreCalculator.getPlayer1Sets());
        assertEquals(0, tennisScoreCalculator.getPlayer2Sets());
        assertNull(tennisScoreCalculator.getWinner());
    }

    @Test
    @DisplayName("Should NOT win set when difference is not 2, in tie-break condition")
    void shouldNotWinTieBreak() {
        getTieBreakCondition();

        tennisScoreCalculator.pointWonBy(player1);
        tennisScoreCalculator.pointWonBy(player1);
        tennisScoreCalculator.pointWonBy(player1);
        tennisScoreCalculator.pointWonBy(player1);
        tennisScoreCalculator.pointWonBy(player1);

        tennisScoreCalculator.pointWonBy(player2);
        tennisScoreCalculator.pointWonBy(player2);
        tennisScoreCalculator.pointWonBy(player2);
        tennisScoreCalculator.pointWonBy(player2);
        tennisScoreCalculator.pointWonBy(player2);

        tennisScoreCalculator.pointWonBy(player1);

        assertEquals("6", tennisScoreCalculator.getPlayer1PointScore());
        assertEquals("5", tennisScoreCalculator.getPlayer2PointScore());
        assertEquals(6, tennisScoreCalculator.getPlayer1GameScore());
        assertEquals(6, tennisScoreCalculator.getPlayer2GameScore());
        assertEquals(0, tennisScoreCalculator.getPlayer1Sets());
        assertEquals(0, tennisScoreCalculator.getPlayer2Sets());
        assertNull(tennisScoreCalculator.getWinner());
    }

    private void getTieBreakWinCondition(String player) {
        tennisScoreCalculator.pointWonBy(player);
        tennisScoreCalculator.pointWonBy(player);
        tennisScoreCalculator.pointWonBy(player);
        tennisScoreCalculator.pointWonBy(player);
        tennisScoreCalculator.pointWonBy(player);
        tennisScoreCalculator.pointWonBy(player);
    }

    private void getTieBreakCondition() {
        winGame(player1);
        winGame(player1);
        winGame(player1);
        winGame(player1);
        winGame(player1);

        winGame(player2);
        winGame(player2);
        winGame(player2);
        winGame(player2);
        winGame(player2);

        // 6 vs 6
        winGame(player1);
        winGame(player2);
    }

    private void winGame(String player) {
        tennisScoreCalculator.pointWonBy(player);
        tennisScoreCalculator.pointWonBy(player);
        tennisScoreCalculator.pointWonBy(player);
        tennisScoreCalculator.pointWonBy(player);
    }

    private void get40Vs40() {
        tennisScoreCalculator.pointWonBy(player1);
        tennisScoreCalculator.pointWonBy(player1);
        tennisScoreCalculator.pointWonBy(player1);
        tennisScoreCalculator.pointWonBy(player2);
        tennisScoreCalculator.pointWonBy(player2);
        tennisScoreCalculator.pointWonBy(player2);
    }

    private void winSet(String player) {
        winGame(player);
        winGame(player);
        winGame(player);
        winGame(player);
        winGame(player);
        winGame(player);
    }

    private void winMatch(String player) {
        winSet(player);
        winSet(player);
    }
}