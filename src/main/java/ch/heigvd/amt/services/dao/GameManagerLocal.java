package ch.heigvd.amt.services.dao;

import ch.heigvd.amt.models.Game;

import javax.ejb.Local;
import java.util.List;

@Local
public interface GameManagerLocal {

    public boolean create(Game game);

    public Game get(long id);

    public boolean update(Game game);

    public boolean delete(Game game);

    public List<Game> getAll();

}
