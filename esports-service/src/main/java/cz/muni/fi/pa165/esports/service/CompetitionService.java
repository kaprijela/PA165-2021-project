package cz.muni.fi.pa165.esports.service;

import cz.muni.fi.pa165.esports.entity.Competition;
import jdk.jfr.Category;

import java.util.List;

public interface CompetitionService {

    void createCompetition(Competition competition);
    void removeCompetition(Competition competition);
    Competition getByName(String name);
    Competition getById(Long id);
    List<Competition> getAll();
    Competition findById(Long competitionId);
}
