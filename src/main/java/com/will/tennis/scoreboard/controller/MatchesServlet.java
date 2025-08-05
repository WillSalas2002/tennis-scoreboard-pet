package com.will.tennis.scoreboard.controller;

import com.will.tennis.scoreboard.dto.MatchDto;
import com.will.tennis.scoreboard.service.impl.MatchesServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/matches")
public class MatchesServlet extends HttpServlet {
    private static final MatchesServiceImpl MATCHES_SERVICE = new MatchesServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<MatchDto> all = MATCHES_SERVICE.findAll();
        req.setAttribute("matches", all);
        req.getRequestDispatcher("matches.jsp").forward(req, resp);
    }
}
