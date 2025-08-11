package com.will.tennis.scoreboard.controller;

import com.will.tennis.scoreboard.service.MatchService;
import com.will.tennis.scoreboard.service.PlayerService;
import com.will.tennis.scoreboard.service.impl.MatchServiceImpl;
import com.will.tennis.scoreboard.service.impl.PlayerServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.UUID;

@WebServlet("/new-match")
public class NewMatchServlet extends HttpServlet {
    private final PlayerService playerService = new PlayerServiceImpl();
    private final MatchService matchService = new MatchServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("new-match.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String player1Name = req.getParameter("player1Name");
        String player2Name = req.getParameter("player2Name");

        playerService.createPlayersIfNotExist(player1Name, player2Name);
        UUID matchId = matchService.createMatch(player1Name, player2Name);
        resp.sendRedirect("http://localhost:9090/tennis-scoreboard/match-score?matchId=" + matchId);
    }
}
