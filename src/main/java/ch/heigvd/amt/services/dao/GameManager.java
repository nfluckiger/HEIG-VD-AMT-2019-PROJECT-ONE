package ch.heigvd.amt.services.dao;

import ch.heigvd.amt.models.Game;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Stateless
public class GameManager {

    @Resource(lookup = "java:/jdbc/officialLeague")
    private DataSource dataSource;

    @EJB
    OfficialManager officialManager;

    @EJB
    TeamManager teamManager;

    // Create
    public boolean create(Game game){
        if(game == null)
            return false;

        boolean success;
        DateTimeFormatter sqlDateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        try {
            Connection conn = dataSource.getConnection();

            PreparedStatement statement = conn.prepareStatement("INSERT INTO Game (timestamp, awayId, homeId, refereeId, umpireId, " +
                                                                "chainJudgeId, lineJudgeId, backJudgeId, sideJudgeId, fieldJudgeId) " +
                                                                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            statement.setObject(1, game.getTimestamp().format(sqlDateTimeFormat));
            statement.setObject(2, game.getAway().getId());
            statement.setObject(3, game.getHome().getId());
            statement.setObject(4, game.getReferee().getId());
            statement.setObject(5, game.getUmpire().getId());
            statement.setObject(6, game.getChainJudge().getId());
            statement.setObject(7, game.getLineJudge().getId());
            statement.setObject(8, game.getBackJudge().getId());
            statement.setObject(9, game.getSideJudge().getId());
            statement.setObject(10, game.getFieldJudge().getId());

            success = statement.execute();

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return success;
    }

    // Read
    public Game get(long id){
        Game game = null;

        try {
            Connection conn = dataSource.getConnection();

            PreparedStatement statement = conn.prepareStatement("SELECT * FROM Game WHERE id=?");
            statement.setObject(1, id);

            ResultSet result = statement.executeQuery();

            if(result.next()){
                game = new Game(id,
                                result.getTimestamp("timestamp").toLocalDateTime(),
                                teamManager.get(result.getInt("idTeamAway")),
                                teamManager.get(result.getInt("idTeamHome")),
                                officialManager.get(result.getInt("idReferee")),
                                officialManager.get(result.getInt("idUmpire")),
                                officialManager.get(result.getInt("idChainJudge")),
                                officialManager.get(result.getInt("idLineJudge")),
                                officialManager.get(result.getInt("idBackJudge")),
                                officialManager.get(result.getInt("idSideJudge")),
                                officialManager.get(result.getInt("idFieldJudge")));
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return game;
    }

    // Update
    public boolean update(Game game){
        if(game == null)
            return false;

        boolean success;
        DateTimeFormatter sqlDateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        try {
            Connection conn = dataSource.getConnection();

            PreparedStatement statement = conn.prepareStatement("UPDATE Game SET timestamp=?, awayId=?, homeId=?, refereeId=?, " +
                                                                "umpireId=?, chainJudgeId=?, lineJudgeId=?, backJudgeId=?, " +
                                                                "sideJudgeId=?, fieldJudgeId=? " +
                                                                "WHERE id=?");
            statement.setObject(1, game.getTimestamp().format(sqlDateTimeFormat));
            statement.setObject(2, game.getAway().getId());
            statement.setObject(3, game.getHome().getId());
            statement.setObject(4, game.getReferee().getId());
            statement.setObject(5, game.getUmpire().getId());
            statement.setObject(6, game.getChainJudge().getId());
            statement.setObject(7, game.getLineJudge().getId());
            statement.setObject(8, game.getBackJudge().getId());
            statement.setObject(9, game.getSideJudge().getId());
            statement.setObject(10, game.getFieldJudge().getId());
            statement.setObject(11, game.getId());

            success = statement.execute();

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return success;
    }

    // Delete
    public boolean delete(Game game){
        if(game == null)
            return false;

        boolean success;

        Connection conn = null;
        try {
            conn = dataSource.getConnection();

            PreparedStatement statement = conn.prepareStatement("DELETE FROM Game WHERE id=?");
            statement.setObject(1, game.getId());

            success = statement.execute();

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return success;
    }

    public List<Game> getAll(){
        List<Game> games = new ArrayList<>();

        try {
            Connection conn = dataSource.getConnection();

            PreparedStatement statement = conn.prepareStatement("SELECT * FROM Game");
            ResultSet result = statement.executeQuery();

            while(result.next()){
                games.add(new Game(result.getInt("id"),
                                   result.getTimestamp("timestamp").toLocalDateTime(),
                                   teamManager.get(result.getInt("idTeamAway")),
                                   teamManager.get(result.getInt("idTeamHome")),
                                   officialManager.get(result.getInt("idReferee")),
                                   officialManager.get(result.getInt("idUmpire")),
                                   officialManager.get(result.getInt("idChainJudge")),
                                   officialManager.get(result.getInt("idLineJudge")),
                                   officialManager.get(result.getInt("idBackJudge")),
                                   officialManager.get(result.getInt("idSideJudge")),
                                   officialManager.get(result.getInt("idFieldJudge"))));
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return games;
    }
}
