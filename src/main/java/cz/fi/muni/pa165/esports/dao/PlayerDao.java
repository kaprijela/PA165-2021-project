package cz.fi.muni.pa165.esports.dao;

import cz.fi.muni.pa165.esports.entity.Player;
import cz.fi.muni.pa165.esports.enums.Gender;

import java.util.List;

/**
 * @author Radovan Tomasik
 */
public interface PlayerDao {
    public Player findById(Long id);
    public void create(Player player);
    public List<Player> findAll();
    public List<Player> findByName(String name);
    public List<Player> findByGender(Gender gender);
}
