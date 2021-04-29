package cz.muni.fi.pa165.esports.service;

import cz.muni.fi.pa165.esports.dao.MatchRecordDao;
import cz.muni.fi.pa165.esports.entity.Competition;
import cz.muni.fi.pa165.esports.entity.MatchRecord;
import cz.muni.fi.pa165.esports.entity.Player;
import cz.muni.fi.pa165.esports.entity.Team;
import cz.muni.fi.pa165.esports.service.CompetitionService;
import cz.muni.fi.pa165.esports.service.MatchRecordService;
import cz.muni.fi.pa165.esports.service.PlayerService;
import cz.muni.fi.pa165.esports.service.TeamService;
import cz.muni.fi.pa165.esports.service.config.ServiceConfiguration;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

/**
 * @author Elena Alvarez
 */
@ContextConfiguration(classes = {ServiceConfiguration.class})
public class MatchRecordServiceTest extends AbstractTestNGSpringContextTests {
    @Mock
    private MatchRecordDao matchRecordDao;

    @Autowired
    @InjectMocks
    private MatchRecordService matchRecordService;

    @Autowired
    @InjectMocks
    private PlayerService playerService;

    @Autowired
    @InjectMocks
    private TeamService teamService;

    @Autowired
    @InjectMocks
    private CompetitionService competitionService;

    Player p1;
    Player p2;
    Team t1;
    Team t2;
    Competition c1;
    Competition c2;
    MatchRecord matchRecord1;
    MatchRecord matchRecord2;

    @BeforeClass
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        matchRecord1 = new MatchRecord();
        matchRecord1.setScore(0);
        matchRecord2 = new MatchRecord();
        matchRecord2.setScore(1);

        p1 = new Player();
        p1.setName("p1");

        p2 = new Player();
        p2.setName("p2");

        t1 = new Team();
        t1.setName("t1");

        t2 = new Team();
        t2.setName("t2");

        c1 = new Competition();
        c2 = new Competition();

        matchRecord1.setPlayer(p1);
        matchRecord1.setTeam(t1);
        matchRecord1.setCompetition(c1);

        matchRecord2.setPlayer(p2);
        matchRecord2.setTeam(t2);
        matchRecord2.setCompetition(c2);


        playerService.create(p1);
        playerService.create(p2);
        teamService.create(t1);
        teamService.create(t2);
        competitionService.createCompetition(c1);
        competitionService.createCompetition(c2);
        matchRecordService.create(matchRecord1);
        matchRecordService.create(matchRecord2);


        List<MatchRecord> allMatchRecords = new ArrayList<>();
        allMatchRecords.add(matchRecord1);
        allMatchRecords.add(matchRecord1);


        Mockito.when(matchRecordDao.findAll()).thenReturn(allMatchRecords);

    }

    @Test
    public void getAll() {
        List<MatchRecord> allMatchRecords = matchRecordService.findAll();

        assertEquals(allMatchRecords.size(), 2);
        assertNotNull(allMatchRecords.get(0));
        assertNotNull(allMatchRecords.get(1));
        assertNotEquals(allMatchRecords.get(0).getScore(), allMatchRecords.get(1).getScore());
    }

    @Test
    public void getByPlayer() {
        List<MatchRecord> byPlayer = matchRecordDao.findByPlayer(p1);
        Assert.assertEquals(byPlayer.get(0).getScore(), 0);

        List<MatchRecord> byPlayer2 = matchRecordDao.findByPlayer(p2);
        Assert.assertEquals(byPlayer2.get(0).getScore(), 1);
    }

    @Test
    public void getByTeam() {
        List<MatchRecord> byTeam = matchRecordDao.findByTeam(t1);
        Assert.assertEquals(byTeam.get(0).getScore(), 0);

        List<MatchRecord> byTeam2 = matchRecordDao.findByTeam(t2);
        Assert.assertEquals(byTeam2.get(0).getScore(), 1);
    }

    @Test
    public void getByCompetition() {
        List<MatchRecord> byCompetition = matchRecordDao.findByCompetition(c1);
        Assert.assertEquals(byCompetition.get(0).getScore(), 0);

        List<MatchRecord> byCompetition2 = matchRecordDao.findByCompetition(c2);
        Assert.assertEquals(byCompetition2.get(0).getScore(), 1);
    }



}
