package cz.muni.fi.pa165.esports.service;

import cz.muni.fi.pa165.esports.dao.PlayerDao;
import cz.muni.fi.pa165.esports.entity.Player;
import cz.muni.fi.pa165.esports.enums.Gender;
import cz.muni.fi.pa165.esports.service.config.ServiceConfiguration;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
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

        Mockito.when(playerDao.findByName("Alice")).thenReturn(aliceList);
        Mockito.when(playerDao.findByName("Bob")).thenReturn(bobList);
        Mockito.when(playerDao.findByName(Mockito.anyString())).thenReturn(new ArrayList<>());

        Mockito.when(playerDao.findByGender(Gender.FEMALE)).thenReturn(aliceList);
        Mockito.when(playerDao.findByGender(Gender.MALE)).thenReturn(bobList);
        Mockito.when(playerDao.findByGender(Gender.OTHER)).thenReturn(new ArrayList<>());

        Mockito.when(playerDao.findById(alice.getId())).thenReturn(alice);
        Mockito.when(playerDao.findById(bob.getId())).thenReturn(bob);
        Mockito.when(playerDao.findById(Mockito.anyLong())).thenReturn(null);
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
}