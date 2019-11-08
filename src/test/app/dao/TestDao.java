//package app.dao;
//
//
//import ch.heigvd.amt.models.Game;
//import ch.heigvd.amt.models.Official;
//import ch.heigvd.amt.models.Team;
//import ch.heigvd.amt.services.dao.*;
//import org.junit.Test;
//
//import java.time.LocalDateTime;
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//public class TestDao {
//    @Test
//    void itShouldBePossibleToStoreAndRetrieveUsers(){
//        OfficialManagerLocal officialManager = new OfficialManager();
//        TeamManagerLocal teamManager = new TeamManager();
//
//        List<Official> users = officialManager.getAll();
//        assertEquals(0, users.size());
//
//        Team testTeam = new Team("AMT", "Sous-un-pont 14", "1450", "Ste-Croix");
//        teamManager.create(testTeam);
//
//        Official david = new Official("David", "Jaquet", "d@jaquet.ch", "superPassword",1, testTeam);
//        officialManager.create(david);
//        assertEquals(1, officialManager.getAll().size());
//
//        Official nathan = new Official("Nathan", "Fluckiger", "n@fluckiger.ch", "superPassword",1, testTeam);
//        officialManager.create(nathan);
//        assertEquals(2, officialManager.getAll().size());
//    }
//
//    @Test
//    void getUserShouldReturnObjectClones() {
//        OfficialManagerLocal officialManager = new OfficialManager();
//        TeamManagerLocal teamManager = new TeamManager();
//        Team testTeam = new Team("AMT", "Sous-un-pont 14", "1450", "Ste-Croix");
//        teamManager.create(testTeam);
//        Official david = new Official("David", "Jaquet", "d@jaquet.ch", "superPassword",1, testTeam);
//        int davidid = officialManager.create(david);
//        Official davidLoad = officialManager.get(davidid);
//        assertTrue(david != davidLoad);
//        assertEquals(david, davidLoad);
//    }
//
//    @Test
//    void itShouldBePossibleToUpdateATeam() {
//        OfficialManagerLocal officialManager = new OfficialManager();
//        TeamManagerLocal teamManager = new TeamManager();
//        Team testTeam = new Team("AMT", "Sous-un-pont 14", "1450", "Ste-Croix");
//        teamManager.create(testTeam);
//        Official david = new Official("David", "Jaquet", "d@jaquet.ch", "superPassword",1, testTeam);
//        int davidid = officialManager.create(david);
//        Official davidLoad = officialManager.get(davidid);
//        davidLoad.setEmail("pwnstar@jaquet.ch");
//        officialManager.update(davidLoad);
//        david = officialManager.get(davidid);
//
//        assertEquals("pwnstar@jaquet.ch", david.getEmail());
//        assertEquals(davidLoad, david);
//    }
//    @Test
//    void itShouldBePossibleToStoreAndRetrieveTeam(){
//        TeamManagerLocal teamManager = new TeamManager();
//
//        List<Team> teams = teamManager.getAll();
//        assertEquals(0, teams.size());
//
//        Team testTeam = new Team("AMT", "Sous-un-pont 14", "1450", "Ste-Croix");
//        teamManager.create(testTeam);
//
//        assertEquals(1, teamManager.getAll().size());
//
//        Team testTeam2 = new Team("MAC", "Sous-un-pont 13", "1400", "Yverdon-les-bains");
//        teamManager.create(testTeam2);
//        assertEquals(2, teamManager.getAll().size());
//    }
//
//    @Test
//    void getTeamShouldReturnObjectClones() {
//        TeamManagerLocal teamManager = new TeamManager();
//        Team testTeam = new Team("AMT", "Sous-un-pont 14", "1450", "Ste-Croix");
//        int teamid = teamManager.create(testTeam);
//        Team testTeamLoad = teamManager.get(teamid);
//        assertTrue(testTeam != testTeamLoad);
//        assertEquals(testTeam, testTeamLoad);
//    }
//
//    @Test
//    void itShouldBePossibleToUpdateATeam() {TeamManagerLocal teamManager = new TeamManager();
//        Team testTeam = new Team("AMT", "Sous-un-pont 14", "1450", "Ste-Croix");
//        int teamid = teamManager.create(testTeam);
//        Team teamload = teamManager.get(teamid);
//        teamload.setName("MAC");
//        teamManager.update(teamload);
//        testTeam = teamManager.get(teamid);
//
//        assertEquals("MAC", testTeam.getName());
//        assertEquals(teamload, testTeam);
//    }
//    @Test
//    void itShouldBePossibleToStoreAndRetrieveGames(){
//        OfficialManagerLocal officialManager = new OfficialManager();
//        TeamManagerLocal teamManager = new TeamManager();
//        GameManagerLocal gameManager = new GameManager();
//
//        List<Game> users = gameManager.getAll();
//        assertEquals(0, users.size());
//
//        Team testTeam = new Team("AMT", "Sous-un-pont 14", "1450", "Ste-Croix");
//        teamManager.create(testTeam);
//        Team testTeam2 = new Team("MAC", "Sous-un-pont 13", "1400", "Yverdon-les-bains");
//        teamManager.create(testTeam2);
//
//        Official david = new Official("David", "Jaquet", "d@jaquet.ch", "superPassword",1, testTeam);
//        officialManager.create(david);
//        Official david2 = new Official("David", "Jaquet", "d2@jaquet.ch", "superPassword",1, testTeam);
//        officialManager.create(david2);
//        Official david3 = new Official("David", "Jaquet", "d3@jaquet.ch", "superPassword",1, testTeam);
//        officialManager.create(david3);
//        Official david4 = new Official("David", "Jaquet", "d4@jaquet.ch", "superPassword",1, testTeam);
//        officialManager.create(david4);
//        Official david5 = new Official("David", "Jaquet", "d5@jaquet.ch", "superPassword",1, testTeam);
//        officialManager.create(david5);
//        Official david6 = new Official("David", "Jaquet", "d6@jaquet.ch", "superPassword",1, testTeam);
//        officialManager.create(david6);
//        Official david7 = new Official("David", "Jaquet", "d7@jaquet.ch", "superPassword",1, testTeam);
//        officialManager.create(david7);
//
//        LocalDateTime time = LocalDateTime.of(2019,12,31, 23,45);
//        Game game = new Game(time, testTeam, testTeam2, david, david2, david3, david4, david5, david6, david7);
//        gameManager.create(game);
//        assertEquals(1, gameManager.getAll().size());
//
//        time = LocalDateTime.of(2020,12,31, 23,45);
//        Game game2 = new Game(time, testTeam, testTeam2, david, david2, david3, david4, david5, david6, david7);
//        gameManager.create(game2);
//        assertEquals(2, gameManager.getAll().size());
//    }
//
//    @Test
//    void getGameShouldReturnObjectClones() {
//        OfficialManagerLocal officialManager = new OfficialManager();
//        TeamManagerLocal teamManager = new TeamManager();
//        GameManagerLocal gameManager = new GameManager();
//
//        Team testTeam = new Team("AMT", "Sous-un-pont 14", "1450", "Ste-Croix");
//        teamManager.create(testTeam);
//        Team testTeam2 = new Team("MAC", "Sous-un-pont 13", "1400", "Yverdon-les-bains");
//        teamManager.create(testTeam2);
//
//        Official david = new Official("David", "Jaquet", "d@jaquet.ch", "superPassword",1, testTeam);
//        officialManager.create(david);
//        Official david2 = new Official("David", "Jaquet", "d2@jaquet.ch", "superPassword",1, testTeam);
//        officialManager.create(david2);
//        Official david3 = new Official("David", "Jaquet", "d3@jaquet.ch", "superPassword",1, testTeam);
//        officialManager.create(david3);
//        Official david4 = new Official("David", "Jaquet", "d4@jaquet.ch", "superPassword",1, testTeam);
//        officialManager.create(david4);
//        Official david5 = new Official("David", "Jaquet", "d5@jaquet.ch", "superPassword",1, testTeam);
//        officialManager.create(david5);
//        Official david6 = new Official("David", "Jaquet", "d6@jaquet.ch", "superPassword",1, testTeam);
//        officialManager.create(david6);
//        Official david7 = new Official("David", "Jaquet", "d7@jaquet.ch", "superPassword",1, testTeam);
//        officialManager.create(david7);
//
//        LocalDateTime time = LocalDateTime.of(2019,12,31, 23,45);
//        Game game = new Game(time, testTeam, testTeam2, david, david2, david3, david4, david5, david6, david7);
//        int gameid = gameManager.create(game);
//        Game gameload = gameManager.get(gameid);
//
//        assertTrue(game != gameload);
//        assertEquals(game, gameload);
//    }
//
//    @Test
//    void itShouldBePossibleToUpdateAGame() {
//        OfficialManagerLocal officialManager = new OfficialManager();
//        TeamManagerLocal teamManager = new TeamManager();
//        GameManagerLocal gameManager = new GameManager();
//
//        Team testTeam = new Team("AMT", "Sous-un-pont 14", "1450", "Ste-Croix");
//        teamManager.create(testTeam);
//        Team testTeam2 = new Team("MAC", "Sous-un-pont 13", "1400", "Yverdon-les-bains");
//        teamManager.create(testTeam2);
//
//        Official david = new Official("David", "Jaquet", "d@jaquet.ch", "superPassword",1, testTeam);
//        officialManager.create(david);
//        Official david2 = new Official("David", "Jaquet", "d2@jaquet.ch", "superPassword",1, testTeam);
//        officialManager.create(david2);
//        Official david3 = new Official("David", "Jaquet", "d3@jaquet.ch", "superPassword",1, testTeam);
//        officialManager.create(david3);
//        Official david4 = new Official("David", "Jaquet", "d4@jaquet.ch", "superPassword",1, testTeam);
//        officialManager.create(david4);
//        Official david5 = new Official("David", "Jaquet", "d5@jaquet.ch", "superPassword",1, testTeam);
//        officialManager.create(david5);
//        Official david6 = new Official("David", "Jaquet", "d6@jaquet.ch", "superPassword",1, testTeam);
//        officialManager.create(david6);
//        Official david7 = new Official("David", "Jaquet", "d7@jaquet.ch", "superPassword",1, testTeam);
//        officialManager.create(david7);
//
//        LocalDateTime time = LocalDateTime.of(2019,12,31, 23,45);
//        Game game = new Game(time, testTeam, testTeam2, david, david2, david3, david4, david5, david6, david7);
//        int gameid = gameManager.create(game);
//        Game gameload = gameManager.get(gameid);
//        time = LocalDateTime.of(2020,12,31, 23,45);
//        gameload.setTimestamp(time);
//        gameManager.update(gameload);
//
//        game = gameManager.get(gameid);
//
//        assertEquals(time, game.getTimestamp());
//        assertEquals(gameload, game);
//    }
//}
