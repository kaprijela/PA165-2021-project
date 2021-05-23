package controllers;
import cz.muni.fi.pa165.esports.dto.*;
import cz.muni.fi.pa165.esports.facade.CompetitionFacade;
import cz.muni.fi.pa165.esports.facade.PlayerFacade;
import cz.muni.fi.pa165.esports.facade.TeamFacade;
import cz.muni.fi.pa165.esports.facade.UserFacade;
import exception.InvalidRequestException;
import exception.ResourceAlreadyExistingException;
import exception.ResourceNotFoundException;
import exception.ServerProblemException;
import hateoas.PlayerRepresentationModelAssembler;
import hateoas.StatisticsRepresentatitionModelAssembler;
import hateoas.TeamRepresentationModelAssembler;
import hateoas.UserRepresentationModelAssembler;
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
//@ExposesResourceFor(CompetitionDTO.class)
@CrossOrigin
public class UserController {
    @Inject
    UserFacade userFacade;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public boolean login(@RequestBody AuthenticatedUserDTO user) {
        log.info(user.getUsername(), user.getPassword());
        return "admin".equals(user.getUsername()) && "admin".equals(user.getPassword());
    }
/*
    @RequestMapping(value = "", method = RequestMethod.GET)
    public final HttpEntity<CollectionModel<EntityModel<UserDTO>>> getUsers() {
        log.debug("restv1 getUsers()");
        List<UserDTO> allUsers = userFacade.getAllUsers();
        CollectionModel<EntityModel<UserDTO>> entityModels = userRepresentationModelAssembler.toCollectionModel(allUsers);
        entityModels.add(linkTo(PlayerController.class).withSelfRel());
        entityModels.add(linkTo(PlayerController.class).slash("/create").withRel("create"));
        return new ResponseEntity<>(entityModels, HttpStatus.OK);
    }


    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
    public final HttpEntity<CollectionModel<EntityModel<UserDTO>>> getByName(@PathVariable("name") String name) throws Exception {
        log.debug("restv1 get by name {}", name);

        UserDTO userByName = userFacade.findByUsername(name);
        if (userByName == null) {
            throw new ResourceNotFoundException("User not found");
        }
        log.debug("restv1 getPlayers()");
        List<UserDTO> allUsers = userFacade.getAllUsers();
        CollectionModel<EntityModel<UserDTO>> entityModels = userRepresentationModelAssembler.toCollectionModel(allUsers);
        entityModels.add(linkTo(PlayerController.class).withSelfRel());
        entityModels.add(linkTo(PlayerController.class).slash("/create").withRel("create"));
        return new ResponseEntity<>(entityModels, HttpStatus.OK);
    }

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    public final HttpEntity<EntityModel<UserDTO>> getById(@PathVariable("id") Long id) throws Exception {
        log.debug("restv1 get by id {}", id);

        UserDTO userById = userFacade.findById(id);
        if (userById == null) {
            throw new ResourceNotFoundException("PLayer not found");
        }
        return new HttpEntity<>(userRepresentationModelAssembler.toModel(userById));
    }

 */
}

