package cz.muni.fi.pa165.esports.service.facade;

import cz.muni.fi.pa165.esports.dto.CompetitionDTO;
import cz.muni.fi.pa165.esports.entity.Competition;
import cz.muni.fi.pa165.esports.service.BeenMappingService;
import cz.muni.fi.pa165.esports.service.CompetitionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

/***
 * @author gavlijan
 */
@RunWith(MockitoJUnitRunner.class)
public class CompetitionFacadeTest {

    @Mock
    CompetitionService competitionService;

    @Mock
    BeenMappingService beanMappingService;

    @InjectMocks
    CompetitionFacadeImpl competitionFacade;

    @BeforeEach
    void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddTeam() {
        competitionFacade.addTeam("competition", "team");
        verify(competitionService, times(1)).addTeam("competition", "team");
    }

    @Test
    public void testRemoveTeam() {
        competitionFacade.removeTeam("competition", "team");
        verify(competitionService, times(1)).removeTeam("competition", "team");
    }

    @Test
    public void testGetAllCompetitions() {
        competitionFacade.getAllCompetitions();
        verify(competitionService, times(1)).getAll();
    }

    @Test
    public void testCreateCompetition() {
        CompetitionDTO competitionDTO = new CompetitionDTO();
        competitionDTO.setName("competitionDTO");
        Competition competition1 = new Competition();
        competition1.setName("competition");
        when(beanMappingService.mapTo(competitionDTO, Competition.class)).thenReturn(competition1);
        competitionFacade.createCompetition(competitionDTO);
        verify(beanMappingService, times(1)).mapTo(competitionDTO, Competition.class);
        verify(competitionService, times(1)).createCompetition(competition1);
    }

    @Test
    public void testGetCompetitionByName() {
        competitionFacade.getCompetitionByName("name");
        verify(competitionService, times(1)).findByName("name");
    }
}