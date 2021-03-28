package cz.fi.muni.pa165.esports.dao;

import cz.fi.muni.pa165.esports.entity.Competition;

import java.util.List;

/**
 * @author jan gavlik
 */
:
public interface CompetitionDao {
    public Competition findById(Long id);
    public void create(Competition c);
    public void delete(Competition c);
    public List<Competition> findAll();
    public Competition findByName(String name);
}
