package cz.muni.fi.pa165.esports.controllers;

import cz.muni.fi.pa165.esports.dto.PlayerDTO;
import cz.muni.fi.pa165.esports.dto.PlayerStatisticsDTO;
import cz.muni.fi.pa165.esports.enums.StatisticType;
import cz.muni.fi.pa165.esports.exceptions.InvalidRequestException;
import cz.muni.fi.pa165.esports.exceptions.ResourceAlreadyExistingException;
import cz.muni.fi.pa165.esports.exceptions.ResourceNotFoundException;
import cz.muni.fi.pa165.esports.exceptions.ServerProblemException;
import cz.muni.fi.pa165.esports.facade.PlayerFacade;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/players")
public class PlayerController {

    private final PlayerFacade playerFacade;

    @Inject
    public PlayerController(PlayerFacade playerFacade) {
        this.playerFacade = playerFacade;
    }

    /**
     * Gets a list of registered players.
     *
     * @return list of {@link PlayerDTO}
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public final List<PlayerDTO> getAllPlayers() {
        log.debug("rest getAllPlayers()");
        return playerFacade.getAllPlayers();
    }

    /**
     * Gets a player according to their ID.
     *
     * @param id ID of the sought player
     * @return {@link PlayerDTO} if player with the given ID exists
     * @throws ResourceNotFoundException if player with the given ID does not exist
     */
    @GetMapping(value = "/{id}")
    public final PlayerDTO getPlayerById(@PathVariable("id") Long id) throws ResourceNotFoundException {
        log.debug("REST getPlayerById({})", id);

        PlayerDTO player = playerFacade.getPlayerById(id);
        if (player == null) {
            throw new ResourceNotFoundException("PLayer not found");
        }
        return player;
    }

    /**
     * Registers a new player in the system.
     *
     * @return newly created player {@link PlayerDTO}
     * @throws InvalidRequestException if the player to be created is not valid
     * @throws ResourceAlreadyExistingException if player with given parameters already exists
     */
    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public final PlayerDTO createPlayer(@RequestBody @Valid PlayerDTO playerDTO, BindingResult bindingResult) {
        log.debug("REST createPlayer()");

        if (bindingResult.hasErrors()) {
            log.error("failed validation {}", bindingResult);
            throw new InvalidRequestException("Failed validation");
        }

        try {
            Long player = playerFacade.createPlayer(playerDTO);
            return playerFacade.getPlayerById(player);
        } catch (Exception ex) {
            throw new ResourceAlreadyExistingException(ex.getMessage()); // FIXME not the only reason an exception can be thrown
        }
    }

    /**
     * Calculates average score for a player.
     *
     * @param playerId ID of the examined player
     * @return {@link PlayerStatisticsDTO} if the player with the given ID exists
     * @throws ResourceNotFoundException if the player with the given ID does not exist
     * @throws ServerProblemException    if the average could not be calculated (server problem)
     */
    @GetMapping(value = "/{id}/statistics/average", produces = MediaType.APPLICATION_JSON_VALUE)
    public final PlayerStatisticsDTO getAveragePlayerScore(@PathVariable("id") Long playerId) {
        log.debug("REST getAveragePlayerScore({})", playerId);

        PlayerDTO playerById = playerFacade.getPlayerById(playerId);
        if (playerById == null) {
            throw new ResourceNotFoundException("Player not found");
        }

        Double result;
        try {
            result = playerFacade.getPlayerAverageScore(playerId);
        } catch (Exception e) {
            log.error("Exception: {}", e.getMessage());
            throw new ServerProblemException("Could not calculate average stats");
        }

        if (result == null) {
            throw new ServerProblemException("Could not calculate average stats");
        }

        return new PlayerStatisticsDTO(playerId, result, StatisticType.AVERAGE);
    }
}
