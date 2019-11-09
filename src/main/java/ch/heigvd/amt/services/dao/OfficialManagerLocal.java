package ch.heigvd.amt.services.dao;

import ch.heigvd.amt.models.Official;

import javax.ejb.Local;
import java.util.List;

@Local
public interface OfficialManagerLocal {
    public long create(Official official);

    // Read
    public Official getById(long id);

    // Update
    public boolean update(Official official);

    // Delete
    public boolean delete(Official official);

    public Official connect(String email, String password);

    public List<Official> getAll();
}
