package cz.muni.fi.pa165.esports.service;

import cz.muni.fi.pa165.esports.dao.CompetitionDao;
import cz.muni.fi.pa165.esports.dao.MatchRecordDao;
import cz.muni.fi.pa165.esports.dao.TeamDao;
import cz.muni.fi.pa165.esports.entity.Competition;
import cz.muni.fi.pa165.esports.entity.MatchRecord;
import cz.muni.fi.pa165.esports.entity.Player;
import cz.muni.fi.pa165.esports.entity.Team;
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

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

/**
 * Test for {@link TeamService}
 *
 * @author Radovan Tomasik, Gabriela Kandova
 */
@ContextConfiguration(classes = {ServiceConfiguration.class})
public class TeamServiceImplTest extends AbstractTestNGSpringContextTests {

    @Mock
    private TeamDao teamDao;
    @Mock
    private MatchRecordDao matchRecordDao;
    @Mock
    private CompetitionDao competitionDao;

    @InjectMocks
    private TeamServiceImpl teamService;

    @BeforeClass
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        Team men = new Team();
        men.setId(1L);
        men.setName("Men of Gondor");
        men.setAbbreviation("MOG");
        men.setDescription("Men of Gondor");

        Team orcs = new Team();
        orcs.setId(2L);
        orcs.setName("Orcs");
        orcs.setDescription("FO");
        orcs.setDescription("Filthy orcs");

        List<Team> allTeams = new ArrayList<>();
        allTeams.add(men);
        allTeams.add(orcs);

        // prepare competitions
        Competition fifa2021 = new Competition();
        fifa2021.setId(1L);
        fifa2021.setName("FIFA Masters 2021");
        fifa2021.setGame(Game.FIFA);
        fifa2021.addTeam(men);
        fifa2021.addTeam(orcs);

        Competition fifa2020 = new Competition();
        fifa2020.setId(2L);
        fifa2020.setName("FIFA Masters 2020");
        fifa2020.setGame(Game.FIFA);
        fifa2020.addTeam(men);
        fifa2020.addTeam(orcs);

        List<Competition> allCompetitions = new ArrayList<>();
        allCompetitions.add(fifa2021);
        allCompetitions.add(fifa2020);

        Mockito.when(competitionDao.findAll()).thenReturn(allCompetitions);

        // prepare players
        Player aragorn = new Player();
        aragorn.setId(1L);
        aragorn.setName("Aragorn");
        aragorn.setGender(Gender.MALE);
        aragorn.setTeam(men);
        men.addPlayer(aragorn);

        Player randomOrc = new Player();
        randomOrc.setId(2L);
        randomOrc.setName("Random Orc");
        randomOrc.setGender(Gender.OTHER);
        randomOrc.setTeam(orcs);
        orcs.addPlayer(randomOrc);

        // prepare match records
        MatchRecord m1 = new MatchRecord();
        m1.setTeam(men);
        m1.setPlayer(aragorn);
        m1.setCompetition(fifa2021);
        m1.setScore(2);
        m1.setMatchNumber(1L);
        MatchRecord m2 = new MatchRecord();
        m2.setTeam(men);
        m2.setPlayer(aragorn);
        m2.setCompetition(fifa2021);
        m2.setScore(2);
        m2.setMatchNumber(2L);
        MatchRecord m3 = new MatchRecord();
        m3.setTeam(men);
        m3.setPlayer(aragorn);
        m3.setCompetition(fifa2021);
        m3.setScore(2);
        m3.setMatchNumber(3L);

        MatchRecord m4 = new MatchRecord();
        m4.setTeam(orcs);
        m4.setPlayer(randomOrc);
        m4.setCompetition(fifa2021);
        m4.setScore(3);
        m4.setMatchNumber(1L);
        MatchRecord m5 = new MatchRecord();
        m5.setTeam(orcs);
        m5.setPlayer(randomOrc);
        m5.setCompetition(fifa2021);
        m5.setScore(0);
        m5.setMatchNumber(2L);
        MatchRecord m6 = new MatchRecord();
        m6.setTeam(orcs);
        m6.setPlayer(randomOrc);
        m6.setCompetition(fifa2021);
        m6.setScore(3);
        m6.setMatchNumber(3L);

        MatchRecord m7 = new MatchRecord();
        m7.setTeam(men);
        m7.setPlayer(aragorn);
        m7.setCompetition(fifa2020);
        m7.setScore(20);
        m7.setMatchNumber(1L);
        MatchRecord m8 = new MatchRecord();
        m8.setTeam(orcs);
        m8.setPlayer(randomOrc);
        m8.setCompetition(fifa2020);
        m8.setScore(19);
        m8.setMatchNumber(1L);

        List<MatchRecord> allRecords = new ArrayList<>();
        allRecords.add(m1);
        allRecords.add(m2);
        allRecords.add(m3);
        allRecords.add(m4);
        allRecords.add(m5);
        allRecords.add(m6);
        allRecords.add(m7);
        allRecords.add(m8);

        List<MatchRecord> menRecords = new ArrayList<>();
        menRecords.add(m1);
        menRecords.add(m2);
        menRecords.add(m3);
        menRecords.add(m7);

        List<MatchRecord> orcRecords = new ArrayList<>();
        orcRecords.add(m4);
        orcRecords.add(m5);
        orcRecords.add(m6);
        orcRecords.add(m8);

        List<MatchRecord> fifa2021Records = new ArrayList<>();
        fifa2021Records.add(m1);
        fifa2021Records.add(m2);
        fifa2021Records.add(m3);
        fifa2021Records.add(m4);
        fifa2021Records.add(m5);
        fifa2021Records.add(m6);

        List<MatchRecord> fifa2020Records = new ArrayList<>();
        fifa2020Records.add(m7);
        fifa2020Records.add(m8);

        Mockito.when(matchRecordDao.findAll()).thenReturn(allRecords);
        Mockito.when(matchRecordDao.findByCompetition(Mockito.any(Competition.class))).thenAnswer(
                (Answer<List<MatchRecord>>) invocationOnMock -> {
                    if (fifa2021.equals(invocationOnMock.getArgument(0))) {
                        return fifa2021Records;
                    }
                    if (fifa2020.equals(invocationOnMock.getArgument(0))) {
                        return fifa2020Records;
                    }
                    return new ArrayList<>();
                }
        );
        Mockito.when(matchRecordDao.findByTeam(Mockito.any(Team.class))).thenAnswer(
                (Answer<List<MatchRecord>>) invocationOnMock -> {
                    if (men.equals(invocationOnMock.getArgument(0))) {
                        return menRecords;
                    }
                    if (orcs.equals(invocationOnMock.getArgument(0))) {
                        return orcRecords;
                    }
                    return new ArrayList<>();
                }
        );

        Mockito.when(teamDao.findAll()).thenReturn(allTeams);

        Mockito.when(teamDao.findById(Mockito.anyLong())).thenAnswer(
                (Answer<Team>) invocationOnMock -> {
                    if (Long.valueOf(1).equals(invocationOnMock.getArgument(0))) {
                        return men;
                    }
                    if (Long.valueOf(2).equals(invocationOnMock.getArgument(0))) {
                        return orcs;
                    }
                    return null;
                }
        );

        Mockito.when(teamDao.findByName(Mockito.anyString())).thenAnswer(
                (Answer<Team>) invocationOnMock -> {
                    if ("Men of Gondor".equals(invocationOnMock.getArgument(0))) {
                        return men;
                    }
                    if ("Orcs".equals(invocationOnMock.getArgument(0))) {
                        return orcs;
                    }
                    return null;
                }
        );

        Mockito.when(teamDao.findByAbbreviation(Mockito.anyString())).thenAnswer(
                (Answer<Team>) invocationOnMock -> {
                    if ("MOG".equals(invocationOnMock.getArgument(0))) {
                        return men;
                    }
                    if ("FO".equals(invocationOnMock.getArgument(0))) {
                        return orcs;
                    }
                    return null;
                }
        );
    }

    @Test
    public void testFindAll() {
        List<Team> allTeams = teamService.findAll();

        // 2 teams
        assertEquals(allTeams.size(), 2);
        assertNotNull(allTeams.get(0));
        assertNotNull(allTeams.get(1));
        assertNotEquals(allTeams.get(0).getAbbreviation(), allTeams.get(1).getAbbreviation());
    }

    @Test
    public void testFindById() {
        Team teamFound = teamService.findById(1L);
        assertNotNull(teamFound);
        assertEquals(teamFound.getAbbreviation(), "MOG");
        teamFound = teamService.findById(2L);
        assertNotNull(teamFound);
        assertEquals(teamFound.getDescription(), "Filthy orcs");
    }

    @Test
    public void testFindByName() {
        assertEquals(teamService.findByName("Orcs").getDescription(), "Filthy orcs");
    }

    @Test
    public void testFindByAbbreviation() {
        assertEquals(teamService.findByAbbreviation("FO").getDescription(), "Filthy orcs");
    }

    @Test
    public void testCreate() {
        // TODO
    }

    @Test
    public void testRemove() {
        // TODO
    }

    @Test
    public void testAddPlayer() {
        // TODO
    }

    @Test
    public void testRemovePlayer() {
        // TODO
    }

    @Test
    public void testCompetitionStatistics() {
        assertNotNull(matchRecordDao);
        Team men = teamDao.findById(1L);
        Team orcs = teamDao.findById(2L);

        List<Competition> competitions = competitionDao.findAll();
        Competition fifa2021 = competitions.get(0); // order defined in setup
        Competition fifa2020 = competitions.get(1);

        double menFifaAverage = teamService.getAverageTeamScoreForCompetition(men, fifa2021);
        assertEquals(menFifaAverage, 2.0);
        double orcFifaAverage = teamService.getAverageTeamScoreForCompetition(orcs, fifa2021);
        assertEquals(orcFifaAverage, 2.0);

        double menFifa2020Average = teamService.getAverageTeamScoreForCompetition(men, fifa2020);
        assertEquals(menFifa2020Average, 20.0);
        double orcsFifa2020Average = teamService.getAverageTeamScoreForCompetition(orcs, fifa2020);
        assertEquals(orcsFifa2020Average, 19.0);

    }

    @Test
    public void testGameStatistics() {
        Team men = teamDao.findById(1L);
        Team orcs = teamDao.findById(2L);

        double menFifaAverage = teamService.getAverageTeamScoreForGame(men, Game.FIFA);
        assertEquals(menFifaAverage, 6.5);
        double orcFifaAverage = teamService.getAverageTeamScoreForGame(orcs, Game.FIFA);
        assertEquals(orcFifaAverage, 6.25);

        // no data for this game
        double menLolAverage = teamService.getAverageTeamScoreForGame(men, Game.LOL);
        assertEquals(menLolAverage, 0);
        double orcLolAverage = teamService.getAverageTeamScoreForGame(orcs, Game.LOL);
        assertEquals(orcLolAverage, 0);
    }
}