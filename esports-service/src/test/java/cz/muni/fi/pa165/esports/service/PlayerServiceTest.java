package cz.muni.fi.pa165.esports.service;

import cz.muni.fi.pa165.esports.dao.PlayerDao;
import cz.muni.fi.pa165.esports.entity.Player;
import cz.muni.fi.pa165.esports.enums.Gender;
import cz.muni.fi.pa165.esports.service.config.ServiceConfiguration;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

/**
 * Unit tests for {@link PlayerService}
 * @author Gabriela Kandova
 */
@ContextConfiguration(classes = {ServiceConfiguration.class})
public class PlayerServiceTest extends AbstractTestNGSpringContextTests {
    @Mock
    private PlayerDao playerDao;

    @Autowired
    @InjectMocks
    private PlayerService playerService;

    @BeforeClass
    public void setup() {
        MockitoAnnotations.initMocks(this);

        Player alice = new Player();
        alice.setId(1L);
        alice.setName("Alice");
        alice.setGender(Gender.FEMALE);

        Player bob = new Player();
        bob.setId(2L);
        bob.setName("Bob");
        bob.setGender(Gender.MALE);

        List<Player> allPlayers = new ArrayList<>();
        allPlayers.add(alice);
        allPlayers.add(bob);

        List<Player> aliceList = new ArrayList<>();
        aliceList.add(alice);

        List<Player> bobList = new ArrayList<>();
        bobList.add(bob);

        Mockito.when(playerDao.findAll()).thenReturn(allPlayers);

        // Inspired by https://stackoverflow.com/a/22345982
        Mockito.when(playerDao.findByName(Mockito.anyString())).thenAnswer(
                (Answer<List<Player>>) invocationOnMock -> {
                    if ("Alice".equals(invocationOnMock.getArgument(0))) {
                        return aliceList;
                    }
                    if ("Bob".equals(invocationOnMock.getArgument(0))) {
                        return bobList;
                    }
                    return new ArrayList<>();
                }
        );

        Mockito.when(playerDao.findByGender(Mockito.any(Gender.class))).thenAnswer(
                (Answer<List<Player>>) invocationOnMock -> {
                    if (Gender.FEMALE.equals(invocationOnMock.getArgument(0))) {
                        return aliceList;
                    }
                    if (Gender.FEMALE.equals(invocationOnMock.getArgument(0))) {
                        return bobList;
                    }
                    return new ArrayList<>();
                }
        );

        Mockito.when(playerDao.findById(Mockito.anyLong())).thenAnswer(
                (Answer<Player>)  invocationOnMock -> {
                    if (Long.valueOf(1).equals(invocationOnMock.getArgument(0))) {
                        return alice;
                    }
                    if (Long.valueOf(2).equals(invocationOnMock.getArgument(0))) {
                        return bob;
                    }
                    return null;
                }
        );
    }

    @Test
    public void getAllPlayersTest() {
        List<Player> allPlayers = playerService.getAllPlayers();

        // expecting 2 players
        assertEquals(allPlayers.size(), 2);
        Player p1 = allPlayers.get(0);
        assertNotNull(p1);
        Player p2 = allPlayers.get(1);
        assertNotNull(p2);

        // assert that player ids, names and genders are different
        assertNotEquals(p1.getId(), p2.getId());
        assertNotEquals(p1.getName(), p2.getName());
        assertNotEquals(p1.getGender(), p2.getGender());

        // assert that bob and alice are present
        // the assertion above guarantees XOR
        assertTrue(p1.getName().equals("Alice") || p2.getName().equals("Alice"));
        assertTrue(p1.getName().equals("Bob") || p2.getName().equals("Bob"));
    }

    @Test
    public void findByIdFoundTest() {
        Player foundPlayer = playerService.findById(1L);
        assertNotNull(foundPlayer);
        assertEquals(foundPlayer.getName(), "Alice");
        assertEquals(foundPlayer.getGender(), Gender.FEMALE);
        assertEquals(foundPlayer.getId(), Long.valueOf(1));

        foundPlayer = playerService.findById(2L);
        assertNotNull(foundPlayer);
        assertEquals(foundPlayer.getName(), "Bob");
        assertEquals(foundPlayer.getGender(), Gender.MALE);
        assertEquals(foundPlayer.getId(), Long.valueOf(2));
    }

    @Test
    public void findByIdNotFoundTest() {
        Player foundPlayer = playerService.findById(3L);
        assertNull(foundPlayer);
    }

    @Test
    public void findByNameFoundSingleTest() {
        String name = "Alice";
        List<Player> foundPlayers = playerService.findByName(name);
        assertEquals(foundPlayers.size(), 1);
        assertNotNull(foundPlayers.get(0));
        assertEquals(foundPlayers.get(0).getName(), name);
        assertEquals(foundPlayers.get(0).getGender(), Gender.FEMALE);
    }

    @Test
    public void findByNameNotFoundTest() {
        List<Player> foundPlayers = playerService.findByName("Cecil");
        assertEquals(foundPlayers.size(), 0);
    }

    @Test
    public void findByNameFoundMultipleTest() {
    }

    @Test
    public void createTest() {
    }

    @Test
    public void removeTest() {
    }

    @Test
    public void getPlayerStatisticsTest() {
    }
}