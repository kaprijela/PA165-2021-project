package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.entity.Competition;

import java.util.List;

/**
 * @author jan gavlik
 */

public interface CompetitionDao {
    public Competition findById(Long id);
    public void create(Competition c);
    public void delete(Competition c);
    public List<Competition> findAll();
    public Competition findByName(String name);
}