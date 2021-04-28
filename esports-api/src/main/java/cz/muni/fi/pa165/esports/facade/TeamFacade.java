package cz.muni.fi.pa165.esports.facade;

import cz.muni.fi.pa165.esports.dto.TeamDTO;

import java.util.List;

/**
 * @author Gabriela Kandova
 */
public interface TeamFacade {
    /**
     * Get all registered teams.
     *
     * @return list of teams
     */
    List<TeamDTO> getAllTeams();

    /**
     * Get a team according to its ID.
     *
     * @param teamId ID of a team
     * @return team with matching ID
     */
    TeamDTO getTeamById(Long teamId);

    /**
     * Get a team according to its name.
     *
     * @param name name of a team
     * @return team with matching name
     */
    TeamDTO getTeamByName(String name);

    /**
     * Get a team according to its abbreviation.
     *
     * @param abbreviation abbreviation of a team
     * @return team with matching abbreviation
     */
    TeamDTO getTeamByAbbreviation(String abbreviation);
}
