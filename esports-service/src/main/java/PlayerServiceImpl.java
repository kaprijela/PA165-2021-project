import cz.fi.muni.pa165.esports.entity.Player;
import cz.fi.muni.pa165.esports.entity.Team;

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
}
