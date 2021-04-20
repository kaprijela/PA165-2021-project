import cz.fi.muni.pa165.esports.entity.Player;
import cz.fi.muni.pa165.esports.entity.Team;

import java.util.List;

public interface PlayerService {

    void applyForTeam(Team team);

    boolean isAdmin(Player player);

    boolean authenticate(Player player, String password);

    List<Player> getAllPlayers();

    void registerPlayer(Player player, String unencryptedPassword);

    void getStatistics();
}
