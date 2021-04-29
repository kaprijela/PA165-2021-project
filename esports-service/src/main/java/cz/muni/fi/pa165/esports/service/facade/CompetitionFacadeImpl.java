package cz.muni.fi.pa165.esports.service.facade;

import cz.muni.fi.pa165.esports.facade.CompetitionFacade;
import cz.muni.fi.pa165.esports.dto.CompetitionDTO;
import cz.muni.fi.pa165.esports.entity.Competition;
import cz.muni.fi.pa165.esports.enums.Game;
import cz.muni.fi.pa165.esports.service.BeenMappingService;
import cz.muni.fi.pa165.esports.service.CompetitionService;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import java.util.List;

@Transactional
public class CompetitionFacadeImpl implements CompetitionFacade {

    @Inject
    CompetitionService competitionService;

    @Inject
    BeenMappingService beanMappingService;

    public CompetitionFacadeImpl(CompetitionService competitionService, BeenMappingService beanMappingService) {
        this.competitionService = competitionService;
        this.beanMappingService = beanMappingService;
    }

    @Override
    public void addTeam(String competition, String team) {
        competitionService.addTeam(competition, team);
    }

    @Override
    public void removeTeam(String competition, String team) {
        competitionService.removeTeam(competition, team);
    }

    @Override
    public List<CompetitionDTO> getAllCompetitions() {
        return beanMappingService.mapTo(competitionService.getAll(), CompetitionDTO.class);
    }

    @Override
    public void createCompetition(CompetitionDTO competitionDTO) {
        Competition competition = beanMappingService.mapTo(competitionDTO, Competition.class);
        competitionService.createCompetition(competition);
    }

    @Override
    public CompetitionDTO getCompetitionByName(String name) {
        return beanMappingService.mapTo(competitionService.findByName(name), CompetitionDTO.class);
    }
}
