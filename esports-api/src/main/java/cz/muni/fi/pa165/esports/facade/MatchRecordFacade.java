package cz.muni.fi.pa165.esports.facade;

import cz.muni.fi.pa165.esports.dto.CompetitionDTO;
import cz.muni.fi.pa165.esports.dto.MatchRecordDTO;
import cz.muni.fi.pa165.esports.dto.PlayerDTO;
import cz.muni.fi.pa165.esports.dto.TeamDTO;

import java.util.List;

public interface MatchRecordFacade {
    public Long create(MatchRecordDTO matchRecord);
    public void delete(Long matchRecordId);

    public MatchRecordDTO getMatchRecordBbyId(Long id);
    public List<MatchRecordDTO> getAllMatchRecord();
    public List<MatchRecordDTO> getMatchRecordByPlayer(Long playerId);
    public List<MatchRecordDTO> getMatchRecordByCompetition(Long competitionId);
    public List<MatchRecordDTO> getMatchRecordByTeam(Long teamId);

    public int getScore(MatchRecordDTO matchRecord);
    public CompetitionDTO getCompetition(MatchRecordDTO matchRecord);
    public TeamDTO getTeam(MatchRecordDTO matchRecord);

    /*
    public void addCompetition(MatchRecordDTO matchRecord, CompetitionDTO competition);
    public void addTeam(MatchRecordDTO matchRecord, TeamDTO team);

     */
}
