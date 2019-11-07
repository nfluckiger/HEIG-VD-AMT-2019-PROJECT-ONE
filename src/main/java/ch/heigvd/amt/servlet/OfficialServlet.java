package ch.heigvd.amt.servlet;

import ch.heigvd.amt.services.dao.OfficialManager;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "OfficialServlet", urlPatterns = { "/officials" })
public class OfficialServlet extends HttpServlet {

    @EJB
    OfficialManager officialManager;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("officials", officialManager.getAll());
        req.getRequestDispatcher("WEB-INF/views/official.jsp").forward(req, resp);
    }
}
