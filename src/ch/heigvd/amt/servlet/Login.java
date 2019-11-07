package ch.heigvd.amt.servlet;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet("/login.jsp")
public class Login extends HttpServlet {

    @EJB
    OfficialManager officialUser;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");

        if(action.equals("login")) {

            req.getRequestDispatcher(Consts.JSP_LOGIN).forward(req, resp);
        }
        else if(action.equals("logout")){

            resp.setContentType("text/html;charset=UTF-8");
            req.getSession().invalidate();
            resp.sendRedirect(req.getContextPath()+Consts.SERVLET_TRAIL);
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");
        HttpSession session = req.getSession();
        User user = null;
        resp.setContentType("text/html;charset=UTF-8");

        if(action.equals("login")) {

            String email = req.getParameter("email");
            String password = req.getParameter("password");

            password = Crypto.getCryptoHash(password);
            user = officialUser.connect(email, password);

            if (user != null) {
                session.setAttribute("user", user);
                resp.sendRedirect(req.getContextPath()+Consts.SERVLET_TRAIL);
            } else {
                req.getSession().removeAttribute("user");
                req.getRequestDispatcher(Consts.JSP_LOGIN).forward(req, resp);
            }
        }

}
