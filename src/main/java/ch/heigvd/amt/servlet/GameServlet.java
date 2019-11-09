package ch.heigvd.amt.servlet;

import ch.heigvd.amt.models.Game;
import ch.heigvd.amt.models.Official;
import ch.heigvd.amt.models.Team;
import ch.heigvd.amt.services.dao.GameManagerLocal;
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
import java.time.LocalDateTime;

@WebServlet(name = "GameServlet", urlPatterns = { "/games" })
public class GameServlet extends HttpServlet {

    @EJB
    private GameManagerLocal gameManager;

    @EJB
    private OfficialManagerLocal officialManager;

    @EJB
    private TeamManagerLocal teamManager;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("games", gameManager.getAll());
        req.getRequestDispatcher("WEB-INF/pages/game.jsp").forward(req, resp);
    }
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        HttpSession session = req.getSession();
        Official user = (Official) session.getAttribute("user");
        resp.setContentType("text/html;charset=UTF-8");

        if(user.getLevel() == 3){
            if(action.equals("create")) {
                LocalDateTime timestamp = LocalDateTime.parse(req.getParameter("date"));
                Team away = teamManager.get(Integer.parseInt(req.getParameter("away")));
                Team home = teamManager.get(Integer.parseInt(req.getParameter("home")));
                Official referee = officialManager.get(Integer.parseInt(req.getParameter("referee")));
                Official umpire = officialManager.get(Integer.parseInt(req.getParameter("umpire")));
                Official chainJudge = officialManager.get(Integer.parseInt(req.getParameter("chainJudge")));
                Official lineJudge = officialManager.get(Integer.parseInt(req.getParameter("lineJudge")));
                Official backJudge = officialManager.get(Integer.parseInt(req.getParameter("backJudge")));
                Official sideJudge= officialManager.get(Integer.parseInt(req.getParameter("sideJudge")));
                Official fieldJudge = officialManager.get(Integer.parseInt(req.getParameter("fieldJudge")));

                Game newGame = new Game(timestamp, away, home, referee, umpire, chainJudge,
                        lineJudge, backJudge, sideJudge, fieldJudge);
                gameManager.create(newGame);

            }
        }else{

            System.out.println("Unauthorized access request");

        }
    }
}
