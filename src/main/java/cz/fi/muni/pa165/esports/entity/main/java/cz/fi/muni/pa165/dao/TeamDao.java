package cz.fi.muni.pa165.dao;


import cz.fi.muni.pa165.entity.Team;

import java.util.List;

/**
 * @author Gabriela Kandova
 */

public interface TeamDao {
    void create(Team team);
    void delete(Team team);

    Team findById(Long id);
    List<Team> findAll();
    Team findByName(String name);
    Team findByAbbreviation(String abbreviation);
}