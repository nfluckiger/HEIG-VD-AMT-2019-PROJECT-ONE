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


@WebServlet(name = "LoginServlet", urlPatterns = { "/login" })
public class LoginServlet extends HttpServlet {

    @EJB
    private OfficialManagerLocal officialUser;

    @EJB
    private OfficialManagerLocal officialManager;

    @EJB
    private TeamManagerLocal teamManager;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");
//        HttpSession session = req.getSession();
//        Official user = null;
//        resp.setContentType("text/html;charset=UTF-8");

        if(action.equals("login")) {

            String email = req.getParameter("email");
            String password = req.getParameter("password");

            password = PasswordHashing.hashPassword(password);

//            user = officialUser.connect(email, password);
//
//            if (user != null) {
//                session.setAttribute("user", user);
//                resp.sendRedirect(req.getContextPath() + "/home");
//            } else {
//                req.getSession().removeAttribute("user");
//                req.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(req, resp);
//            }
            System.out.println("login");
        } else if (action.equals("register")) {
            officialManager.create(new Official(req.getParameter("firstname"),
                                                req.getParameter("lastname"),
                                                req.getParameter("email"),
                                                req.getParameter("password"),
                                                1,
                                                teamManager.get(Integer.parseInt(req.getParameter("team")))));
        }

        resp.sendRedirect("home");
    }
}
