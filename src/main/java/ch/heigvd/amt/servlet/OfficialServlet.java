package ch.heigvd.amt.servlet;

import ch.heigvd.amt.models.Official;
import ch.heigvd.amt.models.Team;
import ch.heigvd.amt.security.PasswordHashing;
import ch.heigvd.amt.services.dao.OfficialManagerLocal;
import ch.heigvd.amt.services.dao.TeamManagerLocal;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "OfficialServlet", urlPatterns = { "/officials" })
public class OfficialServlet extends HttpServlet {

    @EJB
    private OfficialManagerLocal officialManager;

    @EJB
    private TeamManagerLocal teamManager;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        if(id != null) {
            req.setAttribute("official", officialManager.getById(Long.parseLong(id)));
            req.setAttribute("teams", teamManager.getAll());

            req.getRequestDispatcher("WEB-INF/pages/detail/official.jsp").forward(req, resp);
        } else {
            displayAllOfficials(req, resp);
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        HttpSession session = req.getSession();

        switch(action){
            case "update":
                break;

            case "delete":
                deleteOfficial(req, resp);
                break;
        }
    }

    private void displayAllOfficials(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("officials", officialManager.getAll());
        req.getRequestDispatcher("WEB-INF/pages/official.jsp").forward(req, resp);
    }

    private void deleteOfficial(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");

        if(id != null){
            if(officialManager.delete(Long.parseLong(id)))
                req.setAttribute("success", "Official deleted");
            else
                req.setAttribute("error", "Unable to delete this official");
        }

        displayAllOfficials(req, resp);
    }
}
