package app.model;

import ch.heigvd.amt.models.Game;
import ch.heigvd.amt.models.Official;
import ch.heigvd.amt.models.Team;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

/**
 * Test of the Game model
 */
class GameTest {

    /**
     * Create some teams, some officials and some game. Use the getters in the assert
     */
    @Test
    public void itShouldBePossibleToCreateAGame() {
        Team testTeam = new Team("AMT", "Sous-un-pont 14", "1450", "Ste-Croix");
        Team testTeam2 = new Team("MAC", "Sous-un-pont 13", "1400", "Yverdon-les-bains");

        Official david = new Official("David", "Jaquet", "d@jaquet.ch", "superPassword",1, testTeam);
        Official david2 = new Official("David", "Jaquet", "d2@jaquet.ch", "superPassword",1, testTeam);
        Official david3 = new Official("David", "Jaquet", "d3@jaquet.ch", "superPassword",1, testTeam);
        Official david4 = new Official("David", "Jaquet", "d4@jaquet.ch", "superPassword",1, testTeam);
        Official david5 = new Official("David", "Jaquet", "d5@jaquet.ch", "superPassword",1, testTeam);
        Official david6 = new Official("David", "Jaquet", "d6@jaquet.ch", "superPassword",1, testTeam);
        Official david7 = new Official("David", "Jaquet", "d7@jaquet.ch", "superPassword",1, testTeam);

        LocalDateTime time = LocalDateTime.of(2019,12,31, 23,45);
        Game game = new Game(time, testTeam, testTeam2, david, david2, david3, david4, david5, david6, david7);

        assertEquals(time, game.getTimestamp());
        assertEquals(testTeam, game.getAway());
        assertEquals(testTeam2, game.getHome());
        assertEquals(david, game.getReferee());
        assertEquals(david2, game.getUmpire());
        assertEquals(david3, game.getChainJudge());
        assertEquals(david4, game.getLineJudge());
        assertEquals(david5, game.getBackJudge());
        assertEquals(david6, game.getSideJudge());
        assertEquals(david7, game.getFieldJudge());
    }
}