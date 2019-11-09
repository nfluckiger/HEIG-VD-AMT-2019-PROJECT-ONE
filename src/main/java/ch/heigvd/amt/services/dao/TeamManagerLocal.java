package ch.heigvd.amt.services.dao;

import ch.heigvd.amt.models.Team;

import javax.ejb.Local;
import java.util.List;

@Local
public interface TeamManagerLocal {

    public long create(Team team);

    public Team getById(long id);

    public boolean update(Team team);

    public boolean delete(long id);

    public List<Team> getAll();
}
