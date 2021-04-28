package cz.muni.fi.pa165.esports.service.facade;

import cz.muni.fi.pa.esports.dto.*;
import cz.muni.fi.pa.esports.facade.CompetitionFacade;
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

    @Override
    public void addTeam(Long idCompetition, Long idTeam) {
        competitionService.addTeam(idCompetition, idTeam);
    }

    @Override
    public void removeTeam(Long idCompetition, Long idTeam) {
        competitionService.removeTeam(idCompetition, idTeam);
    }

    @Override
    public List<CompetitionDTO> getAllCompetitions() {
        return beanMappingService.mapTo(competitionService.getAll(), CompetitionDTO.class);
    }

    @Override
    public void createCompetition(CompetitionDTO competitionDTO) {
        Competition competition = new Competition();
        competition.setName(competitionDTO.getName());
        competition.setDate(competitionDTO.getDate());
        competition.setPrizepool(competitionDTO.getPrizepool());
        competition.setLocation(competitionDTO.getLocation());
        competition.setGame(Game.valueOf(competitionDTO.getGame()));
        competitionService.createCompetition(competition);
    }

    @Override
    public CompetitionDTO getCompetitionByName(String name) {
        return beanMappingService.mapTo(competitionService.getByName(name), CompetitionDTO.class);
    }
}
