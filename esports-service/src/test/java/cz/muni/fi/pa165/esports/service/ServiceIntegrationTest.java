package cz.muni.fi.pa165.esports.service;

import cz.muni.fi.pa165.esports.entity.Competition;
import cz.muni.fi.pa165.esports.entity.Player;
import cz.muni.fi.pa165.esports.entity.Team;
import cz.muni.fi.pa165.esports.enums.Game;
import cz.muni.fi.pa165.esports.enums.Gender;
import cz.muni.fi.pa165.esports.service.config.ServiceConfiguration;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.List;

/**
 * Integration tests for the service and persistence layer.
 *
 * @author Gabriela Kandova
 */
@ContextConfiguration(classes = {ServiceConfiguration.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ServiceIntegrationTest extends AbstractTestNGSpringContextTests {

    @Inject
    private PlayerService playerService;
    @Inject
    private TeamService teamService;
    @Inject
    private CompetitionService competitionService;

    /**
     * Test persisting a player without a team from the service layer.
     */
    @Test
    public void testSavePlayerGetPlayer() {
        Player alice = new Player();
        alice.setName("Alice");
        alice.setGender(Gender.FEMALE);
        playerService.create(alice);
        Assert.assertNotNull(alice.getId());

        // find by ID
        Player persistedAlice = playerService.findById(alice.getId());
        Assert.assertNotNull(persistedAlice);
        Assert.assertEquals(persistedAlice, alice);

        // find by name
        List<Player> alicePlayers = playerService.findByName("Alice");
        Assert.assertEquals(alicePlayers.size(), 1);
        Assert.assertNotNull(alicePlayers.get(0));
        Assert.assertEquals(alicePlayers.get(0), alice);

        // find all
        List<Player> players = playerService.getAllPlayers();
        for (Player player: players) {
            if (alice.equals(player)) {
                return;
            }
        }
        Assert.fail();
    }

    /**
     * Test persisting a team without players from the service layer.
     */
    @Test
    public void testSaveTeamGetTeam() {
        Team cops = new Team();
        cops.setName("The Police");
        cops.setAbbreviation("COPS");
        teamService.create(cops);

        Team persistedCops = teamService.findById(cops.getId());
        Assert.assertNotNull(persistedCops);
        Assert.assertEquals(persistedCops, cops);

        List<Team> teams = teamService.findAll();
        for (Team team: teams) {
            if (cops.equals(team)) {
                return;
            }
        }
        Assert.fail();
    }

    /**
     * Test persisting a competition without teams or matches from the service layer.
     */
    @Test
    public void testSaveCompetitionGetCompetition() {
        Competition fifa = new Competition();
        fifa.setName("FIFA Masters 2021");
        fifa.setGame(Game.FIFA);
        fifa.setLocation("Online");
        fifa.setDate(LocalDate.of(2021, 9, 24));
        fifa.setPrizepool(9000);
        competitionService.createCompetition(fifa);

        Competition persistedFifa = competitionService.findById(fifa.getId());
        Assert.assertNotNull(persistedFifa);
        Assert.assertEquals(persistedFifa, fifa);
    }

    /**
     * Test adding and removing players from a team from the service layer.
     */
    @Test
    public void addRemovePlayersFromTeam() {
        // prepare entities
        Team team1 = new Team();
        team1.setName("Team 1");
        team1.setAbbreviation("T1");
        teamService.create(team1);

        Player player1 = new Player();
        player1.setName("Player 1");
        player1.setGender(Gender.OTHER);
        playerService.create(player1);

        Player player2 = new Player();
        player2.setName("Player 2");
        player2.setGender(Gender.OTHER);
        playerService.create(player2);

        // add players to team
        teamService.addPlayer(team1, player1);
        Assert.assertEquals(team1.getPlayers().size(), 1);
        Assert.assertNotNull(player1.getTeam());
        Assert.assertEquals(player1.getTeam(), team1);

        teamService.addPlayer(team1, player2);
        Assert.assertEquals(team1.getPlayers().size(), 2);
        Assert.assertNotNull(player2.getTeam());
        Assert.assertEquals(player2.getTeam(), team1);

        // remove players from team
        teamService.removePlayer(team1, player1);
        Assert.assertEquals(team1.getPlayers().size(), 1);
        Assert.assertTrue(team1.getPlayers().contains(player2));
        Assert.assertNull(player1.getTeam());
        Assert.assertNotNull(player2.getTeam());

        teamService.removePlayer(team1, player2);
        Assert.assertEquals(team1.getPlayers().size(), 0);
        Assert.assertNull(player1.getTeam());
        Assert.assertNull(player2.getTeam());
    }
}
