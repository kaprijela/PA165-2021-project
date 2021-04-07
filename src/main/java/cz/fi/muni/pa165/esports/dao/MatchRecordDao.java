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
     * @param id of the sought team entity
     * @return MatchRecord entity if found, else null
     */
    public MatchRecord findById(Long id);

    /**
     * Fetches all MatchRecords.
     * @return list of all MatchRecord entities in the database
     */
    public List<MatchRecord> findAll();


    /**
     * Fetches a MatchRecords according to its match_number.
     * @param matchnumber of the MatchRecords
     * @return MatchRecords entity if found, else null
     */
    public MatchRecord findByMatchNumber(Long matchnumber);

    /**
     * Fetches all players.
     * @param matchnumber of the MatchRecords
     * @return list of all Players entities with that match_number and Competition in the database
     */
    public List<Player> findPlayers(Long matchnumber);
}

