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

    private final int nbGamesToDisplay = 10;

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
            req.getRequestDispatcher("WEB-INF/pages/detail/game.jsp").forward(req, resp);
        } else {
            displayAllGames(req, resp);
        }
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // TODO : Uncomment to filter the right server side
//        HttpSession session = req.getSession();
//        Official user = (Official) session.getAttribute("user");
//
//        if(user.getLevel() < 3){
//            req.setAttribute("error", "You do not have the right to do that");
//            displayAllGames(req, resp);
//
//            return;
//        }

        String action = req.getParameter("action");

        if(action == null){
            req.setAttribute("error", "No action specified");
            displayAllGames(req, resp);

            return;
        }

        switch(action){
            case "create":
                createGame(req, resp);
                break;

            case "delete":
                deleteGame(req, resp);
                break;
        }
    }

    private void displayAllGames(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String page = req.getParameter("page");
        int gmNbGames = gameManager.getNbGames();
        int offset;


        if(page == null || page.isEmpty() || Integer.parseInt(page) < 1)
            offset = 0;
        else
            offset = Math.min((Integer.parseInt(page) - 1) * nbGamesToDisplay, gmNbGames);

        req.setAttribute("nbGames", gmNbGames);
        req.setAttribute("nbTabs", (int)(Math.ceil(gmNbGames / (double)nbGamesToDisplay)));
        req.setAttribute("games", gameManager.getAll(offset, nbGamesToDisplay));
        req.setAttribute("officials", officialManager.getAll());
        req.setAttribute("teams", teamManager.getAll());

        req.getRequestDispatcher("WEB-INF/pages/game.jsp").forward(req, resp);
    }

    private void createGame(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String date = req.getParameter("date");

        if (date.isEmpty()) {
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
        Official sideJudge = officialManager.getById(Long.parseLong(req.getParameter("sideJudge")));
        Official fieldJudge = officialManager.getById(Long.parseLong(req.getParameter("fieldJudge")));

        Game newGame = new Game(timestamp, away, home, referee, umpire, chainJudge,
                lineJudge, backJudge, sideJudge, fieldJudge);
        long id = gameManager.create(newGame);

        if(id == -1)
            req.setAttribute("error", "Unable to create the Game");
        else
            req.setAttribute("success", "Game created");

        doGet(req, resp);
    }

    private void deleteGame(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if(id != null) {
            boolean success = gameManager.delete(Long.parseLong(id));
            if(success)
                req.setAttribute("success", "Game deleted");
            else
                req.setAttribute("error", "Unable to delete the game");

        }

        displayAllGames(req, resp);
    }
}
