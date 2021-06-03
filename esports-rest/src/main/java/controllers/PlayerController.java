package controllers;

import cz.muni.fi.pa165.esports.dto.CompetitionDTO;
import cz.muni.fi.pa165.esports.dto.PlayerDTO;
import cz.muni.fi.pa165.esports.dto.StatisticsDTO;
import cz.muni.fi.pa165.esports.facade.PlayerFacade;
import exception.InvalidRequestException;
import exception.ResourceAlreadyExistingException;
import exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.server.ExposesResourceFor;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@ExposesResourceFor(CompetitionDTO.class)
@RequestMapping("/players")
public class PlayerController {

    private final PlayerFacade playerFacade;

    /**
     * Get list of Players curl -i -X GET http://localhost:8080/pa165/api/v2/players/
     *
     * @return List<PlayerDTO>
     */
    @RequestMapping(value = "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<PlayerDTO> getPlayers() {
        log.debug("rest getPlayers()");
        return playerFacade.getAllPlayers();
    }

    /**
     * Create a Player
     *
     * @return created PlayerDTO
     */
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

    /**
     * GET a Player by name
     *
     * @return PlayerDTO
     */
    @RequestMapping(value = "/name/{name}", method = RequestMethod.GET)
    public final List<PlayerDTO> getByName(@PathVariable("name") String name) throws Exception {
        log.debug("restv1 get by name {}", name);

        return playerFacade.findPlayerByName(name);
    }

    /**
     * GET a Player by ID
     *
     * @return PlayerDTO
     */
    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET)
    public final PlayerDTO getById(@PathVariable("id") Long id) throws Exception {
        log.debug("restv1 get by id {}", id);
        PlayerDTO player = playerFacade.findPlayerById(id);
        if (player == null) {
            throw new ResourceNotFoundException("PLayer not found");
        }
        return player;
    }

    /**
     * GET a Player Statistics by Player ID
     *
     * @return statisticsDTO
     */
    @RequestMapping(value = "getPlayerStatistics/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public final StatisticsDTO getAveragePlayerScore(@PathVariable("id") Long idPlayer) {
        log.debug("restv1 get statitistics for Player: {}", idPlayer);

        Double result = null;
        try {
            result = playerFacade.getPlayerAverage(idPlayer);
        } catch (Exception e) {
            log.error("Exception: {}", e.getMessage());
        }
        if (result == null) {
            throw new IllegalArgumentException("curak");
        }
        StatisticsDTO statisticsDTO = new StatisticsDTO();
        statisticsDTO.setScore(result);
        return statisticsDTO;
    }

    public PlayerController(PlayerFacade playerFacade) {
        this.playerFacade = playerFacade;
    }
}