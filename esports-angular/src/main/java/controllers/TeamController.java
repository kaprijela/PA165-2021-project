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
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@RestController
@RequestMapping("/teams")
public class TeamController {

    TeamFacade teamFacade;
    PlayerFacade playerFacade;
    CompetitionFacade competitionFacade;

    @Inject
    public TeamController(TeamFacade teamFacade,
                          PlayerFacade playerFacade,
                          CompetitionFacade competitionFacade) {
        this.teamFacade = teamFacade;
        this.playerFacade = playerFacade;
        this.competitionFacade = competitionFacade;
    }

    /* GET operations */

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public final Set<TeamDTO> getAllTeams() {
        log.debug("REST getAllTeams()");
        return new HashSet<>(teamFacade.getAllTeams());
    }

    /**
     * Gets a team according to its ID.
     *
     * @param id ID of the sought team
     * @return team ({@link TeamDTO}) with the given abbreviation if it exists
     * @throws ResourceNotFoundException if a team with the given ID does not exist
     */
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public final TeamDTO getById(@PathVariable("id") Long id) throws ResourceNotFoundException {
        log.debug("REST getById({})", id);

        TeamDTO teamDTO = teamFacade.findTeamById(id);
        if (teamDTO == null) {
            throw new ResourceNotFoundException("Team does not exist");
        }
        return teamDTO;
    }

    /**
     * Gets a team according to its abbreviation.
     *
     * @param abbreviation abbreviation of the sought team
     * @return team ({@link TeamDTO}) with the given abbreviation if it exists
     * @throws ResourceNotFoundException if a team with the given abbreviation does not exist
     */
    @GetMapping(value = "/abbr/{abbreviation}", produces = MediaType.APPLICATION_JSON_VALUE)
    public final TeamDTO getByAbbreviation(@PathVariable("abbreviation") String abbreviation) throws ResourceNotFoundException {
        log.debug("REST getByAbbreviation({})", abbreviation);

        TeamDTO teamDTO = teamFacade.findTeamByAbbreviation(abbreviation);
        if (teamDTO == null) {
            throw new ResourceNotFoundException("Team does not exist");
        }
        return teamDTO;
    }

    /**
     * Gets a list of the team's members.
     *
     * @param teamId ID of the examined team
     * @return set of players {@link PlayerDTO}
     */
    @GetMapping(value = "/{id}/players", produces = MediaType.APPLICATION_JSON_VALUE)
    public final Set<PlayerDTO> getPlayers(@PathVariable("id") Long teamId) {
        log.debug("REST getPlayers({})", teamId);

        TeamDTO team = teamFacade.findTeamById(teamId);
        if (team == null) {
            throw new ResourceNotFoundException("Team does not exist");
        }

        return new HashSet<>(team.getPlayers());
    }

    /**
     * Gets a list of the team's members.
     *
     * @param teamAbbreviation abbreviation of the examined team
     * @return set of players {@link PlayerDTO}
     */
    @GetMapping(value = "/abbr/{abbreviation}/players", produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<PlayerDTO> getPlayersByTeamAbbreviation(@PathVariable("abbreviation") String teamAbbreviation) {
        log.debug("REST getPlayersByTeamName({})", teamAbbreviation);

        TeamDTO team = teamFacade.findTeamByAbbreviation(teamAbbreviation);
        if (team == null) {
            throw new ResourceNotFoundException("Team does not exist");
        }

        return new ArrayList<>(team.getPlayers());
    }

    /* POST operations */

    /**
     * Registers a new team in the system.
     *
     * @param teamDTO team to be created
     * @param bindingResult
     * @return newly created team {@link TeamDTO}
     * @throws RuntimeException if the request is not valid or such a team already exists in the system
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public final TeamDTO createTeam(@RequestBody @Valid TeamDTO teamDTO, BindingResult bindingResult) throws RuntimeException {
        log.debug("REST createTeam()");
        if (bindingResult.hasErrors()) {
            log.error("failed validation during createTeam() {}", bindingResult);
            throw new InvalidRequestException("Failed validation");
        }

        try {
            Long team = teamFacade.registerNewTeam(teamDTO);
            return teamFacade.findTeamById(team);
        } catch (Exception ex) {
            throw new ResourceAlreadyExistingException(ex.getMessage()); // FIXME
        }
    }

    /* TODO finish
    @PostMapping(value = "/{id}/players")
    public final PlayerDTO addPlayer(@PathVariable("id") Long teamId) {
        log.debug("REST addPlayer()");
    }
     */

    /* DELETE operations */

    /* TODO check
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public final void deleteById(@PathVariable("id") Long id) throws RuntimeException {
        log.debug("restv1 delete by id {}", id);
        try {
            teamFacade.removeTeam(teamFacade.findTeamById(id));
        } catch (IllegalArgumentException ex) {
            log.error("team " + id + " not found");
            throw new ResourceNotFoundException("competition " + id + " not found");
        } catch (Throwable ex) {
            log.error("cannot delete competition " + id + " :" + ex.getMessage());
            Throwable rootCause = ex;
            while ((ex = ex.getCause()) != null) {
                rootCause = ex;
                log.error("caused by : " + ex.getClass().getSimpleName() + ": " + ex.getMessage());
            }
            throw new ServerProblemException(rootCause.getMessage());
        }
    }
     */

    /*
    //garbage error handling
    @RequestMapping(value = "add/{idTeam}/addPlayer/{idPlayer}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final PlayerDTO addPlayerToTeam(@PathVariable("idTeam") Long idTeam, @PathVariable("idPlayer") Long idPlayer) {
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
    public final PlayerDTO removePlayerFromTeam(@PathVariable("idTeam") Long idTeam, @PathVariable("idPlayer") Long idPlayer) {
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
    public final StatisticsDTO getAverageTeamScoreForCompetition(@PathVariable("id") Long idTeam, @PathVariable("competitionId") Long idCompetition) {
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
     */
}
