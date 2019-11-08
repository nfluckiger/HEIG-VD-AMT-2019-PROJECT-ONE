
package app.model;

import ch.heigvd.amt.models.Team;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TeamTest {

    @Test
    public void itShouldBePossibleToCreateAOfficial() {
        Team testTeam = new Team("AMT", "Sous-un-pont 14", "1450", "Ste-Croix");
        assertEquals("AMT", testTeam.getName());
        assertEquals("Sous-un-pont 14", testTeam.getAddress());
        assertEquals("1450", testTeam.getZip());
        assertEquals("Ste-Croix", testTeam.getCity());
    }
}