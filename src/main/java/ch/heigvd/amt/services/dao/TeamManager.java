package ch.heigvd.amt.services.dao;

import ch.heigvd.amt.models.Team;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// TODO : TESTER AVEC statement.executeUpdate

@Stateless
public class TeamManager {

    @Resource(lookup = "java:/jdbc/officialLeague")
    private DataSource dataSource;

    // Create
    public boolean create(Team team){
        if(team == null)
            return false;

        boolean success;

        try {
            Connection conn = dataSource.getConnection();

            PreparedStatement statement = conn.prepareStatement("INSERT INTO Team (name, address, zip, city)" +
                                                                "VALUES (?, ?, ?, ?)");
            statement.setObject(1, team.getName());
            statement.setObject(2, team.getAddress());
            statement.setObject(3, team.getZip());
            statement.setObject(4, team.getCity());

            success = statement.execute();

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return success;
    }

    // Read
    public Team get(long id){
        Team team = null;

        try {
            Connection conn = dataSource.getConnection();

            PreparedStatement statement = conn.prepareStatement("SELECT * FROM Team WHERE id=?");
            statement.setObject(1, id);

            ResultSet result = statement.executeQuery();

            if(result.next()){
                team = new Team(id,
                                result.getString("name"),
                                result.getString("address"),
                                result.getString("zip"),
                                result.getString("city"));
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return team;
    }

    // Update
    public boolean update(Team team){
        if(team == null)
            return false;

        boolean success;

        try {
            Connection conn = dataSource.getConnection();

            PreparedStatement statement = conn.prepareStatement("UPDATE Team SET name=?, address=?, zip=?, city=? " +
                                                                "WHERE id=?");
            statement.setObject(1, team.getName());
            statement.setObject(2, team.getAddress());
            statement.setObject(3, team.getZip());
            statement.setObject(4, team.getCity());
            statement.setObject(5, team.getId());

            success = statement.execute();

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return success;
    }

    // Delete
    public boolean delete(Team team){
        if(team == null)
            return false;

        boolean success;

        try {
            Connection conn = dataSource.getConnection();

            PreparedStatement statement = conn.prepareStatement("DELETE FROM Team WHERE id=?");
            statement.setObject(1, team.getId());

            success = statement.execute();

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return success;
    }
}
