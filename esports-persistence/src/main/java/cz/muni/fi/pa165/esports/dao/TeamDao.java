package cz.muni.fi.pa165.esports.dao;

import cz.muni.fi.pa165.esports.entity.Player;
import cz.muni.fi.pa165.esports.entity.Team;

import java.util.List;

/**
 * A DAO interface for the team entity.
 *
 * @author Gabriela Kandova
 */
public interface TeamDao extends Dao<Team> {
    /**
     * Adds a team to the database.
     *
     * @param team entity to be persisted
     */
    @Override
    void create(Team team);

    /**
     * Deletes a team from the database.
     *
     * @param team entity to be deleted
     */
    @Override
    void delete(Team team);

    /**
     * Adds a player as a member of a team.
     *
     * @param team team to accept the player
     * @param player player to be added to the team
     */
    void addPlayer(Team team, Player player);

    /**
     * Removes a player from a team.
     *
     * @param team team to kick the player
     * @param player player to be kicked
     */
    void removePlayer(Team team, Player player);

    /**
     * Fetches a team according to its ID from the database.
     *
     * @param id ID of the sought team entity
     * @return team entity if found, else null
     */
    @Override
    Team findById(Long id);

    /**
     * Fetches all teams.
     *
     * @return list of all team entities in the database
     */
    @Override
    List<Team> findAll();

    /**
     * Fetches a team according to its (unique) name.
     *
     * @param name name of the sought team
     * @return team entity if found, else null
     */
    Team findByName(String name);

    /**
     * Fetches a team according to its (unique) abbreviation.
     *
     * @param abbreviation abbreviation of the sought team
     * @return team entity if found, else null
     */
    Team findByAbbreviation(String abbreviation);
}
