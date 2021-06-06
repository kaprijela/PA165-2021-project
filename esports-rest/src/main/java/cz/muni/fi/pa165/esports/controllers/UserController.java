package cz.muni.fi.pa165.esports.controllers;

import cz.muni.fi.pa165.esports.dto.AuthenticatedUserDTO;
import cz.muni.fi.pa165.esports.dto.UserDTO;
import cz.muni.fi.pa165.esports.enums.Role;
import cz.muni.fi.pa165.esports.exceptions.InvalidRequestException;
import cz.muni.fi.pa165.esports.facade.UserFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Slf4j
@CrossOrigin
@RestController
public class UserController {
    private final UserFacade userFacade;

    @Inject
    public UserController(UserFacade userFacade) {
        this.userFacade = userFacade;
    }

    @PostMapping(value = "/login")
    public Role login(@RequestBody AuthenticatedUserDTO user) {
        if (userFacade.isAuthenticated(user)) {
            return userFacade.isAdmin(userFacade.findByUsername(user.getUsername())) ? Role.ADMIN : Role.PLAYER;
        }
        throw new InvalidRequestException("Wrong credentials");
    }

    @GetMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<UserDTO> getAllUsers() {
        log.debug("REST getAllUsers()");
        return userFacade.getAllUsers();
    }

    /*
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

