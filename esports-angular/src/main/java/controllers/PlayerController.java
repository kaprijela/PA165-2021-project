
package controllers;

import cz.muni.fi.pa165.esports.dto.CompetitionDTO;
import cz.muni.fi.pa165.esports.dto.PlayerDTO;
import cz.muni.fi.pa165.esports.dto.TeamDTO;
import cz.muni.fi.pa165.esports.facade.PlayerFacade;
import exception.InvalidRequestException;
import exception.ResourceAlreadyExistingException;
import exception.ResourceNotFoundException;
import hateoas.PlayerRepresentationModelAssembler;
import hateoas.StatisticsRepresentatitionModelAssembler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@ExposesResourceFor(CompetitionDTO.class)
@RequestMapping("/players")
public class PlayerController {

    private final PlayerFacade playerFacade;


    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<PlayerDTO> getPlayers() {
        log.debug("rest getPlayers()");
        return playerFacade.getAllPlayers();
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public final PlayerDTO createPlayer(@RequestBody @Valid PlayerDTO playerDTO, BindingResult bindingResult) throws Exception {
        log.debug("restv1 createPlayer()");
        if (bindingResult.hasErrors()) {
            log.error("failed validation {}", bindingResult.toString());
            throw new InvalidRequestException("Failed validation");
        }
        try {
            Long player = playerFacade.createPlayer(playerDTO);
            return playerFacade.findPlayerById(player);
        } catch (Exception ex) {
            throw new ResourceAlreadyExistingException(ex.getMessage());
        }
    }

    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
    public final List<PlayerDTO> getByName(@PathVariable("name") String name) throws Exception {
        log.debug("restv1 get by name {}", name);

        return playerFacade.findPlayerByName(name);
    }

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    public final PlayerDTO getById(@PathVariable("id") Long id) throws Exception {
        log.debug("restv1 get by id {}", id);
        PlayerDTO player = playerFacade.findPlayerById(id);
        if (player == null) {
            throw new ResourceNotFoundException("PLayer not found");
        }
        return player;
    }

    @RequestMapping(value = "getPlayerStatistics/{playerId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public final Double getAveragePlayerScore(@PathVariable("id") Long idPlayer){
        log.debug("restv1 get statitistics for Player: {}", idPlayer);

        PlayerDTO playerById = playerFacade.findPlayerById(idPlayer);

        if (playerById == null) {
            throw new ResourceNotFoundException("Competition not found");
        }
        Double result = null;
        try {
            result = playerFacade.getPlayerAverage(playerById);
        } catch (Exception e) {
            log.error("Exception: {}", e.getMessage());
        }
        assert result != null;
        return result;
    }

    public PlayerController(PlayerFacade playerFacade) {
        this.playerFacade = playerFacade;
    }
}