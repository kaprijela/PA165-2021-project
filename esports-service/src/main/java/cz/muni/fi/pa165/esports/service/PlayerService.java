package cz.muni.fi.pa165.esports.service;

import cz.muni.fi.pa165.esports.entity.Competition;
import cz.muni.fi.pa165.esports.entity.Player;
import cz.muni.fi.pa165.esports.entity.Team;
import cz.muni.fi.pa165.esports.enums.Game;

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
    void remove(Player player);

    /**
     * Get player average stats
     * @param player player entity
     * @return a double
     */
    Double getPlayerAverage(Player player);

    /**
     * Get player's average stats in a game
     * @param player entity
     * @param game enum value
     * @return an average score as a double
     */
    Double getPlayerAverageByGame(Player player, Game game);

    /**
     * Get player's average stats in a competition
     * @param player entity
     * @param competition entity
     * @return an average score as a double
     */
    Double getPlayerAverageByCompetition(Player player, Competition competition);

    /**
     * Create a player
     * @param player a player entity
     */
    Long create(Player player);
}
