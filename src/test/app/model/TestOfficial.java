package app.model;

import ch.heigvd.amt.models.Official;
import ch.heigvd.amt.models.Team;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class OfficialTest {

    @Test
    public void itShouldBePossibleToCreateAOfficial() {
        Team testTeam = new Team("AMT", "Sous-un-pont 14", "1450", "Ste-Croix");
        Official david = new Official("David", "Jaquet", "d@jaquet.ch", "superPassword",1, testTeam);
        assertEquals("David", david.getFirstname());
        assertEquals("Jaquet", david.getLastname());
        assertEquals("d@jaquet.ch", david.getEmail());
        assertEquals("superPassword", david.getPassword());
        assertEquals(1, david.getLevel());
        assertEquals(testTeam, david.getTeam());
    }
}