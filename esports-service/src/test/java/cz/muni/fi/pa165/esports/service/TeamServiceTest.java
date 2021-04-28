package cz.muni.fi.pa165.esports.service;

import cz.muni.fi.pa165.esports.dao.TeamDao;
import cz.muni.fi.pa165.esports.entity.Team;
import cz.muni.fi.pa165.esports.service.config.ServiceConfiguration;
import org.hibernate.service.spi.ServiceException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.mockito.Mockito.when;

@ContextConfiguration(classes = ServiceConfiguration.class)
public class TeamServiceTest {
    @Mock
    private TeamDao teamDao;

    @Autowired
    private TeamService teamService;

    private Team t1;
    private Team t2;

    @BeforeMethod
    public void createTeams(){
        t1 = new Team();
        t2 = new Team();

        t1.setAbbreviation("Idk");
    }


    @Test
    public void testAbbreviation(){
        Team t3 = new Team();
        when(teamDao.findByAbbreviation("Idk")).thenReturn(t3);
        t3 = teamService.findByAbbreviation("Idk");
        Assert.assertEquals(t3.getAbbreviation(), t1.getAbbreviation());
    }


}