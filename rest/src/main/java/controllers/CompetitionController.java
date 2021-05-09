package controllers;

import cz.muni.fi.pa165.esports.dao.CompetitionDao;
import cz.muni.fi.pa165.esports.dto.CompetitionDTO;
import cz.muni.fi.pa165.esports.dto.PlayerDTO;
import cz.muni.fi.pa165.esports.entity.Competition;
import cz.muni.fi.pa165.esports.entity.Player;
import cz.muni.fi.pa165.esports.facade.CompetitionFacade;
import cz.muni.fi.pa165.esports.facade.PlayerFacade;
import exceptions.ResourceAlreadyExistingException;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.Collection;
import java.util.List;


@RestController
@RequestMapping(ControllerConstants.COMPETITIONS)
@Slf4j
public class CompetitionController {

    @Inject
    CompetitionFacade competitionFacade;

    @RequestMapping(value= "", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public final Collection<CompetitionDTO> getPlayers(){
        log.debug("rest getPlayers()");
        return competitionFacade.getAllCompetitions();
    }

    @RequestMapping(value= "/create", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public final CompetitionDTO createCompetition(@RequestBody CompetitionDTO competitionDTO) throws Exception{
        log.debug("rest createCompetition()");
        try {
            Long competition = competitionFacade.createCompetition(competitionDTO);
            return competitionFacade.getCompetitionById(competition);
        } catch (Exception ex){
            throw new ResourceAlreadyExistingException();
        }
    }

}
