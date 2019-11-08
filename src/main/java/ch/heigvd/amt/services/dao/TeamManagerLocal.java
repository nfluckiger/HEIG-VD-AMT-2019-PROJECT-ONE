package ch.heigvd.amt.services.dao;

import ch.heigvd.amt.models.Team;

import javax.ejb.Local;
import java.util.List;

@Local
public interface TeamManagerLocal {

    public long create(Team team);

    public Team get(long id);

    public boolean update(Team team);

    public boolean delete(Team team);

    public List<Team> getAll();
}
