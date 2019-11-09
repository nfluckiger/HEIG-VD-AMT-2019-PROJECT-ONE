package ch.heigvd.amt.servlet;

import ch.heigvd.amt.models.Team;
import ch.heigvd.amt.services.dao.TeamManagerLocal;

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
    private TeamManagerLocal teamManager;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("teams", teamManager.getAll());
        req.getRequestDispatcher("WEB-INF/pages/team.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = teamManager.create(new Team(req.getParameter("name"),
                                    req.getParameter("address"),
                                    req.getParameter("zip"),
                                    req.getParameter("city")));

        req.setAttribute("id", id);
        doGet(req, resp);
    }
}
