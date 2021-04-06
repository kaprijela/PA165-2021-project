package cz.fi.muni.pa165.esports.dao;


import cz.fi.muni.pa165.esports.entity.MatchRecord;
import cz.fi.muni.pa165.esports.entity.Player;

import java.util.List;

/**
 @Author Elena Álvarez
 */
public interface MatchRecordDao {
    public MatchRecord findById(Long id);
    public void create(MatchRecord c);
    public void delete(MatchRecord c);
    public List<MatchRecord> findAll();
    public MatchRecord findByName(String name);
    public MatchRecord findByMatchNumber(Long match_number);
    //public Match findByPlayer(Player player);
    public List<Player> findPlayers();
}

