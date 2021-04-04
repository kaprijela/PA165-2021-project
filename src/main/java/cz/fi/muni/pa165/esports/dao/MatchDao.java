package cz.fi.muni.pa165.esports.dao;


import cz.fi.muni.pa165.esports.entity.Match;

import java.util.List;

/*
    @Author Elena Álvarez
*/
public interface MatchDao {
    public Match findById(Long id);
    public void create(Match c);
    public void delete(Match c);
    public List<Match> findAll();
    public Match findByName(String name);
    public Match findByMatchNumber(Long match_number);
    //public Match findByPlayer(Player player);
    public List<Match> findPlayers();
}
