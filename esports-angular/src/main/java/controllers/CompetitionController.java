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

import javax.inject.Inject;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Slf4j
@RestController
@ExposesResourceFor(CompetitionDTO.class)
@RequestMapping("/esports/restv1/competitions")
public class CompetitionController {

    @Inject
    CompetitionFacade competitionFacade;

    @Inject
    CompetitionRepresentationModelAssembler competitionRepresentationModelAssembler;

    @Inject
    private EntityLinks entityLink;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public final HttpEntity<CollectionModel<EntityModel<CompetitionDTO>>> getPlayers() {
        log.debug("restv1 getCompetitions()");
        List<CompetitionDTO> allCompetitions = competitionFacade.getAllCompetitions();
        CollectionModel<EntityModel<CompetitionDTO>> entityModels = competitionRepresentationModelAssembler.toCollectionModel(allCompetitions);
        entityModels.add(linkTo(CompetitionController.class).withSelfRel());
        entityModels.add(linkTo(CompetitionController.class).slash("/create").withRel("create"));
        return new ResponseEntity<>(entityModels, HttpStatus.OK);
    }

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

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    public final HttpEntity<EntityModel<CompetitionDTO>> getById(@PathVariable("id") Long id) throws Exception {
        log.debug("restv1 get by id {}", id);

        CompetitionDTO competitionById = competitionFacade.findCompetitionById(id);
        if (competitionById == null) {
            throw new ResourceNotFoundException("Competition not found");
        }
        return new HttpEntity<>(competitionRepresentationModelAssembler.toModel(competitionById));
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
    public final  HttpEntity<EntityModel<CompetitionDTO>> addTeamToCompetition(@PathVariable("id") Long id, @PathVariable("team") String team) throws Exception {
        log.debug("restv1 delete by id {}", id);
        try {
            competitionFacade.addTeam(id, team);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Competition not found");
        }
        return new HttpEntity<>(competitionRepresentationModelAssembler.toModel(competitionFacade.findCompetitionById(id)));
    }

    @RequestMapping(value = "/{id}/removeTeam/{team}", method = RequestMethod.GET)
    public final  HttpEntity<EntityModel<CompetitionDTO>> removeTeamFromCompetition(@PathVariable("id") Long id, @PathVariable("team") String team) throws Exception {
        log.debug("restv1 delete by id {}", id);
        try {
            competitionFacade.removeTeam(id, team);
        } catch (Exception e) {
            throw new ResourceNotFoundException("Competition not found");
        }
        return new HttpEntity<>(competitionRepresentationModelAssembler.toModel(competitionFacade.findCompetitionById(id)));

    }
}
