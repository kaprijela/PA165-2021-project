package cz.fi.muni.pa165.esports.dao;


import cz.fi.muni.pa165.esports.entity.MatchRecord;
import cz.fi.muni.pa165.esports.entity.Player;

import java.util.List;

/**
 * A DAO interface for the MatchRecord entity.
 *
 * @author Elena Álvarez
 */
public interface MatchRecordDao {
    /**
     * Adds a MatchRecord to the database.
     * @param c entity to be persisted
     */
    public void create(MatchRecord c);

    /**
     * Deletes a MatchRecord from the database.
     * @param c entity to be deleted
     */
    public void delete(MatchRecord c);

    /**
     * Fetches a MatchRecord according to its ID from the database.
     * @param id of the sought MatchRecord entity
     * @return MatchRecord entity if found, else null
     */
    public MatchRecord findById(Long id);

    /**
     * Fetches all MatchRecords.
     * @return list of all MatchRecord entities in the database
     */
    public List<MatchRecord> findAll();

    /**
     * Fetches all MatchRecords.
     * @param player
     * @return return all MatchRecords for a given player 
     */
    public List<MatchRecord> findByPlayer(Player player);


}

