package cz.muni.fi.pa165.esports.service;

import cz.muni.fi.pa165.esports.entity.Competition;

import java.util.List;

public interface CompetitionService {

    /**
     * Add Competition
     * @param competition competition
     */
    void createCompetition(Competition competition);
    /**
     * remove Competition
     * @param competition competition
     */
    void removeCompetition(Competition competition);

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
    Competition findById(Long competitionId);
}
