package ch.heigvd.amt.services.dao;

import ch.heigvd.amt.models.Official;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Stateless
public class OfficialManager {

    @Resource(lookup = "java:/jdbc/officialLeague")
    private DataSource dataSource;

    @EJB
    TeamManager teamManager;


    // Create => TODO : Passer la Team au lieu de teamId (meme chose pour l'update et le delete) ?
    public boolean create(String firstname, String lastname, String email, String password, int level, long teamId){
        boolean success;

        try {
            Connection conn = dataSource.getConnection();

            PreparedStatement statement = conn.prepareStatement("INSERT INTO Official (firstname, lastname, email, password, level, idTeam) " +
                                                                "VALUES (?, ?, ?, ?, ?, ?)");
            statement.setObject(1, firstname);
            statement.setObject(2, lastname);
            statement.setObject(3, email);
            statement.setObject(4, password);
            statement.setObject(5, level);
            statement.setObject(6, teamId);

            success = statement.execute();

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return success;
    }

    // Read
    public Official getOfficial(long id){
        Official official = null;

        try {
            Connection conn = dataSource.getConnection();

            PreparedStatement statement = conn.prepareStatement("SELECT * FROM Official WHERE id=?");
            statement.setObject(1, id);

            ResultSet result = statement.executeQuery();

            if(result.next()){
                official = new Official(id,
                                        result.getString("firstname"),
                                        result.getString("lastname"),
                                        result.getString("email"),
                                        result.getString("password"),
                                        result.getInt("level"),
                                        teamManager.getTeam(result.getInt("idTeam")));
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return official;
    }

    // Update
    public boolean update(long id, String firstname, String lastname, String email, String password, int level, long teamId){
        boolean success;

        try {
            Connection conn = dataSource.getConnection();

            PreparedStatement statement = conn.prepareStatement("UPDATE Official SET firstname=?, lastname=?, email=?, password=?, level=?, teamId=? " +
                                                                "WHERE id=?");
            statement.setObject(1, firstname);
            statement.setObject(2, lastname);
            statement.setObject(3, email);
            statement.setObject(4, password);
            statement.setObject(5, level);
            statement.setObject(6, teamId);
            statement.setObject(7, id);

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

        try {
            Connection conn = dataSource.getConnection();

            PreparedStatement statement = conn.prepareStatement("DELETE FROM Official WHERE id=?");
            statement.setObject(1, id);

            success = statement.execute();

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return success;
    }
}
