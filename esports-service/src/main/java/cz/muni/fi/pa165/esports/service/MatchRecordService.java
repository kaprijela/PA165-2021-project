package cz.muni.fi.pa165.esports.service;

import java.util.List;

import cz.muni.fi.pa165.esports.entity.Competition;
import cz.muni.fi.pa165.esports.entity.MatchRecord;
import cz.muni.fi.pa165.esports.entity.Player;
import cz.muni.fi.pa165.esports.entity.Team;

import org.springframework.stereotype.Service;

/**
 * @author Elena Álvarez
 */
@Service
public interface MatchRecordService {
    public MatchRecord create(MatchRecord matchRecord);
    public void delete(MatchRecord matchRecord);
    public MatchRecord findById(Long id);
    public List<MatchRecord> findAll();
    public List<MatchRecord> findByPlayer(Player player);
    public List<MatchRecord> findByCompetition(Competition competition);
    public List<MatchRecord> findByTeam(Team team);

    public int getScore(MatchRecord matchRecord);
    public Competition getCompetition(MatchRecord matchRecord);
    public Team getTeam(MatchRecord matchRecord);

    public void addCompetition(MatchRecord matchRecord, Competition competition);
    public void addTeam(MatchRecord matchRecord, Team team);
}

