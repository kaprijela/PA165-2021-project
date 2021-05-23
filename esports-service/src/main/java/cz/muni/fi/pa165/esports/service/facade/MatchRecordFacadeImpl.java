package cz.muni.fi.pa165.esports.service.facade;

import cz.muni.fi.pa165.esports.dto.MatchRecordDTO;
import cz.muni.fi.pa165.esports.entity.Competition;
import cz.muni.fi.pa165.esports.entity.MatchRecord;
import cz.muni.fi.pa165.esports.entity.Player;
import cz.muni.fi.pa165.esports.entity.Team;
import cz.muni.fi.pa165.esports.facade.MatchRecordFacade;
import cz.muni.fi.pa165.esports.service.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 * @author Elena Álvarez
 */
@Service
@Transactional
public class MatchRecordFacadeImpl implements MatchRecordFacade {

    @Inject
    private MatchRecordService matchRecordService;

    @Inject
    private CompetitionService competitionService;

    @Inject
    private TeamService teamService;

    @Inject
    private PlayerService playerService;

    @Inject
    private BeanMappingService beanMappingService;


    @Override
    public Long create(MatchRecordDTO matchRecordDTO) {
        MatchRecord matchRecord = beanMappingService.mapTo(matchRecordDTO, MatchRecord.class);

        Competition competition = competitionService.findByName(matchRecordDTO.getCompetition());
        Team team = teamService.findByName(matchRecordDTO.getTeam());
        Player player = playerService.findById(matchRecordDTO.getPlayer());

        matchRecord.setMatchNumber(matchRecordDTO.getMatchNumber());
        matchRecord.setCompetition(competition);
        matchRecord.setTeam(team);
        matchRecord.setPlayer(player);

        MatchRecord newMatchRecord = matchRecordService.create(matchRecord);
        return newMatchRecord.getId();
    }

    @Override
    public void delete(Long matchRecordId) {
        matchRecordService.delete(matchRecordService.findById(matchRecordId));
    }

    @Override
    public MatchRecordDTO findMatchRecordBbyId(Long id) {
        MatchRecord matchRecord = matchRecordService.findById(id);
        return (matchRecord == null) ? null : beanMappingService.mapTo(matchRecord, MatchRecordDTO.class);
    }

    @Override
    public List<MatchRecordDTO> getAllMatchRecord() {
        return beanMappingService.mapTo(matchRecordService.findAll(), MatchRecordDTO.class);
    }

    @Override
    public List<MatchRecordDTO> findMatchRecordByPlayer(Long playerId) {
        Player player = playerService.findById(playerId);
        List<MatchRecord> matchRecord = matchRecordService.findByPlayer(player);
        return (matchRecord == null) ? null : beanMappingService.mapTo(matchRecord, MatchRecordDTO.class);
    }

    @Override
    public List<MatchRecordDTO> findMatchRecordByCompetition(String competitionId) {
        Competition competition = competitionService.findByName(competitionId);
        List<MatchRecord> matchRecord = matchRecordService.findByCompetition(competition);
        return (matchRecord == null) ? null : beanMappingService.mapTo(matchRecord, MatchRecordDTO.class);
    }

    @Override
    public List<MatchRecordDTO> findMatchRecordByTeam(String teamId) {
        Team team = teamService.findByName(teamId);
        List<MatchRecord> matchRecord = matchRecordService.findByTeam(team);
        return (matchRecord == null) ? null : beanMappingService.mapTo(matchRecord, MatchRecordDTO.class);
    }
}
