package cz.muni.fi.pa165.esports.service;

import cz.muni.fi.pa165.esports.dao.MatchRecordDao;
import cz.muni.fi.pa165.esports.entity.Competition;
import cz.muni.fi.pa165.esports.entity.MatchRecord;
import cz.muni.fi.pa165.esports.entity.Player;
import cz.muni.fi.pa165.esports.entity.Team;
import cz.muni.fi.pa165.esports.service.config.ServiceConfiguration;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.testng.Assert.*;

/**
 * @author Elena Alvarez
 */
@ContextConfiguration(classes = {ServiceConfiguration.class})
public class MatchRecordServiceTest extends AbstractTestNGSpringContextTests {

    @Mock
    private MatchRecordDao matchRecordDao;

    @Inject
    @InjectMocks
    private MatchRecordService matchRecordService;

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

        p1 = new Player();
        p1.setName("p1");
        p2 = new Player();
        p2.setName("p2");

        t1 = new Team();
        t1.setName("t1");
        t1.setAbbreviation("t1");
        t2 = new Team();
        t2.setName("t2");
        t2.setAbbreviation("t2");

        c1 = new Competition();
        c1.setName("c1");
        c2 = new Competition();
        c2.setName("c2");

        matchRecord1 = new MatchRecord();
        matchRecord1.setId(1L);
        matchRecord1.setScore(0);
        matchRecord1.setPlayer(p1);
        matchRecord1.setTeam(t1);
        matchRecord1.setCompetition(c1);

        matchRecord2 = new MatchRecord();
        matchRecord2.setId(2L);
        matchRecord2.setScore(1);
        matchRecord2.setPlayer(p2);
        matchRecord2.setTeam(t2);
        matchRecord2.setCompetition(c2);
    }

    @Test
    public void getAll() {
        when(matchRecordDao.findAll()).thenReturn(Arrays.asList(matchRecord1, matchRecord2));
        List<MatchRecord> allMatchRecords = matchRecordService.findAll();

        assertEquals(allMatchRecords.size(), 2);
        assertNotNull(allMatchRecords.get(0));
        assertNotNull(allMatchRecords.get(1));
        assertNotEquals(allMatchRecords.get(0).getScore(), allMatchRecords.get(1).getScore());
    }

    @Test
    public void getByPlayer() {
        when(matchRecordDao.findByPlayer(p1)).thenReturn(List.of(matchRecord1));
        when(matchRecordDao.findByPlayer(p2)).thenReturn(List.of(matchRecord2));

        List<MatchRecord> byPlayer = matchRecordDao.findByPlayer(p1);
        Assert.assertEquals(byPlayer.get(0).getScore(), 0);
    }

    @Test
    public void getByTeam() {
        when(matchRecordDao.findByTeam(t1)).thenReturn(List.of(matchRecord1));
        when(matchRecordDao.findByTeam(t2)).thenReturn(List.of(matchRecord2));

        List<MatchRecord> byTeam = matchRecordDao.findByTeam(t1);
        Assert.assertEquals(byTeam.get(0).getScore(), 0);
    }

    @Test
    public void getByCompetition() {
        when(matchRecordDao.findByCompetition(c1)).thenReturn(List.of(matchRecord1));
        when(matchRecordDao.findByCompetition(c2)).thenReturn(List.of(matchRecord2));

        List<MatchRecord> byCompetition = matchRecordDao.findByCompetition(c1);
        Assert.assertEquals(byCompetition.get(0).getScore(), 0);

        List<MatchRecord> byCompetition2 = matchRecordDao.findByCompetition(c2);
        Assert.assertEquals(byCompetition2.get(0).getScore(), 1);
    }
}
