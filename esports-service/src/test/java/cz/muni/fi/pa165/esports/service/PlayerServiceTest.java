package cz.muni.fi.pa165.esports.service;

import cz.muni.fi.pa165.esports.dao.CompetitionDao;
import cz.muni.fi.pa165.esports.dao.MatchRecordDao;
import cz.muni.fi.pa165.esports.dao.PlayerDao;
import cz.muni.fi.pa165.esports.entity.Competition;
import cz.muni.fi.pa165.esports.entity.MatchRecord;
import cz.muni.fi.pa165.esports.entity.Player;
import cz.muni.fi.pa165.esports.enums.Game;
import cz.muni.fi.pa165.esports.enums.Gender;
import cz.muni.fi.pa165.esports.service.config.ServiceConfiguration;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.Answer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.testng.Assert.*;

/**
 * Unit tests for {@link PlayerService}
 *
 * @author Gabriela Kandova
 */
@ContextConfiguration(classes = {ServiceConfiguration.class})
public class PlayerServiceTest extends AbstractTestNGSpringContextTests {

    @Mock
    private PlayerDao playerDao;

    @Mock
    private MatchRecordDao matchRecordDao;

    @Mock
    private CompetitionDao competitionDao;

    @Inject
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

        Player bobbert = new Player();
        bobbert.setId(3L);
        bobbert.setName("Bob");
        bobbert.setGender(Gender.MALE);

        List<Player> allPlayers = Arrays.asList(alice, bob, bobbert);
        List<Player> aliceList = Collections.singletonList(alice);
        List<Player> bobList = Arrays.asList(bob, bobbert);

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
                (Answer<Player>) invocationOnMock -> {
                    if (Long.valueOf(1).equals(invocationOnMock.getArgument(0))) {
                        return alice;
                    }
                    if (Long.valueOf(2).equals(invocationOnMock.getArgument(0))) {
                        return bob;
                    }
                    if (Long.valueOf(3).equals(invocationOnMock.getArgument(0))) {
                        return bobbert;
                    }
                    return null;
                }
        );

        Competition fifa2021 = new Competition();
        fifa2021.setGame(Game.FIFA);
        fifa2021.setId(2021L);
        Competition fifa2022 = new Competition();
        fifa2022.setGame(Game.FIFA);
        fifa2022.setId(2022L);
        Competition lol2024 = new Competition();
        lol2024.setGame(Game.LOL);
        lol2024.setId(2024L);

        Mockito.when(competitionDao.findAll()).thenReturn(Arrays.asList(fifa2021, fifa2022, lol2024));

        MatchRecord m1 = new MatchRecord();
        m1.setId(1L);
        m1.setPlayer(alice);
        m1.setCompetition(fifa2021);
        m1.setScore(3);
        MatchRecord m2 = new MatchRecord();
        m2.setId(2L);
        m2.setPlayer(alice);
        m2.setCompetition(fifa2021);
        m2.setScore(1);
        MatchRecord m3 = new MatchRecord();
        m3.setId(3L);
        m3.setPlayer(alice);
        m3.setCompetition(fifa2022);
        m3.setScore(2);
        MatchRecord m4 = new MatchRecord();
        m4.setId(4L);
        m4.setPlayer(bob);
        m4.setCompetition(fifa2022);
        m4.setScore(1);
        MatchRecord m5 = new MatchRecord();
        m5.setId(5L);
        m5.setPlayer(alice);
        m5.setCompetition(lol2024);
        m5.setScore(19);

        Mockito.when(matchRecordDao.findAll()).thenReturn(Arrays.asList(m1, m2, m3, m4));
        Mockito.when(matchRecordDao.findByPlayer(Mockito.any(Player.class))).thenAnswer(
                (Answer<List<MatchRecord>>) invocationOnMock -> {
                    if (alice.equals(invocationOnMock.getArgument(0))) {
                        return Arrays.asList(m1, m2, m3, m5);
                    }
                    if (bob.equals(invocationOnMock.getArgument(0))) {
                        return Collections.singletonList(m4);
                    }
                    return Collections.emptyList();
                }
        );
        Mockito.when(matchRecordDao.findByCompetition(Mockito.any(Competition.class))).thenAnswer(
                (Answer<List<MatchRecord>>) invocationOnMock -> {
                    if (fifa2021.equals(invocationOnMock.getArgument(0))) {
                        return Arrays.asList(m1, m2);
                    }
                    if (fifa2022.equals(invocationOnMock.getArgument(0))) {
                        return Arrays.asList(m3, m4);
                    }
                    if (lol2024.equals(invocationOnMock.getArgument(0))) {
                        return Collections.singletonList(m5);
                    }
                    return Collections.emptyList();
                }
        );
    }

    @Test
    public void getAllPlayersTest() {
        List<Player> allPlayers = playerService.getAllPlayers();

        // expecting 3 players
        assertEquals(allPlayers.size(), 3);
        Player alice = allPlayers.get(0); // order is defined in setup
        assertNotNull(alice);
        Player bob = allPlayers.get(1);
        assertNotNull(bob);
        Player bobbert = allPlayers.get(2);
        assertNotNull(bobbert);

        assertEquals(alice.getId(), Long.valueOf(1));
        assertEquals(bob.getId(), Long.valueOf(2));
        assertEquals(bobbert.getId(), Long.valueOf(3));

        assertEquals(alice.getName(), "Alice");
        assertEquals(bob.getName(), "Bob");
        assertEquals(bobbert.getName(), "Bob");

        assertEquals(alice.getGender(), Gender.FEMALE);
        assertEquals(bob.getGender(), Gender.MALE);
        assertEquals(bobbert.getGender(), Gender.MALE);
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

        foundPlayer = playerService.findById(3L);
        assertNotNull(foundPlayer);
        assertEquals(foundPlayer.getName(), "Bob");
        assertEquals(foundPlayer.getGender(), Gender.MALE);
        assertEquals(foundPlayer.getId(), Long.valueOf(3));
    }

    @Test
    public void findByIdNotFoundTest() {
        Player foundPlayer = playerService.findById(4L);
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
        String name = "Bob";
        List<Player> foundPlayers = playerService.findByName(name);
        assertEquals(foundPlayers.size(), 2);
        assertNotNull(foundPlayers.get(0));
        assertNotNull(foundPlayers.get(1));
        assertEquals(foundPlayers.get(0).getName(), name);
        assertEquals(foundPlayers.get(1).getName(), name);
        assertNotEquals(foundPlayers.get(0), foundPlayers.get(1));
    }

    @Test
    public void getPlayerAverageTest() {
        Player alice = playerDao.findById(1L);
        assertNotNull(alice);
        double aliceAverage = playerService.getPlayerAverage(alice.getId());
        assertEquals(aliceAverage, 6.25);

        Player bob = playerDao.findById(2L);
        assertNotNull(bob);
        double bobAverage = playerService.getPlayerAverage(bob.getId());
        assertEquals(bobAverage, 1.0);

        Player bobbert = playerDao.findById(3L);
        assertNotNull(bobbert);
        double bobbertAverage = playerService.getPlayerAverage(bobbert.getId());
        assertEquals(bobbertAverage, 0.0);
    }

    @Test
    public void getPlayerAverageByGameTest() {
        Player alice = playerDao.findById(1L);
        assertNotNull(alice);
        Player bobbert = playerDao.findById(3L);
        assertNotNull(bobbert);

        double aliceFifaAverage = playerService.getPlayerAverageByGame(alice, Game.FIFA);
        assertEquals(aliceFifaAverage, 2.0);

        double aliceLolAverage = playerService.getPlayerAverageByGame(alice, Game.LOL);
        assertEquals(aliceLolAverage, 19.0);

        double bobbertFifaAverage = playerService.getPlayerAverageByGame(bobbert, Game.FIFA);
        assertEquals(bobbertFifaAverage, 0.0);
    }

    @Test
    public void getPlayerAverageByCompetitionTest() {
        List<Competition> competitions = competitionDao.findAll();
        Competition fifa2021 = competitions.get(0);
        Competition lol2024 = competitions.get(2);
        assertNotNull(fifa2021);
        assertNotNull(lol2024);

        Player alice = playerDao.findById(1L);
        assertNotNull(alice);
        Player bobbert = playerDao.findById(3L);
        assertNotNull(bobbert);

        double aliceFifa2021Average = playerService.getPlayerAverageByCompetition(alice, fifa2021);
        assertEquals(aliceFifa2021Average, 2.0);
        double aliceLol2024Average = playerService.getPlayerAverageByCompetition(alice, lol2024);
        assertEquals(aliceLol2024Average, 19.0);

        double bobbertFifa2021Average = playerService.getPlayerAverageByCompetition(bobbert, fifa2021);
        assertEquals(bobbertFifa2021Average, 0.0);
    }
}
