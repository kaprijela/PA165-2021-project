package cz.muni.fi.pa165.esports.service;

import cz.muni.fi.pa165.esports.dao.CompetitionDao;
import cz.muni.fi.pa165.esports.entity.Competition;
import cz.muni.fi.pa165.esports.entity.Team;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

/**
 * @author zgavjan
 */
@Service
public class CompetitionServiceImpl implements CompetitionService {

    @Inject
    CompetitionDao competitionDao;

    @Inject
    TeamService teamService;

    @Override
    public Long createCompetition(Competition competition) {
        return competitionDao.save(competition).getId();

    }

    @Override
    public void removeCompetition(Competition competition) {
        competitionDao.delete(competition);
    }

    @Override
    public void addTeam(Long idCompetition, Long idTeam) {
        Optional<Competition> competition = competitionDao.findById(idCompetition);
        Team team = teamService.findById(idTeam);
        competition.get().addTeam(team);
    }

    @Override
    public void removeTeam(Long idCompetition, Long idTeam) {
        Optional<Competition> competition = competitionDao.findById(idCompetition);
        Team team = teamService.findById(idTeam);
        competition.get().removeTeam(team);
    }

    @Override
    public Competition getByName(String name) {
        return competitionDao.findByName(name);
    }

    @Override
    public List<Competition> getAll() {
        return competitionDao.findAll();
    }

    @Override
    public Optional<Competition> findById(Long competitionId) {
        return competitionDao.findById(competitionId);
    }
}
