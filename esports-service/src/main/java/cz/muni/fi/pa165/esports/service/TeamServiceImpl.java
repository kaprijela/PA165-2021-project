package cz.muni.fi.pa165.esports.service;

import cz.muni.fi.pa165.esports.dao.TeamDao;
import cz.muni.fi.pa165.esports.entity.Player;
import cz.muni.fi.pa165.esports.entity.Team;

import cz.muni.fi.pa165.esports.exceptions.EsportsServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;

/**
 * An implementation of the {@link TeamService}.
 *
 * @author Gabriela Kandova
 */
@Service
public class TeamServiceImpl implements TeamService {
    @Autowired
    TeamDao teamDao;

    @Override
    public List<Team> findAll() {
        return teamDao.findAll();
    }

    @Override
    public Team findById(Long id) {
        return teamDao.findById(id);
    }

    @Override
    public Team findByName(String name) {
        return teamDao.findByName(name);
    }

    @Override
    public Team findByAbbreviation(String abbreviation) {
        return teamDao.findByAbbreviation(abbreviation);
    }

    @Override
    public void getTeamStatistics() {
    }

    @Override
    public void create(Team team) {
        teamDao.create(team);
    }

    @Override
    public void remove(Team team) {
        teamDao.delete(team);
    }

    @Override
    public void addPlayer(Team team, Player player) {
        if (team.getPlayers().contains(player)) {
            throw new EsportsServiceException(String.format(
                    "Player %s is already member of team %s", player.getName(), team.getName()
            ));
        }
        if (player.getTeam() != null) {
            throw new EsportsServiceException(String.format(
                    "Player %s is already member of team %s", player.getName(), player.getTeam().getName()
            ));
        }

        team.addPlayer(player); // is this all that's necessary?
    }

    @Override
    public void removePlayer(Team team, Player player) {
        if (!team.getPlayers().contains(player)) {
            throw new EsportsServiceException(String.format(
                    "Player %s is not member of team %s", player.getName(), team.getName()
            ));
        }

        team.removePlayer(player); // is this all that's necessary?
    }
}
