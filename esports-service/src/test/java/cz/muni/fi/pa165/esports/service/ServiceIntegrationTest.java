package cz.muni.fi.pa165.esports.service;

import cz.muni.fi.pa165.esports.entity.Player;
import cz.muni.fi.pa165.esports.entity.Team;
import cz.muni.fi.pa165.esports.enums.Gender;
import cz.muni.fi.pa165.esports.service.config.ServiceConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.inject.Inject;
import java.util.Collections;
import java.util.List;

/**
 * Integration tests for the service and persistence layer.
 *
 * @author Gabriela Kandova
 */
@ContextConfiguration(classes = {ServiceConfiguration.class})
public class ServiceIntegrationTest extends AbstractTestNGSpringContextTests {

    @Inject
    private PlayerService playerService;
    @Inject
    private TeamService teamService;

    /**
     * Test persisting a player without a team from the service layer.
     */
    @Test
    public void testSavePlayerGetPlayer() {
        Player alice = new Player();
        alice.setName("Alice");
        alice.setGender(Gender.FEMALE);
        playerService.create(alice);

        Player persistedAlice = playerService.findById(alice.getId());
        Assert.assertNotNull(persistedAlice);
        Assert.assertEquals(persistedAlice, alice);

        List<Player> players = playerService.getAllPlayers();
        Assert.assertEquals(players, Collections.singletonList(persistedAlice));
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
        Assert.assertEquals(teams, Collections.singletonList(persistedCops));
    }
}
