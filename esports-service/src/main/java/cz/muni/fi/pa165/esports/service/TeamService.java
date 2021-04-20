package cz.muni.fi.pa165.esports.service;

import cz.muni.fi.pa165.esports.entity.Competition;
import cz.muni.fi.pa165.esports.entity.MatchRecord;
import cz.muni.fi.pa165.esports.entity.Player;

import java.util.List;

public interface TeamService {

    boolean applyForCompetition(Competition competition);

    void acceptPlayer(Player player);

    List<Player> getPlayers();

    void getTeamStatistics();
}
