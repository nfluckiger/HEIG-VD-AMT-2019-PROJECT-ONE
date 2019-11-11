package app.dao;


import ch.heigvd.amt.models.Game;
import ch.heigvd.amt.models.Official;
import ch.heigvd.amt.models.Team;
import ch.heigvd.amt.services.dao.*;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TestDao {
    @Test
    public void itShouldBePossibleToStoreAndRetrieveUsers(){
        OfficialManagerLocal officialManager = new OfficialManager();
        TeamManagerLocal teamManager = new TeamManager();

        List<Official> users = officialManager.getAll();
        assertEquals(0, users.size());

        Team testTeam = new Team("AMT", "Sous-un-pont 14", "1450", "Ste-Croix");
        teamManager.create(testTeam);

        Official david = new Official("David", "Jaquet", "d@jaquet.ch", "superPassword",1, testTeam);
        officialManager.create(david);
        assertEquals(1, officialManager.getAll().size());

        Official nathan = new Official("Nathan", "Fluckiger", "n@fluckiger.ch", "superPassword",1, testTeam);
        officialManager.create(nathan);
        assertEquals(2, officialManager.getAll().size());
    }

    @Test
    public void getUserShouldReturnObjectClones() {
        OfficialManagerLocal officialManager = new OfficialManager();
        TeamManagerLocal teamManager = new TeamManager();
        Team testTeam = new Team("AMT", "Sous-un-pont 14", "1450", "Ste-Croix");
        teamManager.create(testTeam);
        Official david = new Official("David", "Jaquet", "d@jaquet.ch", "superPassword",1, testTeam);
        long davidid = officialManager.create(david);
        Official davidLoad = officialManager.getById(davidid);
        assertTrue(david != davidLoad);
        assertEquals(david, davidLoad);
    }

    @Test
    public void itShouldBePossibleToUpdateATeam() {
        OfficialManagerLocal officialManager = new OfficialManager();
        TeamManagerLocal teamManager = new TeamManager();
        Team testTeam = new Team("AMT", "Sous-un-pont 14", "1450", "Ste-Croix");
        teamManager.create(testTeam);
        Official david = new Official("David", "Jaquet", "d@jaquet.ch", "superPassword",1, testTeam);
        long davidid = officialManager.create(david);
        Official davidLoad = officialManager.getById(davidid);
        davidLoad.setEmail("pwnstar@jaquet.ch");
        officialManager.update(davidLoad);
        david = officialManager.getById(davidid);

        assertEquals("pwnstar@jaquet.ch", david.getEmail());
        assertEquals(davidLoad, david);
    }
    @Test
    public void itShouldBePossibleToStoreAndRetrieveTeam(){
        TeamManagerLocal teamManager = new TeamManager();

        List<Team> teams = teamManager.getAll();
        assertEquals(0, teams.size());

        Team testTeam = new Team("AMT", "Sous-un-pont 14", "1450", "Ste-Croix");
        teamManager.create(testTeam);

        assertEquals(1, teamManager.getAll().size());

        Team testTeam2 = new Team("MAC", "Sous-un-pont 13", "1400", "Yverdon-les-bains");
        teamManager.create(testTeam2);
        assertEquals(2, teamManager.getAll().size());
    }

    @Test
    public void getTeamShouldReturnObjectClones() {
        TeamManagerLocal teamManager = new TeamManager();
        Team testTeam = new Team("AMT", "Sous-un-pont 14", "1450", "Ste-Croix");
        long teamid = teamManager.create(testTeam);
        Team testTeamLoad = teamManager.getById(teamid);
        assertTrue(testTeam != testTeamLoad);
        assertEquals(testTeam, testTeamLoad);
    }

    @Test
    public void itShouldBePossibleToStoreAndRetrieveGames(){
        OfficialManagerLocal officialManager = new OfficialManager();
        TeamManagerLocal teamManager = new TeamManager();
        GameManagerLocal gameManager = new GameManager();

        List<Game> users = gameManager.getAll();
        assertEquals(0, users.size());

        Team testTeam = new Team("AMT", "Sous-un-pont 14", "1450", "Ste-Croix");
        teamManager.create(testTeam);
        Team testTeam2 = new Team("MAC", "Sous-un-pont 13", "1400", "Yverdon-les-bains");
        teamManager.create(testTeam2);

        Official david = new Official("David", "Jaquet", "d@jaquet.ch", "superPassword",1, testTeam);
        officialManager.create(david);
        Official david2 = new Official("David", "Jaquet", "d2@jaquet.ch", "superPassword",1, testTeam);
        officialManager.create(david2);
        Official david3 = new Official("David", "Jaquet", "d3@jaquet.ch", "superPassword",1, testTeam);
        officialManager.create(david3);
        Official david4 = new Official("David", "Jaquet", "d4@jaquet.ch", "superPassword",1, testTeam);
        officialManager.create(david4);
        Official david5 = new Official("David", "Jaquet", "d5@jaquet.ch", "superPassword",1, testTeam);
        officialManager.create(david5);
        Official david6 = new Official("David", "Jaquet", "d6@jaquet.ch", "superPassword",1, testTeam);
        officialManager.create(david6);
        Official david7 = new Official("David", "Jaquet", "d7@jaquet.ch", "superPassword",1, testTeam);
        officialManager.create(david7);

        LocalDateTime time = LocalDateTime.of(2019,12,31, 23,45);
        Game game = new Game(time, testTeam, testTeam2, david, david2, david3, david4, david5, david6, david7);
        gameManager.create(game);
        assertEquals(1, gameManager.getAll().size());

        time = LocalDateTime.of(2020,12,31, 23,45);
        Game game2 = new Game(time, testTeam, testTeam2, david, david2, david3, david4, david5, david6, david7);
        gameManager.create(game2);
        assertEquals(2, gameManager.getAll().size());
    }

    @Test
    public void getGameShouldReturnObjectClones() {
        OfficialManagerLocal officialManager = new OfficialManager();
        TeamManagerLocal teamManager = new TeamManager();
        GameManagerLocal gameManager = new GameManager();

        Team testTeam = new Team("AMT", "Sous-un-pont 14", "1450", "Ste-Croix");
        teamManager.create(testTeam);
        Team testTeam2 = new Team("MAC", "Sous-un-pont 13", "1400", "Yverdon-les-bains");
        teamManager.create(testTeam2);

        Official david = new Official("David", "Jaquet", "d@jaquet.ch", "superPassword",1, testTeam);
        officialManager.create(david);
        Official david2 = new Official("David", "Jaquet", "d2@jaquet.ch", "superPassword",1, testTeam);
        officialManager.create(david2);
        Official david3 = new Official("David", "Jaquet", "d3@jaquet.ch", "superPassword",1, testTeam);
        officialManager.create(david3);
        Official david4 = new Official("David", "Jaquet", "d4@jaquet.ch", "superPassword",1, testTeam);
        officialManager.create(david4);
        Official david5 = new Official("David", "Jaquet", "d5@jaquet.ch", "superPassword",1, testTeam);
        officialManager.create(david5);
        Official david6 = new Official("David", "Jaquet", "d6@jaquet.ch", "superPassword",1, testTeam);
        officialManager.create(david6);
        Official david7 = new Official("David", "Jaquet", "d7@jaquet.ch", "superPassword",1, testTeam);
        officialManager.create(david7);

        LocalDateTime time = LocalDateTime.of(2019,12,31, 23,45);
        Game game = new Game(time, testTeam, testTeam2, david, david2, david3, david4, david5, david6, david7);
        long gameid = gameManager.create(game);
        Game gameload = gameManager.getById(gameid);

        assertTrue(game != gameload);
        assertEquals(game, gameload);
    }
}
