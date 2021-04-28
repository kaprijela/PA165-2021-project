package cz.muni.fi.pa.esports.facade;

import cz.muni.fi.pa.esports.dto.*;

import java.util.List;

/**
 * @author gavlijan
 */
public interface CompetitionFacade {

    /**
     * Add a team
     * @param idCompetition id of Competition
     * @param idTeam id of Team
     */
    void addTeam(Long idCompetition, Long idTeam);

    /**
     * remove a team
     * @param idCompetition id of Competition
     * @param idTeam id of Team
     */
    void removeTeam(Long idCompetition, Long idTeam);

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
