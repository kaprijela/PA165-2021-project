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
     * @param idCompetition competition
     * @param idTeam team
     */
    void addTeam(Long idCompetition, Long idTeam);

    /**
     * remove a team to competition
     * @param idCompetition competition
     * @param idTeam team
     */
    void removeTeam(Long idCompetition, Long idTeam);

    /**
     * get a Competition by Name
     * @param name name
     * @return Competition
     */
    Competition getByName(String name);


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
