package cz.muni.fi.pa165.esports.service.facade;

import cz.muni.fi.pa165.esports.dto.CompetitionDTO;
import cz.muni.fi.pa165.esports.dto.PlayerDTO;
import cz.muni.fi.pa165.esports.dto.TeamDTO;
import cz.muni.fi.pa165.esports.entity.Competition;
import cz.muni.fi.pa165.esports.entity.Player;
import cz.muni.fi.pa165.esports.entity.Team;
import cz.muni.fi.pa165.esports.enums.Game;
import cz.muni.fi.pa165.esports.facade.TeamFacade;
import cz.muni.fi.pa165.esports.service.BeanMappingService;
import cz.muni.fi.pa165.esports.service.TeamService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

/**
 * Implementation of TeamFacade.
 *
 * @author Gabriela Kandova
 */
@Slf4j
@Service
@Transactional
public class TeamFacadeImpl implements TeamFacade {

    TeamService teamService;
    BeanMappingService bms;

    @Inject
    public TeamFacadeImpl(TeamService teamService, BeanMappingService beanMappingService) {
        this.teamService = teamService;
        this.bms = beanMappingService;
    }

    @Override
    public List<TeamDTO> getAllTeams() {
        return bms.mapTo(teamService.findAll(), TeamDTO.class);
    }

    @Override
    public TeamDTO findTeamById(Long id) {
        return bms.mapTo(teamService.findById(id), TeamDTO.class);
    }

    @Override
    public TeamDTO findTeamByName(String name) {
        return bms.mapTo(teamService.findByName(name), TeamDTO.class);
    }

    @Override
    public TeamDTO findTeamByAbbreviation(String abbreviation) {
        return bms.mapTo(teamService.findByAbbreviation(abbreviation), TeamDTO.class);
    }

    @Override
    public Long registerNewTeam(TeamDTO team) {
        Team team1 = new Team();
        team1.setName(team.getName());
        team1.setAbbreviation(team.getAbbreviation());
        teamService.create(team1);
        return teamService.findByAbbreviation(team.getAbbreviation()).getId();
    }

    @Override
    public void removeTeam(TeamDTO team) {
        teamService.remove(bms.mapTo(team, Team.class));
    }

    @Override
    public void addPlayerToTeam(TeamDTO team, PlayerDTO player) {
        teamService.addPlayer(bms.mapTo(team, Team.class), bms.mapTo(player, Player.class));
    }

    @Override
    public void removePlayerFromTeam(TeamDTO team, PlayerDTO player) {
        teamService.removePlayer(bms.mapTo(team, Team.class), bms.mapTo(player, Player.class));
    }

    @Override
    public Double getAverageTeamScoreForCompetition(TeamDTO team, CompetitionDTO competition) {
        return teamService.getAverageTeamScoreForCompetition(bms.mapTo(team, Team.class), bms.mapTo(competition, Competition.class));
    }

    @Override
    public Double getAverageTeamScoreForGame(TeamDTO team, Game game) {
        return teamService.getAverageTeamScoreForGame(bms.mapTo(team, Team.class), game);
    }
}
