package controllers;

import cz.muni.fi.pa165.esports.dto.CompetitionDTO;
import cz.muni.fi.pa165.esports.dto.PlayerDTO;
import cz.muni.fi.pa165.esports.dto.StatisticsDTO;
import cz.muni.fi.pa165.esports.dto.TeamDTO;
import cz.muni.fi.pa165.esports.facade.CompetitionFacade;
import cz.muni.fi.pa165.esports.facade.PlayerFacade;
import cz.muni.fi.pa165.esports.facade.TeamFacade;
import exception.InvalidRequestException;
import exception.ResourceAlreadyExistingException;
import exception.ResourceNotFoundException;
import exception.ServerProblemException;
import hateoas.StatisticsRepresentatitionModelAssembler;
import hateoas.TeamRepresentationModelAssembler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Slf4j
@RestController
@ExposesResourceFor(CompetitionDTO.class)
@RequestMapping("/teams")
public class TeamController {

    TeamFacade teamFacade;

    @Inject
    public TeamController(TeamFacade teamFacade) {
        this.teamFacade = teamFacade;
    }

    @Inject
    PlayerFacade playerFacade;

    @Inject
    CompetitionFacade competitionFacade;


    @RequestMapping(value = "", method = RequestMethod.GET)
    public final Set<TeamDTO> getAllTeams() {
        log.debug("rest getAllTeams()");
        return new HashSet<>(teamFacade.getAllTeams());
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public final TeamDTO createTeam(@RequestBody @Valid TeamDTO teamDTO, BindingResult bindingResult) throws Exception {
        log.debug("restv1 createTeam()");
        if (bindingResult.hasErrors()) {
            log.error("failed validation {}", bindingResult.toString());
            throw new InvalidRequestException("Failed validation");
        }
        try {
            Long team = teamFacade.registerNewTeam(teamDTO);
            return teamFacade.findTeamById(team);
        } catch (Exception ex) {
            throw new ResourceAlreadyExistingException(ex.getMessage());
        }
    }

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    public final TeamDTO getById(@PathVariable("id") Long id) throws Exception {
        log.debug("restv1 get by id {}", id);

        TeamDTO teamDTO = teamFacade.findTeamById(id);
        if (teamDTO == null) {
            throw new ResourceNotFoundException("Team not found");
        }
        return teamDTO;
    }

    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
    public final TeamDTO getByName(@PathVariable("name") String name) throws Exception {
        log.debug("restv1 get by name {}", name);

        TeamDTO teamByName = teamFacade.findTeamByName(name);
        if (teamByName == null) {
            throw new ResourceNotFoundException("Competition not found");
        }
        return teamByName;
    }

    @RequestMapping(value = "/abbreviation/{abbreviation}", method = RequestMethod.GET)
    public final TeamDTO getByAbbreviation(@PathVariable("abbreviation") String abbreviation) throws Exception {
        log.debug("restv1 get by abbreviation {}",abbreviation);

        TeamDTO teamDTO = teamFacade.findTeamByAbbreviation(abbreviation);
        if (teamDTO == null) {
            throw new ResourceNotFoundException("Competition not found");
        }
        return teamDTO;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void deleteById(@PathVariable("id") Long id) throws Exception {
        log.debug("restv1 delete by id {}", id);
        try {
            teamFacade.removeTeam(teamFacade.findTeamById(id));
        } catch (IllegalArgumentException ex) {
            log.error("team " + id + " not found");
            throw new ResourceNotFoundException("competition " + id + " not found");
        } catch (Throwable ex) {
            log.error("cannot delete competition " + id + " :" + ex.getMessage());
            Throwable rootCause=ex;
            while ((ex = ex.getCause()) != null) {
                rootCause = ex;
                log.error("caused by : " + ex.getClass().getSimpleName() + ": " + ex.getMessage());
            }
            throw new ServerProblemException(rootCause.getMessage());
        }
    }
    //garbage error handling
    @RequestMapping(value = "add/{idTeam}/addPlayer/{idPlayer}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final PlayerDTO addPlayerToTeam(@PathVariable("idTeam") Long idTeam, @PathVariable("idPlayer") Long idPlayer){
        log.debug("restv1 add player: {} to team with id: {}", idPlayer, idTeam);

        TeamDTO teamById = teamFacade.findTeamById(idTeam);
        PlayerDTO playerById = playerFacade.findPlayerById(idPlayer);

        if (teamById == null || playerById == null) {
            throw new ResourceNotFoundException("Competition not found");
        }
        try {
            teamFacade.addPlayerToTeam(teamById, playerById);
        } catch (Exception e) {
            log.error("Exception: {}", e.getMessage());
        }
        return playerById;
    }

    @RequestMapping(value = "remove/{idTeam}/removePlayer/{idPlayer}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final PlayerDTO removePlayerFromTeam(@PathVariable("idTeam") Long idTeam, @PathVariable("idPlayer") Long idPlayer){
        log.debug("restv1 add player: {} to team with id: {}", idPlayer, idTeam);

        TeamDTO teamById = teamFacade.findTeamById(idTeam);
        PlayerDTO playerById = playerFacade.findPlayerById(idPlayer);

        if (teamById == null || playerById == null) {
            throw new ResourceNotFoundException("Competition not found");
        }
        try {
            teamFacade.kickPlayerFromTeam(teamById, playerById);
        } catch (Exception e) {
            log.error("Exception: {}", e.getMessage());
        }
        return playerById;
    }

    @RequestMapping(value = "{id}/getCompetitionStatistics/{competitionId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public final StatisticsDTO getAverageTeamScoreForCompetition(@PathVariable("id") Long idTeam, @PathVariable("competitionId") Long idCompetition){
        log.debug("restv1 get statitistics for team: {} team in competition: {}", idTeam, idTeam);

        TeamDTO teamById = teamFacade.findTeamById(idTeam);
        CompetitionDTO competitionById = competitionFacade.findCompetitionById(idTeam);

        if (teamById == null || competitionById == null) {
            throw new ResourceNotFoundException("Competition not found");
        }
        Double result = null;
        try {
            result = teamFacade.getAverageTeamScoreForCompetition(teamById, competitionById);
        } catch (Exception e) {
            log.error("Exception: {}", e.getMessage());
        }
        StatisticsDTO statisticsDTO = new StatisticsDTO();
        statisticsDTO.setScore(result);
        return statisticsDTO;
    }
}
