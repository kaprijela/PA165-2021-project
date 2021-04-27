package cz.muni.fi.pa165.esports.service;

import java.util.List;

import cz.muni.fi.pa165.esports.entity.Competition;
import cz.muni.fi.pa165.esports.entity.MatchRecord;
import cz.muni.fi.pa165.esports.entity.Player;
import cz.muni.fi.pa165.esports.entity.Team;

import org.springframework.stereotype.Service;

/**
 * @author Elena Álvarez
 */
@Service
public interface MatchRecordService {
    /**
     * Register a matchRecord in the system.
     *
     * @param matchRecord matchRecord to be registered
     */
    public MatchRecord create(MatchRecord matchRecord);

    /**
     * Remove a matchRecord from the system.
     *
     * @param matchRecord matchRecord to be removed
     */
    public void delete(MatchRecord matchRecord);

    /**
     * Fetch a registered MatchRecord according to its ID.
     *
     * @param id unique ID of a team
     * @return MatchRecord with the given ID, NULL if not found
     */
    public MatchRecord findById(Long id);

    /**
     * Fetches all MatchRecords.
     * @return return all MatchRecords
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
     * @return return all MatchRecords for a given team
     */
    public List<MatchRecord> findByTeam(Team team);

    /**
     * Fetches score.
     * @param matchRecord
     * @return return score for a given matchRecord
     */
    public int getScore(MatchRecord matchRecord);

    /**
     * Fetches Competition.
     * @param matchRecord
     * @return return competition for a given matchRecord
     */
    public Competition getCompetition(MatchRecord matchRecord);

    /**
     * Fetches Competition.
     * @param matchRecord
     * @return return team for a given matchRecord
     */
    public Team getTeam(MatchRecord matchRecord);

    /**
     * Add Competition for a given matchRecord.
     * @param matchRecord
     * @param competition
     */
    public void addCompetition(MatchRecord matchRecord, Competition competition);

    /**
     * Add Team for a given matchRecord.
     * @param matchRecord
     * @param team
     */
    public void addTeam(MatchRecord matchRecord, Team team);
}

