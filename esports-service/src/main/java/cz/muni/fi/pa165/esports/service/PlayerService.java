package cz.muni.fi.pa165.esports.service;

import cz.muni.fi.pa165.esports.entity.Player;
import cz.muni.fi.pa165.esports.entity.Team;

import java.util.List;

public interface PlayerService {

    void applyForTeam(Team team);

    boolean isAdmin(Player player);

    boolean authenticate(Player player, String password);

    List<Player> getAllPlayers();

    void registerPlayer(Player player, String unencryptedPassword);

    void getStatistics();
}
