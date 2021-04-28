package cz.muni.fi.pa165.esports.service;

import cz.muni.fi.pa165.esports.entity.Player;
import cz.muni.fi.pa165.esports.dao.PlayerDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * An implementation of PlayerService
 *
 * @author Radovan Tomasik
 *
 */
@Service
public class PlayerServiceImpl implements PlayerService {
    @Autowired
    private PlayerDao playerDao;

    @Override
    public List<Player> getAllPlayers() {
        return playerDao.findAll();
    }

    @Override
    public Player findById(Long playerId) {
        return playerDao.findById(playerId);
    }

    @Override
    public List<Player> findByName(String name) {
        return playerDao.findByName(name);
    }

    @Override
    public void create(Player player) {
        playerDao.create(player);
    }
}
