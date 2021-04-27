package cz.muni.fi.pa165.esports.service;

import cz.muni.fi.pa165.esports.entity.Competition;

public interface CompetitionService {



    Competition findById(Long competitionId);
}
