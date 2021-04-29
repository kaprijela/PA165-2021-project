package cz.muni.fi.pa165.esports.service;

import cz.muni.fi.pa165.esports.dao.CompetitionDao;
import cz.muni.fi.pa165.esports.entity.Competition;
import cz.muni.fi.pa165.esports.entity.Team;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    CompetitionDao competitionDao;

    @Autowired
    TeamService teamService;

    public CompetitionServiceImpl(CompetitionDao competitionDao, TeamService teamService) {
        this.competitionDao = competitionDao;
        this.teamService = teamService;
    }

    @Override
    public Long createCompetition(Competition competition) {
        log.info("Saving a Competition: {}", competition.getName());
        if (competition == null) {
            log.warn("Competition should not be null");
            throw new IllegalArgumentException("Competition cannot be null");
        }
        return competitionDao.save(competition).getId();
    }

    @Override
    public void removeCompetition(Competition competition) {
        log.info("Removing a Competition: {}", competition.getName());
        competitionDao.delete(competition);
    }

    @Override
    public void addTeam(String competition, String team) {
        log.info("Adding a team with id: {}, to competition with id: {}", team, competition);
        Competition foundCompetition = findByName(competition);
        Team foundTeam = teamService.findByName(team);
        foundCompetition.addTeam(foundTeam);
    }

    @Override
    public void removeTeam(String competition, String team) {
        log.info("Removing a team with id: {}, from competition with id: {}", team, competition);
        Competition foundCompetition = findByName(competition);
        Team foundTeam = teamService.findByName(team);
        foundCompetition.removeTeam(foundTeam);
    }

    @Override
    public Competition findByName(String name) {
        log.info("Getting a competition with name: {}", name);
        Competition byName = competitionDao.findByName(name);
        if (byName == null){
            log.warn("The Competition was not found");
            // should we throw exception?
        }
        return byName;
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
