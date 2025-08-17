package com.will.tennis.scoreboard.controller;

import com.will.tennis.scoreboard.dto.MatchScoreDto;
import com.will.tennis.scoreboard.service.FinishedMatchPersistenceService;
import com.will.tennis.scoreboard.service.OngoingMatchService;
import com.will.tennis.scoreboard.service.impl.FinishedMatchPersistenceServiceImpl;
import com.will.tennis.scoreboard.service.impl.MatchScoreCalculationService;
import com.will.tennis.scoreboard.service.impl.OngoingMatchServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/match-score")
public class MatchScoreServlet extends HttpServlet {
    private final OngoingMatchService ongoingMatchService = new OngoingMatchServiceImpl();
    private final MatchScoreCalculationService calculationService = new MatchScoreCalculationService();
    private final FinishedMatchPersistenceService persistenceService = new FinishedMatchPersistenceServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        MatchScoreDto matchScoreDto = ongoingMatchService.getMatchScoreDto(UUID.fromString(req.getParameter("matchId")));
        req.setAttribute("matchScoreDto", matchScoreDto);
        req.getRequestDispatcher("match-score.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String scoredPlayerName = req.getParameter("scoredPlayer");
        String matchId = req.getParameter("matchId");
        boolean isGameFinished = calculationService.updateScore(matchId, scoredPlayerName);
        if (isGameFinished) {
            persistenceService.saveMatch(matchId);
        }
        doGet(req, resp);
    }
}
