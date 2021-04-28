package cz.muni.fi.pa.esports.facade;


import cz.muni.fi.pa.esports.dto.PlayerDTO;

import java.util.Collection;

/**
 * @author Radovan Tomasik
 */

public interface PlayerFacade {
    /**
     * Find a player by id
     * @param id type long
     * @return a player
     */
    PlayerDTO findPlayerById(Long id);

    /**
     * Find a player by name
     * @param Name type string
     * @return a collection of players
     */
    Collection<PlayerDTO> findPlayerByName(String Name);

    /**
     * Register a player, authentication will be added later
     * @param playerDTO a player entity
     */
    void createPlayer(PlayerDTO playerDTO);

    /**
     * Find all player
     * @return a collection of all player
     */
    Collection<PlayerDTO> getAllPlayers();
}
