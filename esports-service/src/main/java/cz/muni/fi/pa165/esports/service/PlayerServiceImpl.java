package cz.muni.fi.pa165.esports.service;

import cz.muni.fi.pa165.esports.dao.MatchRecordDao;
import cz.muni.fi.pa165.esports.entity.Competition;
import cz.muni.fi.pa165.esports.entity.MatchRecord;
import cz.muni.fi.pa165.esports.entity.Player;
import cz.muni.fi.pa165.esports.dao.PlayerDao;
import cz.muni.fi.pa165.esports.enums.Game;
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

    @Autowired
    private MatchRecordDao matchRecordDao;

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

    public void remove(Player player) {
        playerDao.delete(player);
    }

    @Override
    public Double getPlayerAverage(Player player) {
        List<MatchRecord> matchRecords = matchRecordDao.findByPlayer(player);
        int numberOfMatches = 0;
        int totalScore = 0;
        for (MatchRecord matchRecord:
             matchRecords) {
            numberOfMatches++;
            totalScore += matchRecord.getScore();
        }
        return (double) (totalScore / numberOfMatches);
    }

    @Override
    public Double getPlayerAverageByGame(Player player, Game game) {
        List<MatchRecord> matchRecords = matchRecordDao.findByPlayer(player);
        int numberOfMatches = 0;
        int totalScore = 0;
        for (MatchRecord matchRecord:
                matchRecords) {
            if(matchRecord.getCompetition().getGame() == game) {
                numberOfMatches++;
                totalScore += matchRecord.getScore();
            }
        }
        return (double) (totalScore / numberOfMatches);
    }

    @Override
    public Double getPlayerAverageByCompetition(Player player, Competition competition) {
        List<MatchRecord> matchRecords = matchRecordDao.findByPlayer(player);
        int numberOfMatches = 0;
        int totalScore = 0;
        for (MatchRecord matchRecord:
                matchRecords) {
            if(matchRecord.getCompetition() == competition) {
                numberOfMatches++;
                totalScore += matchRecord.getScore();
            }
        }
        return (double) (totalScore / numberOfMatches);
    }

    public void create(Player player) {
        playerDao.create(player);
    }

}
