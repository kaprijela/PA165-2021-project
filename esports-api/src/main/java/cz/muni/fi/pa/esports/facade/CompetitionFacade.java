package cz.muni.fi.pa.esports.facade;

import cz.muni.fi.pa.esports.dto.*;

import java.util.List;

/**
 * @author gavlijan
 */
public interface CompetitionFacade {

    /**
     * Add a Team
     * @param teamDTO teamObject
     * @return result of the operation
     */
    boolean addTeam(TeamDTO teamDTO);

    /**
     * returns all of the Teams
     * @return result
     */
    List<CompetitionDTO> getAllCompetitions();

    /**
     * Remove a Team
     * @param teamDTO team to be deleted
     * @return result
     */
    void removeTeam(TeamDTO teamDTO);
//    StatisticsDTO

    /**
     * get All the Statistics
     * @param playerDTO player
     */
    void getPlayerStatisticForCompetion(PlayerDTO playerDTO);

    /**
     * Get a top 5 players
     * @return object with top 5 Players
     */
    LadderDTO getLadder();

    /**
     * create a competition
     * @param competitionDTO
     * @return result
     */
    void createCompetition(CompetitionDTO competitionDTO);

    /**
     * update a competition
     * @param competitionDTO competition
     * @return result
     */
    void updateCompetition(CompetitionDTO competitionDTO);

    /**
     * get competition by name
     * @return competition
     */
    CompetitionDTO getCompetitionByName(String name);

}
