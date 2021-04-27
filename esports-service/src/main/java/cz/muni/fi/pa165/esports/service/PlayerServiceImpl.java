package cz.muni.fi.pa165.esports.service;

import cz.muni.fi.pa165.esports.entity.Player;
import cz.muni.fi.pa165.esports.entity.Team;

import java.util.List;

public class PlayerServiceImpl implements PlayerService {
    @Override
    public void applyForTeam(Team team) {

    }

    @Override
    public boolean isAdmin(Player player) {
        return false;
    }

    @Override
    public boolean authenticate(Player player, String password) {
        return false;
    }

    @Override
    public List<Player> getAllPlayers() {
        return null;
    }

    @Override
    public void registerPlayer(Player player, String unencryptedPassword) {

    }

    @Override
    public void getStatistics() {

    }

    @Override
    public Player findById(Long playerId) {
        return null;
    }
}
