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
import java.time.LocalDateTime;
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
    public boolean create(LocalDateTime timestamp, long awayId, long homeId, long refereeId, long umpireId, long chainJudgeId,
                          long lineJudgeId, long backJudgeId, long sideJudgeId, long fieldJudgeId){
        boolean success;
        DateTimeFormatter sqlDateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        try {
            Connection conn = dataSource.getConnection();

            PreparedStatement statement = conn.prepareStatement("INSERT INTO Game (timestamp, awayId, homeId, refereeId, umpireId, " +
                                                                "chainJudgeId, lineJudgeId, backJudgeId, sideJudgeId, fieldJudgeId) " +
                                                                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            statement.setObject(1, timestamp.format(sqlDateTimeFormat));
            statement.setObject(2, awayId);
            statement.setObject(3, homeId);
            statement.setObject(4, refereeId);
            statement.setObject(5, umpireId);
            statement.setObject(6, chainJudgeId);
            statement.setObject(7, lineJudgeId);
            statement.setObject(8, backJudgeId);
            statement.setObject(9, sideJudgeId);
            statement.setObject(10, fieldJudgeId);

            success = statement.execute();

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return success;
    }

    // Read
    public Game getGame(long id){
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
                                officialManager.getOfficial(result.getInt("idReferee")),
                                officialManager.getOfficial(result.getInt("idUmpire")),
                                officialManager.getOfficial(result.getInt("idChainJudge")),
                                officialManager.getOfficial(result.getInt("idLineJudge")),
                                officialManager.getOfficial(result.getInt("idBackJudge")),
                                officialManager.getOfficial(result.getInt("idSideJudge")),
                                officialManager.getOfficial(result.getInt("idFieldJudge")));
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return game;
    }

    // Update
    public boolean update(long id, LocalDateTime timestamp, long awayId, long homeId, long refereeId, long umpireId, long chainJudgeId,
                          long lineJudgeId, long backJudgeId, long sideJudgeId, long fieldJudgeId){
        boolean success;
        DateTimeFormatter sqlDateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        try {
            Connection conn = dataSource.getConnection();

            PreparedStatement statement = conn.prepareStatement("UPDATE Game SET timestamp=?, awayId=?, homeId=?, refereeId=?, " +
                                                                "umpireId=?, chainJudgeId=?, lineJudgeId=?, backJudgeId=?, " +
                                                                "sideJudgeId=?, fieldJudgeId=? " +
                                                                "WHERE id=?");
            statement.setObject(1, timestamp.format(sqlDateTimeFormat));
            statement.setObject(2, awayId);
            statement.setObject(3, homeId);
            statement.setObject(4, refereeId);
            statement.setObject(5, umpireId);
            statement.setObject(6, chainJudgeId);
            statement.setObject(7, lineJudgeId);
            statement.setObject(8, backJudgeId);
            statement.setObject(9, sideJudgeId);
            statement.setObject(10, fieldJudgeId);
            statement.setObject(11, id);

            success = statement.execute();

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return success;
    }

    // Delete
    public boolean delete(long id){
        boolean success;

        Connection conn = null;
        try {
            conn = dataSource.getConnection();

            PreparedStatement statement = conn.prepareStatement("DELETE FROM Game WHERE id=?");
            statement.setObject(1, id);

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
                                   officialManager.getOfficial(result.getInt("idReferee")),
                                   officialManager.getOfficial(result.getInt("idUmpire")),
                                   officialManager.getOfficial(result.getInt("idChainJudge")),
                                   officialManager.getOfficial(result.getInt("idLineJudge")),
                                   officialManager.getOfficial(result.getInt("idBackJudge")),
                                   officialManager.getOfficial(result.getInt("idSideJudge")),
                                   officialManager.getOfficial(result.getInt("idFieldJudge"))));
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return games;
    }
}
