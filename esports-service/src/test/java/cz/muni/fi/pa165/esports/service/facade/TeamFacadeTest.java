package cz.muni.fi.pa165.esports.service.facade;

import cz.muni.fi.pa165.esports.dao.TeamDao;
import cz.muni.fi.pa165.esports.dto.TeamDTO;
import cz.muni.fi.pa165.esports.entity.Competition;
import cz.muni.fi.pa165.esports.entity.Team;
import cz.muni.fi.pa165.esports.facade.TeamFacade;
import cz.muni.fi.pa165.esports.service.BeenMappingService;
import cz.muni.fi.pa165.esports.service.TeamService;
import cz.muni.fi.pa165.esports.service.config.ServiceConfiguration;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;
import org.mockito.InjectMocks;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.testng.Assert.*;

/**
 * @author Radovan Tomasik
 */

@RunWith(MockitoJUnitRunner.class)
public class TeamFacadeTest {

    @Mock
    TeamService teamService;

    @Mock
    BeenMappingService beanMappingService;

    @InjectMocks
    TeamFacadeImpl teamFacade;

    @BeforeEach
    void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetAllTeams() {
        teamFacade.getAllTeams();
        verify(teamService, times(1)).findAll();
    }


    @Test
    public void testGetTeamById() {
        teamFacade.getTeamById(1L);
        verify(teamService, times(1)).findById(1L);
    }


    @Test
    public void testGetTeamByName() {
        teamFacade.getTeamByName("name");
        verify(teamService, times(1)).findByName("name");
    }


    @Test
    public void testGetTeamByAbbreviation() {
        teamFacade.getTeamByAbbreviation("abb");
        verify(teamService, times(1)).findByAbbreviation("abb");
    }

    @Test
    public void  testCreateTeam(){
        TeamDTO teamDTO = new TeamDTO();
        teamDTO.setId(1L);
        teamDTO.setName("Men of Gondor");
        Team menE = new Team();
        menE.setId(1L);
        menE.setName("Men of Gondor");
        when(beanMappingService.mapTo(teamDTO, Team.class)).thenReturn(menE);
        teamFacade.createTeam(teamDTO);
        verify(beanMappingService, times(1)).mapTo(teamDTO, Competition.class);
        verify(teamService, times(1)).create(menE);

    }

    @Test
    public void testDeleteTeam(){
        TeamDTO teamDTO = new TeamDTO();
        teamDTO.setId(1L);
        teamDTO.setName("Men of Gondor");
        Team menE = new Team();
        menE.setId(1L);
        menE.setName("Men of Gondor");
        when(beanMappingService.mapTo(teamDTO, Team.class)).thenReturn(menE);
        teamFacade.createTeam(teamDTO);
        verify(beanMappingService, times(1)).mapTo(teamDTO, Competition.class);
        verify(teamService, times(1)).create(menE);
        teamFacade.removeTeam(teamDTO);
        verify(teamService, times(1)).remove(menE);
    }
}