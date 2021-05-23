package controllers;

import cz.muni.fi.pa165.esports.dto.CompetitionDTO;
import cz.muni.fi.pa165.esports.facade.CompetitionFacade;
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


/**
 * Should be hateoas
 */
@Slf4j
@RestController
@ExposesResourceFor(CompetitionDTO.class)
@RequestMapping("/competitions")
public class CompetitionController {


    @Inject
    private final CompetitionFacade competitionFacade;

    public CompetitionController(CompetitionFacade competitionFacade) {
        this.competitionFacade = competitionFacade;
    }

    /**
     * Get list of Competitions curl -i -X GET http://localhost:8080/esports/api/v2/esports/competitions/
     *
     * @return List<CompetitionDTO>
     */
    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<CompetitionDTO> getAllCompetitions() {
        log.debug("rest getAllCompetitions()");
        return competitionFacade.getAllCompetitions();
    }

    /**
     * Create a Competition
     * curl -X POST -i -H "Content-Type: application/json" --data '{"name":"Fist Fight","location":"behind the bar"}' http://localhost:8080/esports/api/v2/esports/competitions/create
     *
     * @return CompetitionDTO
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
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

    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
    public final CompetitionDTO getByName(@PathVariable("name") String name) throws Exception {
        log.debug("restv1 get by name {}", name);

        CompetitionDTO competitionByName = competitionFacade.findCompetitionByName(name);
        if (competitionByName == null) {
            throw new ResourceNotFoundException("Competition not found");
        }
        return competitionByName;
    }

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    public final CompetitionDTO getById(@PathVariable("id") Long id) throws Exception {
        log.debug("restv1 get by id {}", id);

        CompetitionDTO competitionById = competitionFacade.findCompetitionById(id);
        if (competitionById == null) {
            throw new ResourceNotFoundException("Competition not found");
        }
        return competitionById;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void deleteById(@PathVariable("id") Long id) throws Exception {
        log.debug("restv1 delete by id {}", id);
        try {
            competitionFacade.deleteCompetition(id);
        } catch (IllegalArgumentException ex) {
            log.error("competition " + id + " not found");
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

    @RequestMapping(value = "/{id}/addTeam/{team}", method = RequestMethod.GET)
    public final CompetitionDTO addTeamToCompetition(@PathVariable("id") Long id, @PathVariable("team") String team) throws Exception {
        log.debug("restv1 delete by id {}", id);
        try {
            competitionFacade.addTeam(id, team);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Competition not found");
        }
        return competitionFacade.findCompetitionById(id);
    }

    @RequestMapping(value = "/{id}/removeTeam/{team}", method = RequestMethod.GET)
    public final CompetitionDTO removeTeamFromCompetition(@PathVariable("id") Long id, @PathVariable("team") String team) throws Exception {
        log.debug("restv1 delete by id {}", id);
        try {
            competitionFacade.removeTeam(id, team);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Competition not found");
        }
        return competitionFacade.findCompetitionById(id);
    }
}
