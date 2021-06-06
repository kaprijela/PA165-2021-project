package cz.muni.fi.pa165.esports.controllers;

import cz.muni.fi.pa165.esports.dto.AuthenticatedUserDTO;
import cz.muni.fi.pa165.esports.dto.UserDTO;
import cz.muni.fi.pa165.esports.enums.Role;
import cz.muni.fi.pa165.esports.exceptions.InvalidRequestException;
import cz.muni.fi.pa165.esports.exceptions.ResourceNotFoundException;
import cz.muni.fi.pa165.esports.facade.UserFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
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

    @GetMapping(value = "/users/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public final UserDTO getUserById(@PathVariable("id") Long id) throws ResourceNotFoundException {
        log.debug("REST getUserById({})", id);

        UserDTO userById = userFacade.findById(id);
        if (userById == null) {
            throw new ResourceNotFoundException("User not found");
        }
        return userById;
    }
}

