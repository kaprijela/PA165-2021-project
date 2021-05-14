package controllers;

import cz.muni.fi.pa165.esports.dto.CompetitionDTO;
import cz.muni.fi.pa165.esports.dto.TeamDTO;
import cz.muni.fi.pa165.esports.facade.TeamFacade;
import exception.InvalidRequestException;
import exception.ResourceAlreadyExistingException;
import exception.ResourceNotFoundException;
import exception.ServerProblemException;
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
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Slf4j
@RestController
@ExposesResourceFor(CompetitionDTO.class)
@RequestMapping("/esports/restv1/teams")
public class TeamController {

    @Inject
    TeamFacade teamFacade;

    @Inject
    TeamRepresentationModelAssembler teamRepresentationModelAssembler;

    @Inject
    private EntityLinks entityLink;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public final HttpEntity<CollectionModel<EntityModel<TeamDTO>>> getPlayers() {
        log.debug("restv1 getCompetitions()");
        List<TeamDTO> allTeams = teamFacade.getAllTeams();
        CollectionModel<EntityModel<TeamDTO>> entityModels = teamRepresentationModelAssembler.toCollectionModel(allTeams);
        entityModels.add(linkTo(CompetitionController.class).withSelfRel());
        entityModels.add(linkTo(CompetitionController.class).slash("/create").withRel("create"));
        return new ResponseEntity<>(entityModels, HttpStatus.OK);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public final HttpEntity<EntityModel<TeamDTO>> createCompetition(@RequestBody TeamDTO teamDTO, BindingResult bindingResult) throws Exception {
        log.debug("restv1 createTeam()");
        if (bindingResult.hasErrors()) {
            log.error("failed validation {}", bindingResult.toString());
            throw new InvalidRequestException("Failed validation");
        }
        try {
            Long team = teamFacade.registerNewTeam(teamDTO);
            return new HttpEntity<>(teamRepresentationModelAssembler.toModel(teamFacade.getTeamById(team)));
        } catch (Exception ex) {
            throw new ResourceAlreadyExistingException(ex.getMessage());
        }
    }

    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
    public final HttpEntity<EntityModel<TeamDTO>> getByName(@PathVariable("name") String name) throws Exception {
        log.debug("restv1 get by name {}", name);

        TeamDTO teamByName = teamFacade.getTeamByName(name);
        if (teamByName == null) {
            throw new ResourceNotFoundException("Competition not found");
        }
        return new HttpEntity<>(teamRepresentationModelAssembler.toModel(teamByName));
    }

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    public final HttpEntity<EntityModel<TeamDTO>> getById(@PathVariable("id") Long id) throws Exception {
        log.debug("restv1 get by id {}", id);

        TeamDTO teamDTO = teamFacade.getTeamById(id);
        if (teamDTO == null) {
            throw new ResourceNotFoundException("Competition not found");
        }
        return new HttpEntity<>(teamRepresentationModelAssembler.toModel(teamDTO));
    }

    @RequestMapping(value = "/id/{abbreviation}", method = RequestMethod.GET)
    public final HttpEntity<EntityModel<TeamDTO>> getByAbbreviation(@PathVariable("abbreviation") String abbreviation) throws Exception {
        log.debug("restv1 get by abbreviation {}",abbreviation);

        TeamDTO teamDTO = teamFacade.getTeamByAbbreviation(abbreviation);
        if (teamDTO == null) {
            throw new ResourceNotFoundException("Competition not found");
        }
        return new HttpEntity<>(teamRepresentationModelAssembler.toModel(teamDTO));
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public final void deleteById(@PathVariable("id") Long id) throws Exception {
        log.debug("restv1 delete by id {}", id);
        try {
            teamFacade.removeTeam(teamFacade.getTeamById(id));
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

}
