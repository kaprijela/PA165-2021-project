package controllers;

import cz.muni.fi.pa165.esports.dto.CompetitionDTO;
import cz.muni.fi.pa165.esports.facade.CompetitionFacade;
import exceptions.ResourceAlreadyExistingException;
import exceptions.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.Collection;


@RestController
@RequestMapping(ControllerConstants.COMPETITIONS)
@Slf4j
public class CompetitionController {

    @Inject
    CompetitionFacade competitionFacade;

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final Collection<CompetitionDTO> getPlayers() {
        log.debug("rest getPlayers()");
        return competitionFacade.getAllCompetitions();
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final CompetitionDTO createCompetition(@RequestBody CompetitionDTO competitionDTO) throws Exception {
        log.debug("rest createCompetition()");
        try {
            Long competition = competitionFacade.createCompetition(competitionDTO);
            return competitionFacade.findCompetitionById(competition);
        } catch (Exception ex) {
            throw new ResourceAlreadyExistingException();
        }
    }

    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final CompetitionDTO getByName(@PathVariable("name") String name) throws Exception {
        log.debug("rest get by name {}", name);

        CompetitionDTO competitionByName = competitionFacade.findCompetitionByName(name);
        if (competitionByName == null) {
            throw new ResourceNotFoundException();
        }
        return competitionByName;
    }

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final CompetitionDTO getById(@PathVariable("id") Long id) throws Exception {
        log.debug("rest get by id {}", id);

        CompetitionDTO competitionByName = competitionFacade.findCompetitionById(id);
        if (competitionByName == null) {
            throw new ResourceNotFoundException();
        }
        return competitionByName;
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void deleteById(@PathVariable("id") Long id) throws Exception {
        log.debug("rest delete by id {}", id);
        try {
            competitionFacade.deleteCompetition(id);
        } catch (Exception e) {
            throw new ResourceNotFoundException();
        }
    }

    @RequestMapping(value = "/{id}/addTeam/{team}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void addTeamToCompetition(@PathVariable("id") Long id, @PathVariable("team") String team) throws Exception {
        log.debug("rest delete by id {}", id);
        try {
            competitionFacade.addTeam(id, team);
        } catch (Exception e) {
            throw new ResourceNotFoundException();
        }
    }

    @RequestMapping(value = "/{id}/removeTeam/{team}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void removeTeamFromCompetition(@PathVariable("id") Long id, @PathVariable("team") String team) throws Exception {
        log.debug("rest delete by id {}", id);
        try {
            competitionFacade.removeTeam(id, team);
        } catch (Exception e) {
            throw new ResourceNotFoundException();
        }
    }
}
