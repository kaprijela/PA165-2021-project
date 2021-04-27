package cz.muni.fi.pa165.esports.service.facade;

import cz.muni.fi.pa.esports.dto.*;
import cz.muni.fi.pa.esports.facade.CompetitionFacade;

import java.util.List;

public class CompetitionFacadeImp implements CompetitionFacade {
    @Override
    public boolean addTeam(TeamDTO teamDTO) {
        return false;
    }

    @Override
    public List<TeamDTO> getTeams() {
        return null;
    }

    @Override
    public boolean removeTeam(TeamDTO teamDTO) {
        return false;
    }

    @Override
    public void getPlayerStatisticForCompetion(PlayerDTO playerDTO) {

    }

    @Override
    public LadderDTO getLadder() {
        return null;
    }

    @Override
    public boolean createCompetition(CompetitionDTO competitionDTO) {
        return false;
    }

    @Override
    public boolean updateCompetition(CompetitionDTO competitionDTO) {
        return false;
    }

    @Override
    public void updatePrizepoole(PrizePoolDTO prizePoolDTO) {

    }

    @Override
    public CompetitionDTO getCompetionByName() {
        return null;
    }
}
