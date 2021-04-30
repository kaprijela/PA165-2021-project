package cz.muni.fi.pa165.esports.service.facade;

import cz.muni.fi.pa165.esports.dto.CompetitionDTO;
import cz.muni.fi.pa165.esports.dto.TeamDTO;
import cz.muni.fi.pa165.esports.facade.TeamFacade;
import cz.muni.fi.pa165.esports.service.BeenMappingService;
import cz.muni.fi.pa165.esports.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TeamFacadeImpl implements TeamFacade {
    @Autowired
    TeamService teamService;

    @Autowired
    BeenMappingService beanMappingService;

    @Override
    public List<TeamDTO> getAllTeams() {
        return beanMappingService.mapTo(teamService.findAll(), TeamDTO.class);
    }

    @Override
    public TeamDTO getTeamById(Long teamId) {
        return null;
    }

    @Override
    public TeamDTO getTeamByName(String name) {
        return null;
    }

    @Override
    public TeamDTO getTeamByAbbreviation(String abbreviation) {
        return null;
    }

    @Override
    public void createTeam(TeamDTO teamDTO) {

    }

    @Override
    public void removeTeam(TeamDTO teamDTO) {

    }
}
