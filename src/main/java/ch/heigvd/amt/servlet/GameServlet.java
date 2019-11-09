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
import java.text.ParseException;
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
        String id = req.getParameter("id");

        if(id != null) {
            req.setAttribute("game", gameManager.getById(Long.parseLong(id)));
            req.getRequestDispatcher("WEB-INF/pages/gameDetail.jsp").forward(req, resp);
        } else {
            req.setAttribute("games", gameManager.getAll());
            req.setAttribute("officials", officialManager.getAll());
            req.setAttribute("teams", teamManager.getAll());

            req.getRequestDispatcher("WEB-INF/pages/game.jsp").forward(req, resp);
        }
    }
    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        String action = req.getParameter("action");
//        HttpSession session = req.getSession();
//        Official user = (Official) session.getAttribute("user");
//
//        if(user.getLevel() == 2){
//            if(action.equals("create")) {
        String date = req.getParameter("date");

        if(date.isEmpty()){
            req.setAttribute("error", "You have to choose a valid date");
            doGet(req, resp);

            return;
        }

            LocalDateTime timestamp = LocalDateTime.parse(date);
            Team away = teamManager.getById(Long.parseLong(req.getParameter("away")));
            Team home = teamManager.getById(Long.parseLong(req.getParameter("home")));
            Official referee = officialManager.getById(Long.parseLong(req.getParameter("referee")));
            Official umpire = officialManager.getById(Long.parseLong(req.getParameter("umpire")));
            Official chainJudge = officialManager.getById(Long.parseLong(req.getParameter("chainJudge")));
            Official lineJudge = officialManager.getById(Long.parseLong(req.getParameter("lineJudge")));
            Official backJudge = officialManager.getById(Long.parseLong(req.getParameter("backJudge")));
            Official sideJudge= officialManager.getById(Long.parseLong(req.getParameter("sideJudge")));
            Official fieldJudge = officialManager.getById(Long.parseLong(req.getParameter("fieldJudge")));

            Game newGame = new Game(timestamp, away, home, referee, umpire, chainJudge,
                    lineJudge, backJudge, sideJudge, fieldJudge);
            long id = gameManager.create(newGame);

            req.setAttribute("id", id);
            doGet(req, resp);
//            }
//        }else{
//
//            System.out.println("Unauthorized access request");
//
//        }
    }
}
