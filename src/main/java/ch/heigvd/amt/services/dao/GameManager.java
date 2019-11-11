package ch.heigvd.amt.services.dao;

import ch.heigvd.amt.models.Game;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.*;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Stateless
public class GameManager implements GameManagerLocal {

    @Resource(lookup = "java:/jdbc/officialLeague")
    private DataSource dataSource;

    @EJB
    OfficialManagerLocal officialManager;

    @EJB
    TeamManagerLocal teamManager;

    // Create
    @Override
    public long create(Game game){
        if(game == null)
            return -1;

        long id = -1;
        DateTimeFormatter sqlDateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        try {
            Connection conn = dataSource.getConnection();

            PreparedStatement statement = conn.prepareStatement("INSERT INTO Game (timestamp, idTeamAway, idTeamHome, idReferee, idUmpire, " +
                                                                "idChainJudge, idLineJudge, idBackJudge, idSideJudge, idFieldJudge) " +
                                                                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
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

            statement.executeUpdate();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if(generatedKeys.next())
                id = generatedKeys.getLong(1);

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return -1;
        }

        return id;
    }

    // Read
    @Override
    public int getNbGames(){
        int nbGames = 0;

        try {
            Connection conn = dataSource.getConnection();

            PreparedStatement statement = conn.prepareStatement("SELECT COUNT(*) AS nbGames FROM Game");
            ResultSet result = statement.executeQuery();

            if(result.next())
                nbGames = result.getInt("nbGames");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return nbGames;
    };

    @Override
    public Game getById(long id){
        Game game = null;

        try {
            Connection conn = dataSource.getConnection();

            PreparedStatement statement = conn.prepareStatement("SELECT * FROM Game WHERE id=?");
            statement.setObject(1, id);

            ResultSet result = statement.executeQuery();

            if(result.next()){
                game = new Game(id,
                                result.getTimestamp("timestamp").toLocalDateTime(),
                                teamManager.getById(result.getInt("idTeamAway")),
                                teamManager.getById(result.getInt("idTeamHome")),
                                officialManager.getById(result.getInt("idReferee")),
                                officialManager.getById(result.getInt("idUmpire")),
                                officialManager.getById(result.getInt("idChainJudge")),
                                officialManager.getById(result.getInt("idLineJudge")),
                                officialManager.getById(result.getInt("idBackJudge")),
                                officialManager.getById(result.getInt("idSideJudge")),
                                officialManager.getById(result.getInt("idFieldJudge")));
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return game;
    }

    @Override
    public List<Game> getAll(int offset, int nb){
        List<Game> games = new ArrayList<>();

        try {
            Connection conn = dataSource.getConnection();

            PreparedStatement statement = conn.prepareStatement("SELECT * FROM Game ORDER BY timestamp DESC LIMIT ?, ?");
            statement.setObject(1, offset);
            statement.setObject(2, nb);

            ResultSet result = statement.executeQuery();

            while(result.next()){
                games.add(new Game(result.getInt("id"),
                        result.getTimestamp("timestamp").toLocalDateTime(),
                        teamManager.getById(result.getInt("idTeamAway")),
                        teamManager.getById(result.getInt("idTeamHome")),
                        officialManager.getById(result.getInt("idReferee")),
                        officialManager.getById(result.getInt("idUmpire")),
                        officialManager.getById(result.getInt("idChainJudge")),
                        officialManager.getById(result.getInt("idLineJudge")),
                        officialManager.getById(result.getInt("idBackJudge")),
                        officialManager.getById(result.getInt("idSideJudge")),
                        officialManager.getById(result.getInt("idFieldJudge"))));
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return games;
    }

    // Delete
    @Override
    public boolean delete(long id){
        int nbRowDeleted = 0;

        Connection conn;
        try {
            conn = dataSource.getConnection();

            PreparedStatement statement = conn.prepareStatement("DELETE FROM Game WHERE id=?");
            statement.setObject(1, id);

            nbRowDeleted = statement.executeUpdate();

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return nbRowDeleted != 0;
    }

}
