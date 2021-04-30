package cz.muni.fi.pa165.esports.service.facade;
import java.util.List;

import javax.inject.Inject;

import cz.muni.fi.pa165.esports.dto.MatchRecordDTO;
import cz.muni.fi.pa165.esports.dto.CompetitionDTO;
import cz.muni.fi.pa165.esports.dto.TeamDTO;


import cz.muni.fi.pa165.esports.facade.MatchRecordFacade;
import cz.muni.fi.pa165.esports.entity.Competition;
import cz.muni.fi.pa165.esports.entity.MatchRecord;
import cz.muni.fi.pa165.esports.entity.Player;
import cz.muni.fi.pa165.esports.entity.Team;
import cz.muni.fi.pa165.esports.service.*;

import org.springframework.beans.factory.annotation.Autowired;

/**
 @author Elena Álvarez
 */
public class MatchRecordFacadeImpl implements MatchRecordFacade {
    @Inject
    private MatchRecordService matchRecordService;

    @Inject
    private CompetitionService competitionService;

    @Inject
    private TeamService teamService;

    @Inject
    private PlayerService playerService;

    @Autowired
    private BeenMappingService beanMappingService;


    @Override
    public Long create(MatchRecordDTO matchRecord) {
        MatchRecord mappedMatchRecord = beanMappingService.mapTo(matchRecord, MatchRecord.class);
        Competition mappedCompetition = beanMappingService.mapTo(matchRecord.getCompetition(), Competition.class);
        Team mappedTeam = beanMappingService.mapTo(matchRecord.getTeam(), Team.class);

        mappedMatchRecord.setMatchNumber(matchRecord.getMatchNumber());
        mappedMatchRecord.setCompetition(mappedCompetition);
        mappedMatchRecord.setTeam(mappedTeam);

        MatchRecord newMatchRecord = matchRecordService.create(mappedMatchRecord);
        return newMatchRecord.getId();
    }

    @Override
    public void delete(Long matchRecordId) {
        matchRecordService.delete(matchRecordService.findById(matchRecordId));
    }

    @Override
    public MatchRecordDTO getMatchRecordBbyId(Long id) {
        MatchRecord matchRecord = matchRecordService.findById(id);
        return (matchRecord == null) ? null : beanMappingService.mapTo(matchRecord, MatchRecordDTO.class);
    }

    @Override
    public List<MatchRecordDTO> getAllMatchRecord() {
        return beanMappingService.mapTo(matchRecordService.findAll(), MatchRecordDTO.class);
    }

    @Override
    public List<MatchRecordDTO> getMatchRecordByPlayer(Long playerId) {
        Player player = playerService.findById(playerId);
        List<MatchRecord> matchRecord = matchRecordService.findByPlayer(player);
        return (matchRecord == null) ? null : beanMappingService.mapTo(matchRecord, MatchRecordDTO.class);
    }

    @Override
    public List<MatchRecordDTO> getMatchRecordByCompetition(Long competitionId) {
        Competition competition = competitionService.findById(competitionId).get();
        List<MatchRecord> matchRecord = matchRecordService.findByCompetition(competition);
        return (matchRecord == null) ? null : beanMappingService.mapTo(matchRecord, MatchRecordDTO.class);
    }

    @Override
    public List<MatchRecordDTO> getMatchRecordByTeam(Long teamId) {
        Team team = teamService.findById(teamId);
        List<MatchRecord> matchRecord = matchRecordService.findByTeam(team);
        return (matchRecord == null) ? null : beanMappingService.mapTo(matchRecord, MatchRecordDTO.class);
    }

    @Override
    public int getScore(MatchRecordDTO matchRecord) {

        return matchRecord.getScore();
    }

    @Override
    public CompetitionDTO getCompetition(MatchRecordDTO matchRecord) {

        return matchRecord.getCompetition();
    }

    @Override
    public TeamDTO getTeam(MatchRecordDTO matchRecord) {

        return matchRecord.getTeam();
    }

    /*
    @Override
    public void addCompetition(MatchRecordDTO matchRecord, CompetitionDTO competition) {
    }

    @Override
    public void addTeam(MatchRecordDTO matchRecord, TeamDTO team) {

    }

     */
}
