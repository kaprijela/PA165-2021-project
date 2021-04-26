package cz.muni.fi.pa165.esports.service;

import cz.muni.fi.pa165.esports.entity.Competition;
import cz.muni.fi.pa165.esports.entity.Player;
import cz.muni.fi.pa165.esports.entity.Team;

import java.util.List;

public class TeamServiceImpl implements TeamService {

    @Override
    public boolean applyForCompetition(Competition competition) {
        return false;
    }

    @Override
    public void acceptPlayer(Player player) {

    }

    @Override
    public List<Player> getPlayers() {
        return null;
    }

    @Override
    public void getTeamStatistics() {

    }

    @Override
    public Team findById(Long teamId) {
        return null;
    }
}
