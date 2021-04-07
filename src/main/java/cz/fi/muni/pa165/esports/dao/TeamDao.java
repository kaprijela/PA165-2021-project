package cz.fi.muni.pa165.esports.dao;

import cz.fi.muni.pa165.esports.entity.Team;

import java.util.List;

/**
 * A DAO interface for the team entity.
 *
 * @author Gabriela Kandova
 */

public interface TeamDao {
    /**
     * Adds a team to the database.
     *
     * @param team entity to be persisted
     */
    void create(Team team);

    /**
     * Deletes a team from the database.
     *
     * @param team entity to be deleted
     */
    void delete(Team team);

    /**
     * Fetches a team according to its ID from the database.
     *
     * @param id ID of the sought team entity
     * @return team entity if found, else null
     */
    Team findById(Long id);

    /**
     * Fetches all teams.
     *
     * @return list of all team entities in the database
     */
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
