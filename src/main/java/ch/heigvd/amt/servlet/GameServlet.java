package ch.heigvd.amt.servlet;

import ch.heigvd.amt.services.dao.GameManager;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "GameServlet", urlPatterns = { "/games" })
public class GameServlet extends HttpServlet {

    @EJB
    private GameManager gameManager;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("games", gameManager.getAll());
        req.getRequestDispatcher("WEB-INF/views/game.jsp").forward(req, resp);
    }
}
