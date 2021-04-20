package cz.muni.fi.pa165.esports.dao;

import cz.muni.fi.pa165.esports.entity.Competition;

import java.util.List;

/**
 * @author jan gavlik
 */

public interface CompetitionDao {
    /**
     * Add a Competition to database.
     * @param c Entity to be persisted
     */
    void create(Competition c);

    /**
     * Delete a competition from database.
     * If the Entity is not managed,
     * will be merged and then deleted.
     * @param c Competition to be deleted
     */
    void delete(Competition c);

    /**
     * Find team by its unique id.
     * @param id Long id
     * @return Competition
     */
    Competition findById(Long id);

    /**
     * @return Returns all Competitions
     */
    List<Competition> findAll();

    /**
     * Find a competition unique name
     * @param name Name of Competition
     * @return Return the Competition with name
     */
    Competition findByName(String name);
}
