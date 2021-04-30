package cz.muni.fi.pa165.esports.facade;

import cz.muni.fi.pa165.esports.dto.MatchRecordDTO;

import java.util.List;

/**
 * @author elena
 */
public interface MatchRecordFacade {
    /**
     * will create a MatchRecord
     * @param matchRecord matchRecord
     * @return long
     */
    public Long create(MatchRecordDTO matchRecord);

    /**
     * will delete matchRecord
     * @param matchRecordId matchRecord
     */
    public void delete(Long matchRecordId);

    /**
     * get MatchRecordById
     * @param id id
     * @return matchRecordDao
     */
    public MatchRecordDTO getMatchRecordBbyId(Long id);

    /**
     * Return all records
     * @return matchDTO
     */
    public List<MatchRecordDTO> getAllMatchRecord();

    /**
     * Return a MatchRecords For a Player
     * @param playerId long
     * @return MatchRecordDTO
     */
    public List<MatchRecordDTO> getMatchRecordByPlayer(Long playerId);

    /**
     * Return a MatchRecords for a competition
     * @param competitionId unique identifier string
     * @return MatchRecordDTO
     */
    public List<MatchRecordDTO> getMatchRecordByCompetition(String competitionId);

    /**
     * Return a MatchRecords for a team
     * @param teamId unique identifier string
     * @return MatchRecordDTO
     */
    public List<MatchRecordDTO> getMatchRecordByTeam(String teamId);
}
