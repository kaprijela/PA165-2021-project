import cz.fi.muni.pa165.esports.entity.Competition;
import cz.fi.muni.pa165.esports.entity.MatchRecord;
import cz.fi.muni.pa165.esports.entity.Player;

import java.util.List;

public interface TeamService {

    boolean applyForCompetion(Competition competition);

    void acceptPlayer(Player player);

    List<Player> getPlayers();

    void getTeamStatistics();
}
