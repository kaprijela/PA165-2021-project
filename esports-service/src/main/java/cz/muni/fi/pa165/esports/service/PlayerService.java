package cz.muni.fi.pa165.esports.service;

import cz.muni.fi.pa165.esports.entity.Player;
import cz.muni.fi.pa165.esports.entity.Team;

import java.util.List;

/**
 * Player service layer interface
 *
 * @author Radovan Tomasik
 */
public interface PlayerService {
    /**
     * Returns all players.
     * @return a list of players
     */
    List<Player> getAllPlayers();

    /**
     * Find a player by theirs id
     * @param playerId player id
     * @return a player
     */
    Player findById(Long playerId);

    /**
     * Find a player by their name
     * @param name a name
     * @return a list of players with a specific name
     */
    List<Player> findByName(String name);

    /**
     * Delete a player
     * @param player player entity
     */
    void remove(Player player)
      
    Double getPlayerStatistics(Player player);

     * Create a player
     * @param player a player entity
     */
    void create(Player player);
}
