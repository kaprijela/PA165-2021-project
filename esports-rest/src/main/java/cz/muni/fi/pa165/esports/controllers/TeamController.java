package cz.muni.fi.pa165.esports.controllers;

import cz.muni.fi.pa165.esports.dto.CompetitionDTO;
import cz.muni.fi.pa165.esports.dto.PlayerDTO;
import cz.muni.fi.pa165.esports.dto.StatisticsDTO;
import cz.muni.fi.pa165.esports.dto.TeamDTO;
import cz.muni.fi.pa165.esports.enums.Game;
import cz.muni.fi.pa165.esports.exceptions.EsportsServiceException;
import cz.muni.fi.pa165.esports.facade.CompetitionFacade;
import cz.muni.fi.pa165.esports.facade.PlayerFacade;
import cz.muni.fi.pa165.esports.facade.TeamFacade;
import cz.muni.fi.pa165.esports.exceptions.InvalidRequestException;
import cz.muni.fi.pa165.esports.exceptions.ResourceAlreadyExistingException;
import cz.muni.fi.pa165.esports.exceptions.ResourceNotFoundException;
import cz.muni.fi.pa165.esports.exceptions.ServerProblemException;
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

/**
 * REST controller for the Team entity
 *
 * @author Gabriela Kandova
 */
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

    /**
     * Gets all registered teams.
     *
     * @return set of {@link TeamDTO}
     */
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
     * @throws ResourceNotFoundException if a team with the given ID not found
     */
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public final TeamDTO getById(@PathVariable("id") Long id) throws ResourceNotFoundException {
        log.debug("REST getById({})", id);

        TeamDTO teamDTO = teamFacade.findTeamById(id);
        if (teamDTO == null) {
            throw new ResourceNotFoundException("Team not found");
        }
        return teamDTO;
    }

    /**
     * Gets a team according to its abbreviation.
     *
     * @param abbreviation abbreviation of the sought team
     * @return team ({@link TeamDTO}) with the given abbreviation if it exists
     * @throws ResourceNotFoundException if a team with the given abbreviation is not found
     */
    @GetMapping(value = "/abbr/{abbreviation}", produces = MediaType.APPLICATION_JSON_VALUE)
    public final TeamDTO getByAbbreviation(@PathVariable("abbreviation") String abbreviation) throws ResourceNotFoundException {
        log.debug("REST getByAbbreviation({})", abbreviation);

        TeamDTO teamDTO = teamFacade.findTeamByAbbreviation(abbreviation);
        if (teamDTO == null) {
            throw new ResourceNotFoundException("Team not found");
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
    public final Set<PlayerDTO> getPlayers(@PathVariable("id") Long teamId) throws ResourceNotFoundException {
        log.debug("REST getPlayers({})", teamId);

        TeamDTO team = teamFacade.findTeamById(teamId);
        if (team == null) {
            throw new ResourceNotFoundException("Team not found");
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
    public final List<PlayerDTO> getPlayersByTeamAbbreviation(@PathVariable("abbreviation") String teamAbbreviation) throws ResourceNotFoundException {
        log.debug("REST getPlayersByTeamName({})", teamAbbreviation);

        TeamDTO team = teamFacade.findTeamByAbbreviation(teamAbbreviation);
        if (team == null) {
            throw new ResourceNotFoundException("Team not found");
        }

        return new ArrayList<>(team.getPlayers());
    }

    /**
     * Gets the average score for a team when participating in a competition.
     *
     * @param teamId ID of the examined team
     * @param competitionId ID of the examined competition
     * @return {@link StatisticsDTO}
     */
    @RequestMapping(value = "/{teamId}/competitions/{compId}/statistics/average", produces = MediaType.APPLICATION_JSON_VALUE)
    public final StatisticsDTO getAverageTeamScoreForCompetition(@PathVariable("teamId") Long teamId, @PathVariable("compId") Long competitionId) {
        log.debug("REST get average score for team {} in competition {}", teamId, competitionId);

        TeamDTO teamById = teamFacade.findTeamById(teamId);
        if (teamById == null) {
            throw new ResourceNotFoundException("Team not found");
        }

        CompetitionDTO competitionById = competitionFacade.findCompetitionById(teamId);
        if (competitionById == null) {
            throw new ResourceNotFoundException("Competition not found");
        }

        Double result;
        try {
            result = teamFacade.getAverageTeamScoreForCompetition(teamById, competitionById);
        } catch (Exception e) {
            log.error("Exception: {}", e.getMessage());
            throw new ServerProblemException(e.getMessage());
        }
        StatisticsDTO statisticsDTO = new StatisticsDTO();
        statisticsDTO.setScore(result);
        return statisticsDTO;
    }

    /**
     * Gets the average score of a team in a specific game.
     *
     * @param teamId ID of the examined team
     * @param game string representation of a {@link Game}
     * @return {@link StatisticsDTO}
     * @throws ResourceNotFoundException when the team or game don't exist
     * @throws ServerProblemException when the calculation fails
     */
    @GetMapping(value = "/{teamId}/games/{game}/statistics/average", produces = MediaType.APPLICATION_JSON_VALUE)
    public final StatisticsDTO getAverageTeamScoreForGame(@PathVariable("teamId") Long teamId, @PathVariable("game") String game) {
        log.debug("REST get average score for team {} in game {}", teamId, game);

        TeamDTO teamById = teamFacade.findTeamById(teamId);
        if (teamById == null) {
            throw new ResourceNotFoundException("Team not found");
        }

        Game gameEnum;
        try {
            gameEnum = Game.valueOf(game);
        } catch (IllegalArgumentException iae) {
            throw new ResourceNotFoundException("Game not found");
        }

        Double result;
        try {
            result = teamFacade.getAverageTeamScoreForGame(teamById, gameEnum);
        } catch (Exception ex) {
            log.error("Exception: {}", ex.getMessage());
            throw new ServerProblemException(ex.getMessage());
        }

        StatisticsDTO statisticsDTO = new StatisticsDTO();
        statisticsDTO.setScore(result);
        return statisticsDTO;
    }

    /* POST operations */

    /**
     * Registers a new team in the system.
     *
     * @param teamDTO team to be created
     * @param bindingResult TODO
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

    /**
     * Adds a player to a team.
     *
     * @param teamId ID of the team that is getting a new player
     * @param playerId ID of the player who is joining the team
     */
    @PostMapping(value = "/{teamId}/players/{playerId}")
    public final void addPlayerToTeam(@PathVariable("teamId") Long teamId, @PathVariable("playerId") Long playerId) {
        log.debug("REST add player {} to team {}", playerId, teamId);

        TeamDTO teamById = teamFacade.findTeamById(teamId);
        if (teamById == null) {
            throw new ResourceNotFoundException("Team not found");
        }

        PlayerDTO playerById = playerFacade.findPlayerById(playerId);
        if (playerById == null) {
            throw new ResourceNotFoundException("Player not found");
        }

        try {
            teamFacade.addPlayerToTeam(teamById, playerById);
        } catch (EsportsServiceException e) {
            throw new InvalidRequestException(e.getMessage());
        } catch (Exception e) {
            log.error("Exception: {}", e.getMessage());
            throw new ResourceNotFoundException(e.getMessage());
        }
    }

    /* DELETE operations */

    /**
     * Deletes a registered team from the system.
     *
     * @param id ID of the team to be deleted
     */
    @DeleteMapping(value = "/{id}")
    public final void deleteById(@PathVariable("id") Long id) {
        log.debug("REST delete by id {}", id);
        TeamDTO team = teamFacade.findTeamById(id);

        if (team == null) {
            throw new ResourceNotFoundException("Team not found");
        }

        try {
            teamFacade.removeTeam(team);
        } catch (Exception ex) {
            log.error("Exception: {}", ex.getMessage());
            throw new ServerProblemException(ex.getMessage());
        }
    }

    /**
     * Removes a player from a team.
     *
     * @param teamId ID of the team from which to remove the player
     * @param playerId ID of the player who is leaving the team
     * @throws ResourceNotFoundException if the team or the player do not exist
     * @throws InvalidRequestException if the player is not a member of the team
     * @throws ServerProblemException if something else went wrong
     */
    @DeleteMapping(value = "/{teamId}/players/{playerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public final void removePlayerFromTeam(@PathVariable("teamId") Long teamId, @PathVariable("playerId") Long playerId) {
        log.debug("REST remove player {} from team {}", playerId, teamId);

        TeamDTO teamById = teamFacade.findTeamById(teamId);
        if (teamById == null) {
            throw new ResourceNotFoundException("Team not found");
        }

        PlayerDTO playerById = playerFacade.findPlayerById(playerId);
        if (playerById == null) {
            throw new ResourceNotFoundException("Player not found");
        }

        try {
            teamFacade.removePlayerFromTeam(teamById, playerById);
        } catch (EsportsServiceException e) {
            log.error("Exception: {}", e.getMessage());
            throw new InvalidRequestException(e.getMessage());
        } catch (Exception e) {
            log.error("Exception: {}", e.getMessage());
            throw new ServerProblemException(e.getMessage());
        }
    }
}
