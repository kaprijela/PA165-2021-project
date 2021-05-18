package cz.muni.fi.pa165.esports.facade;

import cz.muni.fi.pa165.esports.dto.CompetitionDTO;

import java.util.List;

/**
 * @author gavlijan
 */
public interface CompetitionFacade {

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
    Long createCompetition(CompetitionDTO competitionDTO);

    /**
     * get competition by name
     * @return competition
     */
    CompetitionDTO findCompetitionByName(String name);

    /**
     * get competition by specified id
     * @param id of competition you search for
     * @return CompetitionDTO
     */
    CompetitionDTO findCompetitionById(Long id);

    /**
     * will delete a competition
     * @param id of deleted competition
     */
    void deleteCompetition(Long id);

    /**
     * Add a team
     * @param competition id of Competition
     * @param team id of Team
     */
    void addTeam(Long competition, String team);

    /**
     * remove a team
     * @param competition id of Competition
     * @param team id of Team
     */
    void removeTeam(Long competition, String team);
}
