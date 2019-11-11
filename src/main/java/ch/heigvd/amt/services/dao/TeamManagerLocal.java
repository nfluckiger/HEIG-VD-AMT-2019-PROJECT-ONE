package ch.heigvd.amt.services.dao;

import ch.heigvd.amt.models.Team;

import javax.ejb.Local;
import java.util.List;

/**
 * Manager to implement the DAO for the teams
 */
@Local
public interface TeamManagerLocal {

    /**
     * Method to create a team in the database
     *
     * @param team  Team to save in the database
     * @return Return the ID of the created game.
     *         If the game hasn't been create, return -1
     */
    public long create(Team team);

    /**
     * Retrieve a team by its ID
     *
     * @param id    ID of the team
     * @return  Team created
     */
    public Team getById(long id);

    /**
     * Get all the teams stored in the database
     *
     * @return  List of all the teams
     */
    public List<Team> getAll();

    /**
     * Delete the team with ID received in parameter
     *
     * @param id    ID of the team to delete
     * @return      True if the team is deleted, false otherwise
     */
    public boolean delete(long id);
}
