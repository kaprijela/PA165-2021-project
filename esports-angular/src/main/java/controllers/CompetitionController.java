package controllers;
import cz.muni.fi.pa165.esports.dto.CompetitionDTO;
import cz.muni.fi.pa165.esports.facade.CompetitionFacade;
import exception.InvalidRequestException;
import exception.ResourceAlreadyExistingException;
import exception.ResourceNotFoundException;
import exception.ServerProblemException;
import hateoas.CompetitionRepresentationModelAssembler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.*;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

/**
 * Should be hateoas
 */
@Slf4j
@RestController
@ExposesResourceFor(CompetitionDTO.class)
@RequestMapping("/competitions")
public class CompetitionController {

    private final CompetitionFacade competitionFacade;
    CompetitionRepresentationModelAssembler competitionRepresentationModelAssembler;
    //@Inject EntityLinks entityLink;

    @Inject
    public CompetitionController(CompetitionFacade competitionFacade) {
        this.competitionFacade = competitionFacade;
    }

    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<CompetitionDTO> getAllCompetitions() {
        log.debug("rest getAllCompetitions()");
        return competitionFacade.getAllCompetitions();
    }
    /**
     * Get list of Competitions curl -i -X GET http://localhost:8080/esports/api/v2/esports/competitions/
     *
     * @return List<CompetitionDTO>
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public final HttpEntity<CollectionModel<EntityModel<CompetitionDTO>>> getPlayers() {
        log.debug("restv1 getCompetitions()");
        List<CompetitionDTO> allCompetitions = competitionFacade.getAllCompetitions();
        CollectionModel<EntityModel<CompetitionDTO>> entityModels = competitionRepresentationModelAssembler.toCollectionModel(allCompetitions);
        return new ResponseEntity<>(entityModels, HttpStatus.OK);
    }

    /**
     * Create a Competition
     * curl -X POST -i -H "Content-Type: application/json" --data '{"name":"Fist Fight","location":"behind the bar"}' http://localhost:8080/esports/api/v2/esports/competitions/create
     *
     * @return CompetitionDTO
     */
    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public final HttpEntity<EntityModel<CompetitionDTO>> createCompetition(@RequestBody CompetitionDTO competitionDTO, BindingResult bindingResult) throws Exception {
        log.debug("restv1 createCompetition()");
        if (bindingResult.hasErrors()) {
            log.error("failed validation {}", bindingResult.toString());
            throw new InvalidRequestException("Failed validation");
        }
        try {
            Long competition = competitionFacade.createCompetition(competitionDTO);
            return new HttpEntity<>(competitionRepresentationModelAssembler.toModel(competitionFacade.findCompetitionById(competition)));
        } catch (Exception ex) {
            throw new ResourceAlreadyExistingException(ex.getMessage());
        }
    }

    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
    public final HttpEntity<EntityModel<CompetitionDTO>> getByName(@PathVariable("name") String name) throws Exception {
        log.debug("restv1 get by name {}", name);

        CompetitionDTO competitionByName = competitionFacade.findCompetitionByName(name);
        if (competitionByName == null) {
            throw new ResourceNotFoundException("Competition not found");
        }
        return new HttpEntity<>(competitionRepresentationModelAssembler.toModel(competitionByName));
    }

}
