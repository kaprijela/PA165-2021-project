package cz.muni.fi.pa165.esports.service;

import cz.muni.fi.pa165.esports.entity.Competition;

import java.util.List;
import java.util.Optional;

public interface CompetitionService {

    /**
     * Add Competition
     * @param competition competition
     */
    Long createCompetition(Competition competition);
    /**
     * remove Competition
     * @param competition competition
     */
    void removeCompetition(Competition competition);

    /**
     * add a team to competition
     * @param competition competition
     * @param team team
     */
    void addTeam(String competition, String team);

    /**
     * remove a team to competition
     * @param competition competition
     * @param team team
     */
    void removeTeam(String competition, String team);

    /**
     * get a Competition by Name
     * @param name name
     * @return Competition
     */
    Competition findByName(String name);


    /**
     * return all the competition
     * @return list of competitions
     */
    List<Competition> getAll();

    /**
     * return competition
     * @param competitionId id
     * @return competition
     */
    Optional<Competition> findById(Long competitionId);
}
