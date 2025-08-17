package com.will.tennis.scoreboard.controller;

import com.will.tennis.scoreboard.dto.MatchDto;
import com.will.tennis.scoreboard.service.MatchService;
import com.will.tennis.scoreboard.service.impl.MatchServiceImpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/matches")
public class MatchServlet extends HttpServlet {
    private static final MatchService MATCH_SERVICE = new MatchServiceImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String page = req.getParameter("page");
        String name = req.getParameter("filterByName");

        List<MatchDto> matches = MATCH_SERVICE.findAll(name, page);
        long totalPageCount = name == null ? MATCH_SERVICE.getTotalPageCount() : MATCH_SERVICE.getTotalPageCount(name);

        req.setAttribute("matches", matches);
        req.setAttribute("pageCount", totalPageCount);

        req.getRequestDispatcher("matches.jsp").forward(req, resp);
    }
}
