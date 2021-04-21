package cz.muni.fi.pa165.esports.service;

import cz.muni.fi.pa165.esports.dao.MatchRecordDao;
import cz.muni.fi.pa165.esports.entity.Competition;
import cz.muni.fi.pa165.esports.entity.MatchRecord;
import cz.muni.fi.pa165.esports.entity.Player;
import cz.muni.fi.pa165.esports.entity.Team;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author Elena Álvarez
 *
 *  Implementation of the {@link MatchRecordService}. This class is part of the
 *  service module of the application that provides the implementation of the
 *  business logic (main logic of the application).
 *
 */
@Service
@Transactional
public class MatchRecordServiceImpl implements MatchRecordService {
    @Autowired
    private MatchRecordDao matchRecordDao;

    //Persistence
    @Override
    public void create(MatchRecord matchRecord) {
        matchRecordDao.create(matchRecord);
    }

    @Override
    public void delete(MatchRecord matchRecord) throws IllegalArgumentException {
        matchRecordDao.delete(matchRecord);
    }

    @Override
    public MatchRecord findById(Long id) {
        return matchRecordDao.findById(id);
    }

    @Override
    public List<MatchRecord> findAll() {
        return matchRecordDao.findAll();
    }

    @Override
    public List<MatchRecord> findByPlayer(Player player) {
        return matchRecordDao.findByPlayer(player);
    }

    @Override
    public List<MatchRecord> findByCompetition(Competition competition) {
        return matchRecordDao.findByCompetition(competition);
    }

    //Getters
    @Override
    public int getScore(MatchRecord matchRecord) {
        return matchRecord.getScore();
    }

    @Override
    public Competition getCompetition(MatchRecord matchRecord) {
        return matchRecord.getCompetition();
    }

    @Override
    public Team getTeam(MatchRecord matchRecord) {
        return null;
    }

    //Setters
    @Override
    public void addCompetition(MatchRecord matchRecord, Competition competition) {
        if (matchRecord.getCompetition() == null && competition != null){
            matchRecord.setCompetition(competition);
        }
    }

    @Override
    public void addTeam(MatchRecord matchRecord, Team team) {
        if (matchRecord.getTeam() == null && team != null){
            matchRecord.setTeam(team);
        }
    }
}
