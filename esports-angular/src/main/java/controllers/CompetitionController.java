package controllers;

import cz.muni.fi.pa165.esports.dto.CompetitionDTO;
import cz.muni.fi.pa165.esports.dto.MatchRecordDTO;
import cz.muni.fi.pa165.esports.facade.CompetitionFacade;
import cz.muni.fi.pa165.esports.facade.MatchRecordFacade;
import exception.InvalidRequestException;
import exception.ResourceAlreadyExistingException;
import exception.ResourceNotFoundException;
import exception.ServerProblemException;
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

    @Inject
    public CompetitionController(CompetitionFacade competitionFacade, MatchRecordFacade matchRecordFacade) {
        this.competitionFacade = competitionFacade;
        this.matchRecordFacade = matchRecordFacade;
    }

    /**
     * Get list of Competitions curl -i -X GET http://localhost:8080/pa165/api/v2/competitions/
     *
     * @return List<CompetitionDTO>
     */
    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<CompetitionDTO> getAllCompetitions() {
        log.debug("rest getAllCompetitions()");
        return competitionFacade.getAllCompetitions();
    }

    /**
     * Create a Competition
     *
     * @return created CompetitionDTO
     */
    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public final CompetitionDTO createCompetition(@RequestBody CompetitionDTO competitionDTO, BindingResult bindingResult) throws Exception {
        log.debug("restv1 createCompetition()");
        if (bindingResult.hasErrors()) {
            log.error("failed validation {}", bindingResult.toString());
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
     * GET a Competition by name
     *
     * @return CompetitionDTO
     */
    @GetMapping(value = "/name/{name}")
    public final CompetitionDTO getByName(@PathVariable("name") String name) throws Exception {
        log.debug("restv1 get by name {}", name);

        CompetitionDTO competitionByName = competitionFacade.findCompetitionByName(name);
        if (competitionByName == null) {
            throw new ResourceNotFoundException("Competition not found");
        }
        return competitionByName;
    }

    /**
     * GET a Competition by name
     *
     * @return CompetitionDTO
     */
    @GetMapping(value = "/id/{id}")
    public final CompetitionDTO getById(@PathVariable("id") Long id) throws Exception {
        log.debug("restv1 get by id {}", id);

        CompetitionDTO competitionById = competitionFacade.findCompetitionById(id);
        if (competitionById == null) {
            throw new ResourceNotFoundException("Competition not found");
        }
        return competitionById;
    }

    /**
     * Delete a Competition
     */
    @DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public final void deleteById(@PathVariable("id") Long id) throws Exception {
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
     * ADD a Team to a Competition by Team and Competition ID
     *
     * @return competitionDTO
     */
    @RequestMapping(value = "add/{id}/addTeam/{team}", method = RequestMethod.GET)
    public final CompetitionDTO addTeamToCompetition(@PathVariable("id") Long id, @PathVariable("team") String team) throws Exception {
        log.debug("restv1 delete by id {}", id);
        try {
            competitionFacade.addTeam(id, team);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Competition not found");
        }
        return competitionFacade.findCompetitionById(id);
    }

    /**
     * Remove a Team from Competition by Team and Competition ID
     *
     */
    @RequestMapping(value = "remove/{id}/removeTeam/{team}", method = RequestMethod.GET)
    public final CompetitionDTO removeTeamFromCompetition(@PathVariable("id") Long id, @PathVariable("team") String team) throws Exception {
        log.debug("restv1 delete by id {}", id);
        try {
            competitionFacade.removeTeam(id, team);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Competition not found");
        }
        return competitionFacade.findCompetitionById(id);
    }

    /**
     * ADD Matchrecord to a Competition
     *
     * @return competitionDTO
     */
    @PostMapping("/{id}/records/")
    public final MatchRecordDTO addMatchRecordToCompetition(@PathVariable("id") Long competitionId, @RequestBody MatchRecordDTO matchRecord) {
        log.debug("rest add match record");
        try {
            Long newId = matchRecordFacade.create(matchRecord);
            return matchRecordFacade.findMatchRecordBbyId(newId);
        } catch (Exception e) {
            throw new ResourceAlreadyExistingException(e.getMessage());
        }
    }
}
