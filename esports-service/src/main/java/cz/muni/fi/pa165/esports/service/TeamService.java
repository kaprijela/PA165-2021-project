package cz.muni.fi.pa165.esports.service;

import cz.muni.fi.pa165.esports.entity.Player;
import cz.muni.fi.pa165.esports.entity.Team;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * An interface that defines service access to the {@link Team} entity.
 *
 * @author Gabriela Kandova
 */
@Service
public interface TeamService {
    /**
     * Fetch all teams registered in the system.
     *
     * @return a list of all teams
     */
    List<Team> findAll();

    /**
     * Fetch a registered team according to its ID.
     *
     * @param id unique ID of a team
     * @return team with the given ID, NULL if not found
     */
    Team findById(Long id);

    /**
     * Fetch a registered team according to its name.
     *
     * @param name unique name of a team
     * @return team with the given name, NULL if not found
     */
    Team findByName(String name);

    /**
     * Fetch a registered team according to its abbreviation.
     *
     * @param abbreviation unique abbreviation of a team
     * @return team with the given abbreviation, NULL if not found
     */
    Team findByAbbreviation(String abbreviation);

    /**
     * Register a team in the system.
     *
     * @param team team to be registered
     */
    void create(Team team);

    /**
     * Remove a team from the system.
     *
     * @param team team to be removed
     */
    void remove(Team team);

    /**
     * Add a player to a team if the player is free.
     *
     * @param team   team to accept the player
     * @param player player to join the team
     */
    void addPlayer(Team team, Player player);

    /**
     * Remove a player from a team.
     *
     * @param team   team from which to remove the player
     * @param player player to remove from the team
     */
    void removePlayer(Team team, Player player);
}
