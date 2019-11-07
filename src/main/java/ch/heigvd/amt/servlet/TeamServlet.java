package ch.heigvd.amt.servlet;

import ch.heigvd.amt.services.dao.TeamManager;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "TeamServlet", urlPatterns = { "/teams" })
public class TeamServlet extends HttpServlet {

    @EJB
    private TeamManager teamManager;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("teams", teamManager.getAll());
        req.getRequestDispatcher("WEB-INF/views/team.jsp").forward(req, resp);
    }
}
