package cz.muni.pa165.sampledata;

import cz.muni.fi.pa165.esports.entity.*;
import cz.muni.fi.pa165.esports.enums.Gender;
import cz.muni.fi.pa165.esports.enums.Role;
import cz.muni.fi.pa165.esports.service.*;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;

/**
 * Loads sample data to populate the esports database.
 *
 * @author Radovan Tomasik
 */
@Component
@Transactional // transactions are handled on facade layer
public class SampleDataLoadingFacadeImpl implements SampleDataLoadingFacade {
    PlayerService playerService;
    TeamService teamService;
    CompetitionService competitionService;
    MatchRecordService matchRecordService;
    UserService userService;

    @Inject
    public SampleDataLoadingFacadeImpl(PlayerService playerService,
                                       TeamService teamService,
                                       CompetitionService competitionService,
                                       MatchRecordService matchRecordService,
                                       UserService userService) {
        this.playerService = playerService;
        this.teamService = teamService;
        this.competitionService = competitionService;
        this.matchRecordService = matchRecordService;
        this.userService = userService;
    }

    @Override
    public void loadData() {
        /* load users */

        // team manager
        SystemUser teamManager = new SystemUser();
        teamManager.setUsername("teamAdmin");
        teamManager.setEmail("team.admin@esports.org");
        teamManager.setRole(Role.TEAM_MANAGER);
        userService.create(teamManager, "admin");

        // competition manager
        SystemUser competitionManager = new SystemUser();
        competitionManager.setUsername("compAdmin");
        competitionManager.setEmail("comp.admin@esports.org");
        competitionManager.setRole(Role.COMPETITION_MANAGER);
        userService.create(competitionManager, "admin");

        // player
        SystemUser playerUser = new SystemUser();
        playerUser.setUsername("player");
        playerUser.setEmail("player123@esports.org");
        playerUser.setRole(Role.PLAYER);
        userService.create(playerUser, "player");

        // no role, just a registered user
        SystemUser lurker = new SystemUser();
        lurker.setUsername("lurker");
        lurker.setEmail("lurker456@esports.org");
        userService.create(lurker, "lurk-lurk");

        // both a player and a manager
        SystemUser playerManager = new SystemUser();
        playerManager.setUsername("playerManager");
        playerManager.setEmail("player.manager@esports.org");
        playerManager.setRole(Role.PLAYER);
        playerManager.setRole(Role.TEAM_MANAGER); // do multiple roles work?
        userService.create(playerManager, "admin");

        /* load teams */

        Team orcs = new Team();
        orcs.setName("Orcs");
        orcs.setAbbreviation("ORCS");
        orcs.setDescription("Hurr durr!");
        teamService.create(orcs);

        Team elves = new Team();
        elves.setName("Elves");
        elves.setAbbreviation("ELVS");
        elves.setDescription("Magnificence itself");
        teamService.create(elves);

        Team reservoirDogs = new Team();
        reservoirDogs.setName("Reservoir Dogs");
        reservoirDogs.setAbbreviation("RDOGS");
        reservoirDogs.setDescription("The best ones for the job");
        teamService.create(reservoirDogs);

        /* load players */

        Player player = new Player();
        player.setName("Radko");
        player.setGender(Gender.MALE);
        orcs.addPlayer(player);
        playerService.create(player);
        MatchRecord m1 = new MatchRecord();
        m1.setMatchNumber(5L);
        m1.setPlayer(player);
        matchRecordService.create(m1);

        Player mrWhite = new Player();
        mrWhite.setName("Larry");
        mrWhite.setGender(Gender.MALE);
        reservoirDogs.addPlayer(mrWhite);
        playerService.create(mrWhite);

        Player mrOrange = new Player();
        mrOrange.setName("Mr. Orange");
        mrOrange.setGender(Gender.MALE);
        reservoirDogs.addPlayer(mrOrange);
        playerService.create(mrOrange);

        Player mrPink = new Player();
        mrPink.setName("Mr. Pink");
        mrPink.setGender(Gender.MALE);
        reservoirDogs.addPlayer(mrPink);
        playerService.create(mrPink);

        Player mrBrown = new Player();
        mrBrown.setName("Mr. Brown");
        mrBrown.setGender(Gender.MALE);
        reservoirDogs.addPlayer(mrBrown);
        playerService.create(mrBrown);

        Player mrBlonde = new Player();
        mrBlonde.setName("Mr. Blonde");
        mrBlonde.setGender(Gender.MALE);
        reservoirDogs.addPlayer(mrBlonde);
        playerService.create(mrBlonde);

        Player mrsRed = new Player();
        mrsRed.setName(mrWhite.getName());
        mrsRed.setGender(Gender.FEMALE);
        playerService.create(mrsRed);

        /* load competitions */

        Competition c1 = new Competition();
        Competition c2 = new Competition();
        Competition c3 = new Competition();

        c1.setId(1L);
        c2.setId(2L);
        c3.setId(3L);

        c1.setName("Japan League 2021");
        c2.setName("Nordic Championship 2021");
        c3.setName("Masters Clash Championship");

        c1.addTeam(orcs);

        c1.setLocation("Japan");
        c2.setLocation("Oslo");
        c3.setLocation("London");

        competitionService.createCompetition(c1);
        competitionService.createCompetition(c2);
        competitionService.createCompetition(c3);
    }
}
