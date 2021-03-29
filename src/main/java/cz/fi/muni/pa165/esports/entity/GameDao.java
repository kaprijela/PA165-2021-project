package cz.fi.muni.pa165.dao;


import cz.fi.muni.pa165.entity.Game;

import java.util.List;

public interface GameDao {
    public Game findById(Long id);
    public void create(Game c);
    public void delete(Game c);
    public List<Game> findAll();
    public Game findByName(String name);
}
