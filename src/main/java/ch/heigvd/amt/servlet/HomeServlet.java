package ch.heigvd.amt.servlet;

import ch.heigvd.amt.models.Game;
import ch.heigvd.amt.models.Official;
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
import java.util.List;

/**
 * Servlet to manage the home page of an official
 */
@WebServlet(name = "HomeServlet", urlPatterns = { "/home" })
public class HomeServlet extends HttpServlet {

    @EJB
    OfficialManagerLocal officialManager;

    @EJB
    TeamManagerLocal teamManager;

    /**
     * Get the official and display its home page
     * @param req   Request HTTP
     * @param resp  Response HTTP
     */
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Official user = (Official) session.getAttribute("user");

        List<Game> games = officialManager.getMyFiveNextGames(user.getId());
        req.setAttribute("games", games);

        req.getRequestDispatcher("/WEB-INF/pages/home.jsp").forward(req, resp);
    }

    // TODO : Vérifier si on utilise cette fonction
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
