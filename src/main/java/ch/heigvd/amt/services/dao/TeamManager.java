package ch.heigvd.amt.services.dao;

import ch.heigvd.amt.models.Team;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Stateless
public class TeamManager implements TeamManagerLocal {

    @Resource(lookup = "java:/jdbc/officialLeague")
    private DataSource dataSource;

    // Create
    public long create(Team team){
        if(team == null)
            return -1;

        long id = -1;

        try {
            Connection conn = dataSource.getConnection();

            PreparedStatement statement = conn.prepareStatement("INSERT INTO Team (name, address, zip, city)" +
                                                                "VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            statement.setObject(1, team.getName());
            statement.setObject(2, team.getAddress());
            statement.setObject(3, team.getZip());
            statement.setObject(4, team.getCity());

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
    public Team getById(long id){
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
    public boolean delete(long id){;

        int nbRowDeleted = 0;

        try {
            Connection conn = dataSource.getConnection();

            PreparedStatement statement = conn.prepareStatement("DELETE FROM Team WHERE id=?");
            statement.setObject(1, id);

            nbRowDeleted = statement.executeUpdate();

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return nbRowDeleted != 0;
    }

    public List<Team> getAll(){
        List<Team> teams = new ArrayList<>();

        try {
            Connection conn = dataSource.getConnection();

            PreparedStatement statement = conn.prepareStatement("SELECT * FROM Team");
            ResultSet result = statement.executeQuery();

            while(result.next()){
                teams.add(new Team(result.getInt("id"),
                                   result.getString("name"),
                                   result.getString("address"),
                                   result.getString("zip"),
                                   result.getString("city")));
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return teams;
    }
}
