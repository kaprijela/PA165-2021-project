package cz.muni.fi.pa165.esports.service.dozer;

import cz.muni.fi.pa165.esports.dto.PlayerDTO;
import cz.muni.fi.pa165.esports.entity.Player;
import cz.muni.fi.pa165.esports.entity.Team;
import cz.muni.fi.pa165.esports.service.TeamService;
import org.dozer.DozerConverter;
import org.springframework.stereotype.Component;

import javax.inject.Inject;

/**
 * Class specifying the conversion of {@link Team} to {@link String} and vice versa.
 * Conversion logic concerns the attribute {@link Player#getTeam()}, as in {@link PlayerDTO} it is represented only by its abbreviation.
 *
 * @author Gabriela Kandova
 */
@Component("teamStringConverter")
public class TeamStringConverter extends DozerConverter<Team, String> {

    private static TeamService teamService;

    public TeamStringConverter() {
        super(Team.class, String.class);
    }

    @Inject
    public TeamStringConverter(TeamService teamService) {
        super(Team.class, String.class);
        TeamStringConverter.teamService = teamService;
    }

    @Override
    public String convertTo(Team team, String abbreviation) {
        if (team == null) {
            return null;
        }

        return team.getAbbreviation();
    }

    @Override
    public Team convertFrom(String teamAbbreviation, Team team) {
        if (teamAbbreviation == null) {
            return null;
        }

        Team newTeam = teamService.findByAbbreviation(teamAbbreviation);
        if (newTeam == null) {
            throw new IllegalStateException(String.format("No team with abbreviation %s found", teamAbbreviation));
        }
        return newTeam;
    }

}
