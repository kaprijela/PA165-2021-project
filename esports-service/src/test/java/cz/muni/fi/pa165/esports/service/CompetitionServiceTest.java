package cz.muni.fi.pa165.esports.service;

import cz.muni.fi.pa165.esports.dao.CompetitionDao;
import cz.muni.fi.pa165.esports.entity.Competition;
import cz.muni.fi.pa165.esports.entity.Team;
import cz.muni.fi.pa165.esports.service.config.ServiceConfiguration;
import org.hibernate.service.spi.ServiceException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.testng.Assert;

import static org.mockito.Mockito.*;

/**
 * @author gavlijan
 */
@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = {ServiceConfiguration.class})
public class CompetitionServiceTest {

    @Mock
    private CompetitionDao competitionDao;

    @Mock
    private TeamService teamService;

    @Autowired
    @InjectMocks
    private CompetitionServiceImpl competitionService;
    private Competition competition;
    private Team team;

    @Before
    public void setup() throws ServiceException {
        competition = new Competition();
        competition.setName("competition");
        competition.setId(1L);

        team = new Team();
        team.setName("team");
    }

    @Test
    public void testCreateCompetition() {
        when(competitionDao.save(competition)).thenReturn(competition);
        competitionService.createCompetition(competition);

        verify(competitionDao, times(1)).save(competition);
    }

    @Test
    public void testRemoveCompetition() {
        competitionService.removeCompetition(competition);
        verify(competitionDao, times(1)).delete(competition);
    }

    @Test
    public void testAddTeam() {
        when(teamService.findByName("team")).thenReturn(team);
        when(competitionDao.findByName("competition")).thenReturn(competition);
        competitionService.addTeam("competition", "team");
        Assert.assertEquals(competition.getTeams().size(), 1);
    }

    @Test
    public void testRemoveTeam() {
        when(teamService.findByName("team")).thenReturn(team).thenReturn(team);
        when(competitionDao.findByName("competition")).thenReturn(competition).thenReturn(competition);
        competitionService.addTeam("competition", "team");
        Assert.assertEquals(competition.getTeams().size(), 1);
        competitionService.removeTeam("competition", "team");
        Assert.assertEquals(competition.getTeams().size(), 0);
    }

    @Test
    public void testFindByName() {
        competitionService.findByName("competition");
        verify(competitionDao, times(1)).findByName("competition");
    }

    @Test
    public void testGetAll() {
        competitionService.getAll();
        verify(competitionDao, times(1)).findAll();
    }

    @Test
    public void testFindById() {
        competitionService.findById(1L);
        verify(competitionDao, times(1)).findById(1L);
    }
}