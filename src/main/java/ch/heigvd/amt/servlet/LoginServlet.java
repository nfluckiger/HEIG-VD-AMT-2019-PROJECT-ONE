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
 * Servlet for the sign in/up and the logout of the user
 */
@WebServlet(name = "LoginServlet", urlPatterns = { "/login" })
public class LoginServlet extends HttpServlet {

    @EJB
    public OfficialManagerLocal officialManager;

    @EJB
    TeamManagerLocal teamManager;

    /**
     * Display the login page and log out the user if needed
     * @param req   Request HTTP
     * @param resp  Response HTTP
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        String action = req.getParameter("action");

        if(session != null && action != null && action.equals("logout"))
            session.invalidate();

        req.setAttribute("teams", teamManager.getAll());
        req.getRequestDispatcher("/WEB-INF/pages/login.jsp").forward(req, resp);
    }

    /**
     * Log in (and register if needed) the official, the redirect to the home page
     *
     * @param req   Request HTTP
     * @param resp  Response HTTP
     */
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");
        HttpSession session = req.getSession();
        Official user;

        if(action.equals("login")) {
            String email = req.getParameter("email");
            String password = req.getParameter("password");

            password = PasswordHashing.hashPassword(password);
            System.out.println(password);

            user = officialManager.connect(email, password);

            if (user != null) {
                session.setAttribute("user", user);
                resp.sendRedirect(req.getContextPath() + "/home");
            } else {
                req.setAttribute("error", "Unable to log in");
                doGet(req, resp);
            }
        } else if (action.equals("register")) {
            user = new Official(req.getParameter("firstname"),
                                req.getParameter("lastname"),
                                req.getParameter("emailSignUp"),
                                PasswordHashing.hashPassword(req.getParameter("passwordSignUp")),
                          1,
                                teamManager.getById(Integer.parseInt(req.getParameter("team"))));

            long id = officialManager.create(user);

            if(id != -1){
                user.setId(id);
                session.setAttribute("user", user);
                req.getRequestDispatcher("/WEB-INF/pages/home.jsp").forward(req, resp);
            } else {
                req.setAttribute("error", "Unable to register");
                doGet(req, resp);
            }
        }
    }
}
