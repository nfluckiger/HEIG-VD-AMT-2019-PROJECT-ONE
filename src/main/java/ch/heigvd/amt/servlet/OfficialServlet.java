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
        req.setAttribute("officials", officialManager.getAll());
        req.getRequestDispatcher("WEB-INF/views/official.jsp").forward(req, resp);
    }
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        HttpSession session = req.getSession();
        Official user = (Official) session.getAttribute("user");
        resp.setContentType("text/html;charset=UTF-8");

        if(user.getLevel() == 3){
            if(action.equals("create")) {
                String firstname = req.getParameter("firstname");
                String lastname = req.getParameter("lastname");
                String email = req.getParameter("email");
                String password = req.getParameter("password");
                int level = Integer.parseInt(req.getParameter("level"));
                Team team = teamManager.get(Integer.parseInt(req.getParameter("team")));
                password = PasswordHashing.hashPassword(password);
                Official newOfficial = new Official(firstname, lastname, email, password, level, team);
                officialManager.create(newOfficial);

            }
        }else{

            System.out.println("Unauthorized access request");

        }
    }
}
