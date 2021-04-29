package cz.muni.fi.pa165.esports.service;

import cz.muni.fi.pa165.esports.dao.CompetitionDao;
import cz.muni.fi.pa165.esports.entity.Competition;
import cz.muni.fi.pa165.esports.entity.Team;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

/**
 * @author zgavjan
 */
@Slf4j
@Service
public class CompetitionServiceImpl implements CompetitionService {

    @Inject
    CompetitionDao competitionDao;

    @Inject
    TeamService teamService;

    @Override
    public Long createCompetition(Competition competition) {
        log.info("Saving a Competition: {}", competition.getName());
        return competitionDao.save(competition).getId();

    }

    @Override
    public void removeCompetition(Competition competition) {
        log.info("Removing a Competition: {}", competition.getName());
        competitionDao.delete(competition);
    }

    @Override
    public void addTeam(Long idCompetition, Long idTeam) {
        log.info("Adding a team with id: {}, to competition with id: {}", idTeam, idCompetition);
        Optional<Competition> competition = competitionDao.findById(idCompetition);
        Team team = teamService.findById(idTeam);
        competition.get().addTeam(team);
    }

    @Override
    public void removeTeam(Long idCompetition, Long idTeam) {
        log.info("Removing a team with id: {}, from competition with id: {}", idTeam, idCompetition);
        Optional<Competition> competition = competitionDao.findById(idCompetition);
        Team team = teamService.findById(idTeam);
        competition.get().removeTeam(team);
    }

    @Override
    public Competition getByName(String name) {
        log.info("Getting a competition with name: {}", name);
        return competitionDao.findByName(name);
    }

    @Override
    public List<Competition> getAll() {
        log.info("Returning all competitions");
        return competitionDao.findAll();
    }

    @Override
    public Optional<Competition> findById(Long competitionId) {
        log.info("Looking for competition with id: {}", competitionId);
        return competitionDao.findById(competitionId);
    }
}
