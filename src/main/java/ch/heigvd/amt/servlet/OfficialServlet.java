package ch.heigvd.amt.servlet;

import ch.heigvd.amt.models.Official;
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

/**
 * Servlet to manage the URI starting with /officials
 */
@WebServlet(name = "OfficialServlet", urlPatterns = { "/officials" })
public class OfficialServlet extends HttpServlet {

    @EJB
    OfficialManagerLocal officialManager;

    @EJB
    TeamManagerLocal teamManager;

    /**
     * Display the games or the detail of a game in function of the parameter id. Manage the get method
     *
     * @param req   Request HTTP
     * @param resp  Response HTTP
     */
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

    /**
     * Manage the post method for the officials
     *
     * @param req   Request HTTP
     * @param resp  Response HTTP
     */
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if(action == null){
            req.setAttribute("error", "No action specified");
            displayAllOfficials(req, resp);

            return;
        }

        String id = req.getParameter("id");
        if(id == null) {
            req.setAttribute("error", "No ID specified");
            displayAllOfficials(req, resp);

            return;
        }

        switch(action){
            case "update":
                updateOfficial(req, resp, Long.parseLong(id));
                break;

            case "delete":
                deleteOfficial(req, resp, Long.parseLong(id));
                break;
        }
    }

    /**
     * Display All the officials stored in the database
     *
     * @param req   Request HTTP
     * @param resp  Response HTTP
     */
    private void displayAllOfficials(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("officials", officialManager.getAll());
        req.getRequestDispatcher("WEB-INF/pages/official.jsp").forward(req, resp);
    }

    /**
     * Update an official
     *
     * @param req   Request HTTP
     * @param resp  Response HTTP
     * @param id    ID of the official to update
     */
    private void updateOfficial(HttpServletRequest req, HttpServletResponse resp, long id) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Official user = (Official) session.getAttribute("user");

        if(user.getLevel() == 3 || user.getId() == id) {
            Official official = officialManager.getById(id);

            official.setFirstname(req.getParameter("firstname"));
            official.setLastname(req.getParameter("lastname"));
            official.setEmail(req.getParameter("email"));
            official.setLevel(Integer.parseInt(req.getParameter("level")));
            official.setTeam(teamManager.getById(Long.parseLong(req.getParameter("team"))));

            String newPassword = req.getParameter("password");
            if(!newPassword.isEmpty())
                official.setPassword(PasswordHashing.hashPassword(newPassword));

            if(officialManager.update(official))
                req.setAttribute("success", "Official updated");
            else
                req.setAttribute("error", "Unable to update the official");

            doGet(req, resp);
        } else {
            req.setAttribute("error", "You cannot update this official");
            displayAllOfficials(req, resp);
        }
    }

    /**
     * Delete an official from the database
     *
     * @param req   Request HTTP
     * @param resp  Response HTTP
     * @param id    ID of the official to remove
     */
    private void deleteOfficial(HttpServletRequest req, HttpServletResponse resp, long id) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Official user = (Official) session.getAttribute("user");

        if(user.getLevel() == 3 && user.getId() != id) {
            if(officialManager.delete(id))
                req.setAttribute("success", "Official deleted");
            else
                req.setAttribute("error", "Unable to delete this official");
        } else {
            req.setAttribute("error", "You cannot delete this official");
        }

        displayAllOfficials(req, resp);
    }
}
