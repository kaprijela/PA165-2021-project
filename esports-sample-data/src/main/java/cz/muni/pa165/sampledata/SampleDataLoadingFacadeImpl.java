package cz.muni.pa165.sampledata;


import cz.muni.fi.pa165.esports.entity.*;
import cz.muni.fi.pa165.esports.enums.Gender;
import cz.muni.fi.pa165.esports.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;


/**
 * Loads some sample data to populate the eshop database.
 *
 * @author Radovan Tomasik
 */
@Component
@Transactional //transactions are handled on facade layer
public class SampleDataLoadingFacadeImpl implements SampleDataLoadingFacade {
    @Autowired
    PlayerService playerService;

    @Autowired
    TeamService teamService;

    @Autowired
    CompetitionService competitionService;

    @Autowired
    UserService userService;

    @Autowired
    MatchRecordService matchRecordService;

    @Override
    public void loadData() throws IOException {
        Team t1 = new Team();
        t1.setName("Orcs");
        t1.setAbbreviation("O");
        teamService.create(t1);


        SystemUser admin = new SystemUser();
        admin.setUsername("BOSS");
        admin.setEmail("KOKOT");
        admin.setAdmin(true);
        userService.create(admin, "1234");

        Player player = new Player();
        player.setName("Radko");
        player.setGender(Gender.MALE);
        player.setTeam(t1);
        playerService.create(player);
        MatchRecord m1 = new MatchRecord();
        m1.setMatchNumber(5L);
        m1.setPlayer(player);
        matchRecordService.create(m1);



        Player mrWhite = new Player();
        mrWhite.setName("Larry");
        mrWhite.setGender(Gender.MALE);
        playerService.create(mrWhite);

        Player mrOrange = new Player();
        mrOrange.setName("Freddy");
        mrOrange.setGender(Gender.MALE);
        playerService.create(mrOrange);

        Player mrPink = new Player();
        mrPink.setName("null");
        mrPink.setGender(Gender.MALE);
        playerService.create(mrPink);

        Player mrBrown = new Player();
        mrBrown.setName("Mr. Brown");
        mrBrown.setGender(Gender.MALE);
        playerService.create(mrBrown);

        Player mrBlonde = new Player();
        mrBlonde.setName("Mr. Blonde");
        mrBlonde.setGender(Gender.MALE);
        playerService.create(mrBlonde);

        Player mrsRed = new Player();
        mrsRed.setName(mrWhite.getName());
        mrsRed.setGender(Gender.FEMALE);
        playerService.create(mrsRed);



        Team t2 = new Team();
        t2.setName("Elves");
        t2.setAbbreviation("E");
        teamService.create(t2);

        Competition c1 = new Competition();
        Competition c2 = new Competition();
        Competition c3 = new Competition();

        c1.setId(1L);
        c2.setId(2L);
        c3.setId(3L);

        c1.setName("Japan League 2021");
        c2.setName("Nordic Championship 2021");
        c3.setName("Masters Clash Championship");

        c1.addTeam(t1);


        c1.setLocation("Japan");
        c2.setLocation("Oslo");
        c3.setLocation("London");

        competitionService.createCompetition(c1);
        competitionService.createCompetition(c2);
        competitionService.createCompetition(c3);

    }
}
