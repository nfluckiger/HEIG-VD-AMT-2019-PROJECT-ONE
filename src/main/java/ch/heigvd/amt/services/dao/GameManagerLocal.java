package ch.heigvd.amt.services.dao;

import ch.heigvd.amt.models.Game;

import javax.ejb.Local;
import java.util.List;

@Local
public interface GameManagerLocal {

    public long create(Game game);

    public int getNbGames();

    public Game getById(long id);

    public List<Game> getAll(int offset, int nb);

    public boolean delete(long id);

}
