package cz.muni.fi.pa165.esports.dao;


import cz.muni.fi.pa165.esports.entity.Competition;
import cz.muni.fi.pa165.esports.entity.MatchRecord;
import cz.muni.fi.pa165.esports.entity.Player;
import cz.muni.fi.pa165.esports.entity.Team;

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

    /**
     * Fetches all MatchRecords.
     * @param competition
     * @return return all MatchRecords for a given competition
     */
    public List<MatchRecord> findByCompetition(Competition competition);

    /**
     * Fetches all MatchRecords.
     * @param team
     * @return return all MatchRecords for a given competition
     */
    public List<MatchRecord> findByTeam(Team team);


}

