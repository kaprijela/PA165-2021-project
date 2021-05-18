import com.beust.jcommander.internal.Lists;
import com.fasterxml.jackson.databind.ObjectMapper;
import controllers.CompetitionController;
import controllers.ControllerConstants;
import cz.muni.fi.pa165.esports.dto.CompetitionDTO;
import cz.muni.fi.pa165.esports.facade.CompetitionFacade;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import javax.inject.Inject;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebAppConfiguration
@ContextConfiguration(classes = {RootWebContext.class})
public class CompetitionControllerTest {

    @Mock
    private CompetitionFacade competitionFacade;

    @InjectMocks
    @Inject
    private CompetitionController competitionController;

    private MockMvc mockMvc;

    @BeforeClass
    public void setup() {
        MockitoAnnotations.initMocks(this);
        mockMvc = standaloneSetup(competitionController).setMessageConverters(new MappingJackson2HttpMessageConverter()).build();
    }

    @Test
    public void getAllCompetitions() throws Exception {
        doReturn(createCompetitions()).when(competitionFacade).getAllCompetitions();

        mockMvc.perform(get(ControllerConstants.COMPETITIONS))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.[?(@.id==1)].name").value("competition1"))
                .andDo(print());
    }

    @Test
    public void createCompetition() throws Exception {
        CompetitionDTO competition = getCompetition();
        when(competitionFacade.createCompetition(any())).thenReturn(3L);
        when(competitionFacade.findCompetitionById(3L)).thenReturn(competition);

        mockMvc.perform(post(ControllerConstants.COMPETITIONS + "/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(convertObjectToString(competition)))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.[?(@.id==3)].name").value("competition3"))
                .andDo(print());
    }

    @Test
    void findCompetitionByName() throws Exception {
        CompetitionDTO competition = getCompetition();
        when(competitionFacade.findCompetitionByName("competition3")).thenReturn(competition);

        mockMvc.perform(get(ControllerConstants.COMPETITIONS + "/name/competition3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[?(@.id==3)].name").value("competition3"))
                .andDo(print());
    }

    @Test
    void findCompetitionByInvalidName() throws Exception {
        CompetitionDTO competition = getCompetition();
        when(competitionFacade.findCompetitionByName("competition3")).thenReturn(null);

        mockMvc.perform(get(ControllerConstants.COMPETITIONS + "/name/competition3"))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    void findCompetitionById() throws Exception {
        CompetitionDTO competition = getCompetition();
        when(competitionFacade.findCompetitionById(3L)).thenReturn(competition);

        mockMvc.perform(get(ControllerConstants.COMPETITIONS + "/id/3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[?(@.id==3)].name").value("competition3"))
                .andDo(print());
    }

    @Test
    void deleteById() throws Exception {
        mockMvc.perform(get(ControllerConstants.COMPETITIONS + "/delete/3"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void deleteByInvalidId() throws Exception {
        doThrow(NoSuchElementException.class).when(competitionFacade).deleteCompetition(3L);

        mockMvc.perform(get(ControllerConstants.COMPETITIONS + "/delete/3"))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    void addTeamToCompetition() throws Exception {
        mockMvc.perform(get(ControllerConstants.COMPETITIONS + "/3/addTeam/team"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void addInvalidTeamToCompetition() throws Exception {
        doThrow(NoSuchElementException.class).when(competitionFacade).addTeam(3L,"invalidTeam");

        mockMvc.perform(get(ControllerConstants.COMPETITIONS + "/3/addTeam/invalidTeam"))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

    @Test
    void removeTeamFromCompetition() throws Exception {
        mockMvc.perform(get(ControllerConstants.COMPETITIONS + "/3/removeTeam/team"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    private List<CompetitionDTO> createCompetitions() {
        CompetitionDTO competitionDTO = new CompetitionDTO();
        competitionDTO.setId(1L);
        competitionDTO.setName("competition1");

        CompetitionDTO competitionDTO2 = new CompetitionDTO();
        competitionDTO2.setId(2L);
        competitionDTO2.setName("competition2");

        return Lists.newArrayList(competitionDTO, competitionDTO2);
    }

    private CompetitionDTO getCompetition() {
        CompetitionDTO competitionDTO = new CompetitionDTO();
        competitionDTO.setId(3L);
        competitionDTO.setName("competition3");
        return competitionDTO;
    }

    private static String convertObjectToString(Object object) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }
}
