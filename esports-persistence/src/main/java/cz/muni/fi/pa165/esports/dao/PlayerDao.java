package cz.muni.fi.pa165.esports.dao;

import cz.muni.fi.pa165.esports.entity.Player;
import cz.muni.fi.pa165.esports.enums.Gender;

import java.util.List;

/**
 * @author Radovan Tomasik
 */
public interface PlayerDao extends Dao<Player> {
    /**
     * Find a player with a specific id
     *
     * @param id Player_id
     * @return a Player
     */
    @Override
    Player findById(Long id);

    /**
     * Add a player to the database
     *
     * @param player player
     */
    @Override
    void create(Player player);

    /**
     * Remove a player from database
     *
     * @param player a specific player
     */
    @Override
    void delete(Player player);

    /**
     * Find all the players in the database
     *
     * @return a list of players
     */
    @Override
    List<Player> findAll();

    /**
     * Find all players with a specific name
     *
     * @param name player name
     * @return a list of players
     */
    List<Player> findByName(String name);

    /**
     * Find all players with a specific gender
     *
     * @param gender Gender enum
     * @return a list of players
     */
    List<Player> findByGender(Gender gender);
}
