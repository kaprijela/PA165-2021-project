package cz.fi.muni.pa165.dao;


import cz.fi.muni.pa165.entity.Player;
import cz.fi.muni.pa165.enums.Gender;

import java.util.List;

/**
 * @author Radovan Tomasik
 */
public interface PlayerDao {
    public Player findById(Long id);
    public void create(Player player);
    public void delete(Player player);
    public List<Player> findAll();
    public List<Player> findByName(String name);
    public List<Player> findByGender(Gender gender);
}