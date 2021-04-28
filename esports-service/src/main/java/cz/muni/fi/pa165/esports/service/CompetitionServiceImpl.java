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
    }

    @Override
    public void removeCompetition(Competition competition) {

    }

    @Override
    public Competition getByName(String name) {
        return null;
    }

    @Override
    public Competition getById(Long id) {
        return null;
    }

    @Override
    public List<Competition> getAll() {
        return null;
    }

    @Override
    public Competition findById(Long competitionId) {
        return null;
    }
}
