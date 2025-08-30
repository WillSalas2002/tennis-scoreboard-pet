package com.will.tennis.scoreboard.controller;

import com.will.tennis.scoreboard.service.OngoingMatchService;
import com.will.tennis.scoreboard.service.PlayerService;
import com.will.tennis.scoreboard.service.impl.OngoingMatchServiceImpl;
import com.will.tennis.scoreboard.service.impl.PlayerServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

import static com.will.tennis.scoreboard.util.Constants.MATCH_SCORE_REDIRECTION_URL;
import static com.will.tennis.scoreboard.util.Constants.NEW_MATCH_JSP;

@WebServlet("/new-match")
public class NewMatchServlet extends HttpServlet {
    private static final int MIN_CHARS = 3;
    private final PlayerService playerService = new PlayerServiceImpl();
    private final OngoingMatchService ongoingMatchService = new OngoingMatchServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher(NEW_MATCH_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String player1Name = req.getParameter("player1Name");
        String player2Name = req.getParameter("player2Name");

        if (validateNames(player1Name, player2Name)) {
            req.setAttribute("error", "Player names should not be null and name size should be at least 5 characters long.");
            req.getRequestDispatcher(NEW_MATCH_JSP).forward(req, resp);
            return;
        }

        playerService.createPlayersIfNotExist(player1Name, player2Name);
        UUID matchId = ongoingMatchService.createMatch(player1Name, player2Name);

        resp.sendRedirect(MATCH_SCORE_REDIRECTION_URL + matchId);
    }

    private boolean validateNames(String player1, String player2) {
        return (player1 == null || player2 == null || player1.length() < MIN_CHARS || player2.length() < 5);
    }
}
