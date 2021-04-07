package cz.fi.muni.pa165.esports.dao;

import cz.fi.muni.pa165.esports.entity.Player;
import cz.fi.muni.pa165.esports.enums.Gender;

import java.util.List;

/**
 * @author Radovan Tomasik
 */
public interface PlayerDao {
    /**
     * Find a player with a specific id
     * @param id Player_id
     * @return a Player
     */
    public Player findById(Long id);

    /**
     * Add a player to the databse
     * @param player player
     */
    public void create(Player player);

    /**
     * Remove a player from database
     * @param player a specific player
     */
    public void delete(Player player);

    /**
     * Find all the players in the database
     * @return a list of players
     */
    public List<Player> findAll();

    /**
     * Find all players with a specific name
     * @param name player name
     * @return a list of players
     */
    public List<Player> findByName(String name);

    /**
     * Find all players with a specific gender
     * @param gender Gender enum
     * @return a list of players
     */
    public List<Player> findByGender(Gender gender);
}
