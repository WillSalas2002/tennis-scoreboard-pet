package com.will.tennis.scoreboard.controller;

import com.will.tennis.scoreboard.service.PlayerService;
import com.will.tennis.scoreboard.service.impl.PlayerServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/new-match")
public class NewMatchServlet extends HttpServlet {
    private final PlayerService playerService = new PlayerServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("new-match.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String player1Name = req.getParameter("player1Name");
        String player2Name = req.getParameter("player2Name");

        playerService.createPlayersIfNotExist(player1Name, player2Name);

        req.setAttribute("player1Name", player1Name);
        req.setAttribute("player2Name", player2Name);

        req.getRequestDispatcher("match-score.jsp").forward(req, resp);
    }
}
