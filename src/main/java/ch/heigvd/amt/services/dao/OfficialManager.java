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


    // Create
    public boolean create(Official official){
        if(official == null)
            return false;

        boolean success;

        try {
            Connection conn = dataSource.getConnection();

            PreparedStatement statement = conn.prepareStatement("INSERT INTO Official (firstname, lastname, email, password, level, idTeam) " +
                                                                "VALUES (?, ?, ?, ?, ?, ?)");
            statement.setObject(1, official.getFirstname());
            statement.setObject(2, official.getLastname());
            statement.setObject(3, official.getEmail());
            statement.setObject(4, official.getPassword());
            statement.setObject(5, official.getLevel());
            statement.setObject(6, official.getTeam().getId());

            success = statement.execute();

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return success;
    }

    // Read
    public Official get(long id){
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
                                        teamManager.get(result.getInt("idTeam")));
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return official;
    }

    // Update
    public boolean update(Official official){
        if(official == null)
            return false;

        boolean success;

        try {
            Connection conn = dataSource.getConnection();

            PreparedStatement statement = conn.prepareStatement("UPDATE Official SET firstname=?, lastname=?, email=?, password=?, level=?, teamId=? " +
                                                                "WHERE id=?");
            statement.setObject(1, official.getFirstname());
            statement.setObject(2, official.getLastname());
            statement.setObject(3, official.getEmail());
            statement.setObject(4, official.getPassword());
            statement.setObject(5, official.getLevel());
            statement.setObject(6, official.getTeam().getId());
            statement.setObject(7, official.getId());

            success = statement.execute();

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return success;
    }

    // Delete
    public boolean delete(Official official){
        if(official == null)
            return false;

        boolean success;

        try {
            Connection conn = dataSource.getConnection();

            PreparedStatement statement = conn.prepareStatement("DELETE FROM Official WHERE id=?");
            statement.setObject(1, official.getId());

            success = statement.execute();

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return success;
    }

    public Official connect(String email, String password) {

        Official user = null;

        try{
            Connection connection = dataSource.getConnection();
            PreparedStatement pstmt = connection.prepareStatement("SELECT * FROM user WHERE email=? AND password=?");
            pstmt.setObject(1, email);
            pstmt.setObject(2, password);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                long id = rs.getLong("id");
                String firstname = rs.getString("firstname");
                String lastname = rs.getString("lastname");
                String date = rs.getString("date");
                String emailUser = rs.getString("email");
                String passwordUser = rs.getString("password");
                int level = rs.getInt("level");
                int team = rs.getInt("idTeam");

                user = new Official(id, firstname, lastname, emailUser, password, level, teamManager.get(team));
            }
            connection.close();
        }catch (SQLException ex){

            System.out.println(ex);
        }
        return user;
    }
}
