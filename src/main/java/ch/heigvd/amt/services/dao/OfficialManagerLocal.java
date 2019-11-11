package ch.heigvd.amt.services.dao;

import ch.heigvd.amt.models.Game;
import ch.heigvd.amt.models.Official;

import javax.ejb.Local;
import java.util.List;

/**
 * Manager to implement the DAO for the officials
 */
@Local
public interface OfficialManagerLocal {

    /**
     * Method to create an official in the database
     *
     * @param official  Official to save in the database
     * @return Return the ID of the created official.
     *         If the official hasn't been create, return -1
     */
    public long create(Official official);

    /**
     * Retrieve an official by its ID
     *
     * @param id    ID of the official
     * @return  Official with id received in paramater.
     *          Return null if the official cannot be retrieved
     */
    public Official getById(long id);

    /**
     * Get all officials stored in database
     *
     * @return List of all officials retrieved
     */
    public List<Official> getAll();

    /**
     * Get the 5 next games for the official with the ID in parameter
     *
     * @param id    ID of the official
     * @return  List of the games. The list size is between 0 and 5
     */
    public List<Game> getMyFiveNextGames(long id);

    /**
     * Update the official received in parameter
     *
     * @param official  Official to update
     * @return  True if the official has been updated, false otherwise
     */
    public boolean update(Official official);

    /**
     * Delete the official with the ID passed in parameter
     *
     * @param id    ID of the official to remove from database
     * @return  True if the official has been deleted, false otherwise
     */
    public boolean delete(long id);

    /**
     * Check if an official can log in. Check with mail and the password
     *
     * @param email     email of the official
     * @param password  password of the official
     *
     * @return  Official object if the credentials are ok
     */
    public Official connect(String email, String password);
}
