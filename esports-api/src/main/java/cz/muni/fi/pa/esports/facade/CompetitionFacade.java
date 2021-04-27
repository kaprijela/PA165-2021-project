package cz.muni.fi.pa.esports.facade;

import cz.muni.fi.pa.esports.dto.*;

import java.util.List;

public interface CompetitionFacade {

    boolean addTeam(TeamDTO teamDTO);
    List<TeamDTO> getTeams();
    boolean removeTeam(TeamDTO teamDTO);
//    StatisticsDTO
    void getPlayerStatisticForCompetion(PlayerDTO playerDTO);
    LadderDTO getLadder();
    boolean createCompetition(CompetitionDTO competitionDTO);
    boolean updateCompetition(CompetitionDTO competitionDTO);
    void updatePrizepoole(PrizePoolDTO prizePoolDTO);
    CompetitionDTO getCompetionByName();
}
