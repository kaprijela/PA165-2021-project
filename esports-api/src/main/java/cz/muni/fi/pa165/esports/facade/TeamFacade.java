package cz.muni.fi.pa165.esports.facade;

import cz.muni.fi.pa165.esports.dto.CompetitionDTO;
import cz.muni.fi.pa165.esports.dto.PlayerDTO;
import cz.muni.fi.pa165.esports.dto.TeamDTO;
import cz.muni.fi.pa165.esports.enums.Game;

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
     * Get a registered team according to its ID.
     *
     * @param id ID of a team
     * @return team with matching ID or null if no such team exists
     */
    TeamDTO getTeamById(Long id);

    /**
     * Get a registered team according to its name.
     *
     * @param name name of a team
     * @return team with matching name or null if no such team exists
     */
    TeamDTO getTeamByName(String name);

    /**
     * Get a registered team according to its abbreviation.
     *
     * @param abbreviation abbreviation of a team
     * @return team with matching abbreviation or null if no such team exists
     */
    TeamDTO getTeamByAbbreviation(String abbreviation);

    /**
     * Register a team in the system.
     * The team's name and abbreviation must be unique, otherwise an exception is thrown.
     *
     * @param team team to be registered
     */
    void registerNewTeam(TeamDTO team);

    /**
     * Removes a registered team from the system.
     *
     * @param team team to be unregistered
     */
    void removeTeam(TeamDTO team);

    /**
     * Adds a new player to the team.
     *
     * @param team team the player is joining
     * @param player player to join the team
     */
    void addPlayerToTeam(TeamDTO team, PlayerDTO player);

    /**
     * Kicks one of the team's players from the team.
     *
     * @param team team that is kicking the player
     * @param player player to kick from the team
     */
    void kickPlayerFromTeam(TeamDTO team, PlayerDTO player);

    Double getAverageTeamScoreForCompetition(TeamDTO team, CompetitionDTO competition);

    Double getAverageTeamScoreForGame(TeamDTO team, Game game);
}
