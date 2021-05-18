package cz.muni.fi.pa165.esports.dao;

import cz.muni.fi.pa165.esports.entity.Competition;
import cz.muni.fi.pa165.esports.enums.Game;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

/**
 * @author jan gavlik
 * originally a regular dao, migrated to CrudRepository in milestone2 -> name stayed
 * *looks slightly cooler now*
 */

public interface CompetitionDao extends CrudRepository<Competition, Long> {

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
