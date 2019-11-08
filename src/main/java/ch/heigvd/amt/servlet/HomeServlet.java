package ch.heigvd.amt.servlet;

import ch.heigvd.amt.models.Official;
import ch.heigvd.amt.services.dao.OfficialManagerLocal;
import ch.heigvd.amt.services.dao.TeamManagerLocal;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "OfficialServlet", urlPatterns = { "/home" })
public class HomeServlet extends HttpServlet {

    @EJB
    OfficialManagerLocal officialManager;

    @EJB
    TeamManagerLocal teamManager;

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/home.jsp").forward(req, resp);
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        doGet(req, resp);
    }
}
