package cz.fi.muni.pa165.esports.dao;

import cz.fi.muni.pa165.esports.entity.Competition;

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
     * @return Competion
     */
    Competition findById(Long id);

    /**
     * @return Returns all Competions
     */
    List<Competition> findAll();

    /**
     * Find a competion unique name
     * @param name Name of Competition
     * @return Return the Competion with name
     */
    Competition findByName(String name);
}
