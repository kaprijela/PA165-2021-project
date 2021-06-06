package cz.muni.fi.pa165.esports.controllers;

import cz.muni.fi.pa165.esports.dto.CompetitionDTO;
import cz.muni.fi.pa165.esports.dto.MatchRecordDTO;
import cz.muni.fi.pa165.esports.dto.TeamDTO;
import cz.muni.fi.pa165.esports.facade.CompetitionFacade;
import cz.muni.fi.pa165.esports.facade.MatchRecordFacade;
import cz.muni.fi.pa165.esports.exceptions.InvalidRequestException;
import cz.muni.fi.pa165.esports.exceptions.ResourceAlreadyExistingException;
import cz.muni.fi.pa165.esports.exceptions.ResourceNotFoundException;
import cz.muni.fi.pa165.esports.exceptions.ServerProblemException;
import cz.muni.fi.pa165.esports.facade.TeamFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

@Slf4j
@RestController
@ExposesResourceFor(CompetitionDTO.class)
@RequestMapping("/competitions")
public class CompetitionController {

    private final CompetitionFacade competitionFacade;
    private final MatchRecordFacade matchRecordFacade;
    private final TeamFacade teamFacade;

    @Inject
    public CompetitionController(
        CompetitionFacade competitionFacade,
        TeamFacade teamFacade,
        MatchRecordFacade matchRecordFacade
    ) {
        this.competitionFacade = competitionFacade;
        this.teamFacade = teamFacade;
        this.matchRecordFacade = matchRecordFacade;
    }

    /* GET */

    /**
     * Gets a list of registered competitions.
     *
     * @return list of {@link CompetitionDTO}
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<CompetitionDTO> getAllCompetitions() {
        log.debug("rest getAllCompetitions()");
        return competitionFacade.getAllCompetitions();
    }

    /**
     * Gets a competition by its ID.
     *
     * @param id ID of the sought competition
     * @return {@link CompetitionDTO} if a competition with the given ID exists
     * @throws ResourceNotFoundException if a competition with the given ID does not exist
     */
    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public final CompetitionDTO getCompetitionById(@PathVariable("id") Long id) throws ResourceNotFoundException {
        log.debug("REST getCompetitionById({})", id);

        CompetitionDTO competitionById = competitionFacade.findCompetitionById(id);
        if (competitionById == null) {
            throw new ResourceNotFoundException("Competition not found");
        }
        return competitionById;
    }

    /* POST */

    /**
     * Registers a competition in the system.
     *
     * @return created {@link CompetitionDTO} if the operation was successful
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public final CompetitionDTO createCompetition(@RequestBody CompetitionDTO competitionDTO, BindingResult bindingResult) {
        log.debug("restv1 createCompetition()");
        if (bindingResult.hasErrors()) {
            log.error("failed validation {}", bindingResult);
            throw new InvalidRequestException("Failed validation");
        }
        try {
            Long competition = competitionFacade.createCompetition(competitionDTO);
            return competitionFacade.findCompetitionById(competition);
        } catch (Exception ex) {
            throw new ResourceAlreadyExistingException(ex.getMessage());
        }
    }

    /**
     * Adds a team to the participants of a competition.
     *
     * @param competitionId ID of the competition to which the team is to be added
     * @param teamId ID of the team to be added to the competition
     * @return {@link CompetitionDTO}
     * @throws ResourceNotFoundException if the competition or team with the given id were not found
     * @throws ServerProblemException if the operation failed
     */
    @PostMapping(value = "/{competitionId}/teams/{teamId}")
    public final CompetitionDTO addTeamToCompetition(@PathVariable("competitionId") Long competitionId, @PathVariable("teamId") Long teamId) {
        log.debug("REST add team {} to competition {}", teamId, competitionId);

        CompetitionDTO competitionById = competitionFacade.findCompetitionById(competitionId);
        if (competitionById == null) {
            throw new ResourceNotFoundException("Competition not found");
        }

        TeamDTO teamById = teamFacade.findTeamById(teamId);
        if (teamById == null) {
            throw new ResourceNotFoundException("Team not found");
        }

        try {
            competitionFacade.addTeam(competitionId, teamById.getName());
        } catch (Exception e) {
            throw new ServerProblemException(e.getMessage());
        }
        return competitionFacade.findCompetitionById(competitionId);
    }

    /**
     * Adds a match record to a competition.
     *
     * @param competitionId ID of the competition to which the record is to be added
     * @return newly created {@link MatchRecordDTO}
     */
    @PostMapping("/{id}/records/")
    public final MatchRecordDTO addMatchRecordToCompetition(@PathVariable("id") Long competitionId, @RequestBody MatchRecordDTO matchRecord) {
        log.debug("rest addMatchRecordToCompetition({})", competitionId);

        try {
            Long newId = matchRecordFacade.create(matchRecord);
            return matchRecordFacade.findMatchRecordBbyId(newId);
        } catch (Exception e) {
            throw new ResourceAlreadyExistingException(e.getMessage());
        }
    }

    /* DELETE */

    /**
     * Deletes a registered competition from the system.
     *
     * @param id ID of the competition to be deleted
     */
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public final void deleteById(@PathVariable("id") Long id) {
        log.debug("restv1 delete by id {}", id);
        try {
            competitionFacade.deleteCompetition(id);
        } catch (IllegalArgumentException ex) {
            log.error("competition " + id + " not found");
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

    /**
     * Removes a team from a competition.
     *
     * @param competitionId ID of the competition from which to remove the team
     * @param teamId ID of the team to be removed from the competition
     * @throws ResourceNotFoundException if the competition or team with the given id were not found
     * @throws ServerProblemException if the operation failed
     */
    @DeleteMapping(value = "/{competitionId}/teams/{teamId}")
    public final void removeTeamFromCompetition(@PathVariable("competitionId") Long competitionId, @PathVariable("teamId") Long teamId) {
        log.debug("REST remove team {} from competition {}", teamId, competitionId);

        CompetitionDTO competitionById = competitionFacade.findCompetitionById(competitionId);
        if (competitionById == null) {
            throw new ResourceNotFoundException("Competition not found");
        }

        TeamDTO teamById = teamFacade.findTeamById(teamId);
        if (teamById == null) {
            throw new ResourceNotFoundException("Team not found");
        }

        try {
            competitionFacade.removeTeam(competitionId, teamById.getName());
        } catch (Exception e) {
            throw new ServerProblemException(e.getMessage());
        }
        // TODO: if team is not present at the competition
    }

}
