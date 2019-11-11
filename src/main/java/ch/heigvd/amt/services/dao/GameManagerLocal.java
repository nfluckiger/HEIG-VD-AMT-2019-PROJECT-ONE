package ch.heigvd.amt.services.dao;

import ch.heigvd.amt.models.Game;

import javax.ejb.Local;
import java.util.List;

/**
 * Manager to implement the DAO for the games
 */
@Local
public interface GameManagerLocal {

    /**
     * Method to create a game in the database
     *
     * @param game  Game to save in the database
     * @return  Return the ID of the created game.
     *          If the game hasn't been create, return -1
     */
    public long create(Game game);

    /**
     * Get the number of the games in the database
     *
     * @return  Number of game in the database in int format
     */
    public int getNbGames();

    /**
     * Retrieve a game by its ID
     *
     * @param id    ID of the game
     * @return  Game with id received in paramater.
     *          Return null if the game cannot be retrieved
     */
    public Game getById(long id);

    /**
     * Get a number of a game order by date (desc) with an offset
     *
     * @param offset    offset of the games
     * @param nb        Number of games that we want to retrieve
     * @return List of all games retrieved
     */
    public List<Game> getAll(int offset, int nb);

    /**
     * Delete a game in the database in function of an ID
     *
     * @param id    ID of the game to delete
     * @return True if the game is deleted, false otherwise
     */
    public boolean delete(long id);

}
