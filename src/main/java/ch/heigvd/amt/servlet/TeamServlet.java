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
        String id = req.getParameter("id");

        if(id != null) {
            req.setAttribute("team", teamManager.getById(Long.parseLong(id)));
            req.getRequestDispatcher("WEB-INF/pages/detail/team.jsp").forward(req, resp);
        } else {
            displayAllTeams(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if(action == null){
            req.setAttribute("error", "No action is specified");
            doGet(req, resp);

            return;
        }

        switch(action){
            case "create":
                createTeam(req, resp);
                break;

            case "delete":
                deleteTeam(req, resp);
                break;
        }
    }

    private void displayAllTeams(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("teams", teamManager.getAll());
        req.getRequestDispatcher("WEB-INF/pages/team.jsp").forward(req, resp);
    }

    private void createTeam(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        long id = teamManager.create(new Team(req.getParameter("name"),
                                              req.getParameter("address"),
                                              req.getParameter("zip"),
                                              req.getParameter("city")));

        if(id == -1)
            req.setAttribute("error", "Unable to create the team");
        else
            req.setAttribute("success", "Team created");

        doGet(req, resp);
    }

    private void deleteTeam(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        if(id != null) {
            boolean success = teamManager.delete(Long.parseLong(id));

            if(success)
                req.setAttribute("success", "Team deleted");
            else
                req.setAttribute("error", "Unable to delete the team");
        }

        displayAllTeams(req, resp);
    }
}
