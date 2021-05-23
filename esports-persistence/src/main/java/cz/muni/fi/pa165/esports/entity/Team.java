package cz.muni.fi.pa165.esports.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Team is an entity that represents an eSports team.
 * A team consists of players and participates in competitions.
 *
 * Two team instances are equal if they have the same name and abbreviation.
 *
 * @author Gabriela Kandova
 */


@Getter
@Setter
@NoArgsConstructor
@Entity(name = "Team")
@Table(name = "team")
public class Team {
    public Team(Long id) {
        this.id = id;
    }

    /* attributes */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * A team must have a unique name.
     */
    @NotNull
    @NotBlank
    @Column(unique = true, nullable = false)
    private String name;

    /**
     * A team must also have a unique abbreviation.
     */
    @NotNull
    @NotBlank
    @Column(unique = true, nullable = false)
    private String abbreviation;

    /**
     * Team description is optional.
     */
    private String description;

    /**
     * A team consists of zero or more players.
     * At any time, a player can belong to at most one team.
     */
    @OneToMany
    @JoinColumn(name="team_id")
    private Set<Player> players = new HashSet<>();

    /**
     * A team participates in competitions.
     * Zero or more match records refer to the team based on such participation.
     */
    @OneToMany
    private Set<MatchRecord> matchRecords = new HashSet<>();

    /* additional getters and setters */

    /**
     * Adds a player to the set of team members.
     * Also sets the other side of the relationship (Player::team).
     * Does not check if the operation makes sense.
     *
     * @param player player entity to be added to the team
     */
    public void addPlayer(Player player) {
        this.players.add(player);
    }

    /**
     * Removes a player from the set of team members.
     * Also unsets the other side of the relationship (Player::team).
     * Does not check if the operation makes sense.
     *
     * @param player player entity to be removed from the team
     */
    public void removePlayer(Player player) {
        this.players.remove(player);
        player.setTeam(null);
    }

    /* equals and hashcode */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Team)) return false;
        Team team = (Team) o;
        return getName().equals(team.getName()) && getAbbreviation().equals(team.getAbbreviation());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getAbbreviation());
    }

    @Override
    public String toString(){
        return name;
    }
}
