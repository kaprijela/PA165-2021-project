package cz.muni.fi.pa.esports.facade;
import java.util.List;

import javax.inject.Inject;

import cz.muni.fi.pa.esports.dto.MatchRecordDTO;
import cz.muni.fi.pa.esports.dto.CompetitionDTO;
import cz.muni.fi.pa.esports.dto.TeamDTO;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

public class MatchRecordFacadeImpl implements MatchRecordFacade{
    @Inject
    private CompetitionService competitionService;

    @Inject
    private TeamService teamService;

    @Override
    public void create(MatchRecordDTO matchRecord) {

    }

    @Override
    public void delete(Long matchRecordId) {

    }

    @Override
    public MatchRecordDTO getMatchRecordBbyId(Long id) {
        return null;
    }

    @Override
    public List<MatchRecordDTO> getAllMatchRecord() {
        return null;
    }

    @Override
    public List<MatchRecordDTO> getMatchRecordByPlayer(Long playerId) {
        return null;
    }

    @Override
    public List<MatchRecordDTO> getMatchRecordByCompetition(Long competitionId) {
        Competition = competitionService.findById(competitionId);

        return null;
    }

    @Override
    public int getScore(MatchRecordDTO matchRecord) {
        return 0;
    }

    @Override
    public CompetitionDTO getCompetition(MatchRecordDTO matchRecord) {
        return null;
    }

    @Override
    public TeamDTO getTeam(MatchRecordDTO matchRecord) {
        return null;
    }

    @Override
    public void addCompetition(MatchRecordDTO matchRecord, CompetitionDTO competition) {

    }

    @Override
    public void addTeam(MatchRecordDTO matchRecord, TeamDTO team) {

    }
}
