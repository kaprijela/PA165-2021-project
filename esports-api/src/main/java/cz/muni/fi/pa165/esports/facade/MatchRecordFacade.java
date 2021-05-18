package cz.muni.fi.pa165.esports.facade;

import cz.muni.fi.pa165.esports.dto.MatchRecordDTO;

import java.util.List;

/**
 * @author Elena Alvarez
 */
public interface MatchRecordFacade {
    /**
     * will create a MatchRecord
     * @param matchRecord matchRecord
     * @return long id of the matchRecord
     */
    Long create(MatchRecordDTO matchRecord);

    /**
     * will delete matchRecord
     * @param matchRecordId id of the matchRecord to be removed
     */
    void delete(Long matchRecordId);

    /**
     * get MatchRecordById
     * @param id id
     * @return matchRecordDao
     */
    MatchRecordDTO findMatchRecordBbyId(Long id);

    /**
     * Return all records
     * @return matchDTO
     */
    List<MatchRecordDTO> getAllMatchRecord();

    /**
     * Return a MatchRecords For a Player
     * @param playerId long
     * @return MatchRecordDTO
     */
    List<MatchRecordDTO> findMatchRecordByPlayer(Long playerId);

    /**
     * Return a MatchRecords for a competition
     * @param competitionId unique identifier string
     * @return MatchRecordDTO
     */
    List<MatchRecordDTO> findMatchRecordByCompetition(String competitionId);

    /**
     * Return a MatchRecords for a team
     * @param teamId unique identifier string
     * @return MatchRecordDTO
     */
    List<MatchRecordDTO> findMatchRecordByTeam(String teamId);
}
