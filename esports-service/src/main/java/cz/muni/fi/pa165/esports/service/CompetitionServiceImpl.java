package cz.muni.fi.pa165.esports.service;

import cz.muni.fi.pa165.esports.dao.CompetitionDao;
import cz.muni.fi.pa165.esports.entity.Competition;

import javax.inject.Inject;
import java.util.List;

/**
 * @author zgavjan
 */
public class CompetitionServiceImpl implements CompetitionService {

    @Inject
    CompetitionDao competitionDao;

    @Override
    public void createCompetition(Competition competition) {
        competitionDao.create(competition);
    }

    @Override
    public void removeCompetition(Competition competition) {
        competitionDao.delete(competition);
    }

    @Override
    public Competition getByName(String name) {
        return competitionDao.findByName(name);
    }

    @Override
    public Competition getById(Long id) {
        return competitionDao.findById(id);
    }

    @Override
    public List<Competition> getAll() {
        return competitionDao.findAll();
    }

    @Override
    public Competition findById(Long competitionId) {
        return competitionDao.findById(competitionId);
    }
}
