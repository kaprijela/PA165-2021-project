package controllers;
import cz.muni.fi.pa165.esports.dto.*;

import cz.muni.fi.pa165.esports.dto.CompetitionDTO;
import cz.muni.fi.pa165.esports.dto.PlayerDTO;
import cz.muni.fi.pa165.esports.dto.TeamDTO;
import cz.muni.fi.pa165.esports.dto.UserDTO;
import cz.muni.fi.pa165.esports.enums.Role;
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
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Slf4j
@RestController
@CrossOrigin
public class UserController {
    @Inject
    UserFacade userFacade;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public List<Role> login(@RequestBody AuthenticatedUserDTO user) {
        log.info(user.getUsername(), user.getPassword());
        if ("teamAdmin".equals(user.getUsername()) && "admin".equals(user.getPassword())) {
            return Arrays.asList(Role.TEAM_MANAGER, Role.PLAYER);
        }
        if ("compAdmin".equals(user.getUsername()) && "admin".equals(user.getPassword())) {
            return Collections.singletonList(Role.COMPETITION_MANAGER);
        }
        if ("player".equals(user.getUsername()) && "player".equals(user.getPassword())) {
            return Collections.singletonList(Role.PLAYER);
        }
        return null;
    }
/*
    @RequestMapping(value = "", method = RequestMethod.GET)
    public final List<UserDTO> getUsers() {
        log.debug("restv2 getUsers()");
        return userFacade.getAllUsers();
    }


    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
    public final List<UserDTO> getByName(@PathVariable("name") String name) throws Exception {
        log.debug("restv2 get by name {}", name);

        UserDTO userByName = userFacade.findByUsername(name);
        if (userByName == null) {
            throw new ResourceNotFoundException("User not found");
        }
        log.debug("restv2 getPlayers()");
        return userFacade.getAllUsers();
    }

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    public final UserDTO getById(@PathVariable("id") Long id) throws Exception {
        log.debug("restv2 get by id {}", id);

        UserDTO userById = userFacade.findById(id);
        if (userById == null) {
            throw new ResourceNotFoundException("PLayer not found");
        }
        return userById;
    }
 */
}

