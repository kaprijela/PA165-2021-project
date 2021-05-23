package controllers;

import cz.muni.fi.pa165.esports.dto.CompetitionDTO;
import cz.muni.fi.pa165.esports.dto.PlayerDTO;
import cz.muni.fi.pa165.esports.facade.PlayerFacade;
import exception.InvalidRequestException;
import exception.ResourceAlreadyExistingException;
import exception.ResourceNotFoundException;
import hateoas.PlayerRepresentationModelAssembler;
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
import javax.validation.Valid;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Slf4j
@RestController
@ExposesResourceFor(CompetitionDTO.class)
@RequestMapping("/esports/players")
public class PlayerController {
    @Inject
    PlayerFacade playerFacade;

    @Inject
    PlayerRepresentationModelAssembler playerRepresentationModelAssembler;

    @Inject
    private EntityLinks entityLink;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public final HttpEntity<CollectionModel<EntityModel<PlayerDTO>>> getPlayers() {
        log.debug("restv1 getPlayers()");
        List<PlayerDTO> allPlayers = playerFacade.getAllPlayers();
        CollectionModel<EntityModel<PlayerDTO>> entityModels = playerRepresentationModelAssembler.toCollectionModel(allPlayers);
        entityModels.add(linkTo(PlayerController.class).withSelfRel());
        entityModels.add(linkTo(PlayerController.class).slash("/create").withRel("create"));
        return new ResponseEntity<>(entityModels, HttpStatus.OK);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public final HttpEntity<EntityModel<PlayerDTO>> createPlayer(@RequestBody @Valid PlayerDTO playerDTO, BindingResult bindingResult) throws Exception {
        log.debug("restv1 createPlayer()");
        if (bindingResult.hasErrors()) {
            log.error("failed validation {}", bindingResult.toString());
            throw new InvalidRequestException("Failed validation");
        }
        try {
            Long player = playerFacade.createPlayer(playerDTO);
            return new HttpEntity<>(playerRepresentationModelAssembler.toModel(playerFacade.findPlayerById(player)));
        } catch (Exception ex) {
            throw new ResourceAlreadyExistingException(ex.getMessage());
        }
    }

    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
    public final List<PlayerDTO> getByName(@PathVariable("name") String name) throws Exception {
        log.debug("restv1 get by name {}", name);

        return playerFacade.findPlayerByName(name);
//        if (playersByName == null) {
//            throw new ResourceNotFoundException("Players not found");
//        }
//        log.debug("restv1 getPlayers()");
//        List<PlayerDTO> allPlayers = playerFacade.getAllPlayers();
//        CollectionModel<EntityModel<PlayerDTO>> entityModels = playerRepresentationModelAssembler.toCollectionModel(allPlayers);
//        entityModels.add(linkTo(PlayerController.class).withSelfRel());
//        entityModels.add(linkTo(PlayerController.class).slash("/create").withRel("create"));
//        return new ResponseEntity<>(entityModels, HttpStatus.OK);
    }

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    public final HttpEntity<EntityModel<PlayerDTO>> getById(@PathVariable("id") Long id) throws Exception {
        log.debug("restv1 get by id {}", id);

        PlayerDTO playerById = playerFacade.findPlayerById(id);
        if (playerById == null) {
            throw new ResourceNotFoundException("PLayer not found");
        }
        return new HttpEntity<>(playerRepresentationModelAssembler.toModel(playerById));
    }


}
