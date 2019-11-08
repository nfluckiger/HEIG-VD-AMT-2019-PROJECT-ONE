package ch.heigvd.amt.servlet;

import ch.heigvd.amt.models.Official;
import ch.heigvd.amt.models.Team;
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
        req.setAttribute("officials", officialManager.getAll());
        req.getRequestDispatcher("WEB-INF/views/official.jsp").forward(req, resp);
    }
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("action").equals("register")) {
            officialManager.create(new Official(req.getParameter("firstname"),
                    req.getParameter("lastname"),
                    req.getParameter("email"),
                    req.getParameter("password"),
                    1,                              // New officials start at level 1
                    teamManager.get(Integer.parseInt(req.getParameter("team")))));

            resp.sendRedirect("/home");
        }
//        HttpSession session = req.getSession();
//        Official user = (Official) session.getAttribute("user");
//
//        if(user.getLevel() == 3){

//        }else{
//
//            System.out.println("Unauthorized access request");
//
//        }
    }
}
