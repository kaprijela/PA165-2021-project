package cz.muni.fi.pa165.esports.facade;

import cz.muni.fi.pa165.esports.dto.CompetitionDTO;

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
    void addTeam(String competition, String team);

    /**
     * remove a team
     * @param idCompetition id of Competition
     * @param idTeam id of Team
     */
    void removeTeam(String competition, String team);

    /**
     * returns all of the Teams
     * @return result
     */
    List<CompetitionDTO> getAllCompetitions();

    /**
     * create a competition
     * @param competitionDTO
     * @return result
     */
    void createCompetition(CompetitionDTO competitionDTO);

    /**
     * get competition by name
     * @return competition
     */
    CompetitionDTO getCompetitionByName(String name);

}
