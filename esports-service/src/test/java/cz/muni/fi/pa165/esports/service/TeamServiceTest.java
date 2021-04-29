package cz.muni.fi.pa165.esports.service;

import cz.muni.fi.pa165.esports.dao.TeamDao;
import cz.muni.fi.pa165.esports.entity.Team;
import cz.muni.fi.pa165.esports.service.config.ServiceConfiguration;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;

/**
 * @author Radovan Tomasik
 * Test for TeamService
 */
@ContextConfiguration(classes = {ServiceConfiguration.class})
public class TeamServiceTest extends AbstractTestNGSpringContextTests {
    @Mock
    private TeamDao teamDao;

    @Autowired
    @InjectMocks
    private TeamService teamService;

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
    public void testGetTeamStatistics() {
    }

}