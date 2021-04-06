package cz.fi.muni.pa165.esports.entity;

import javax.persistence.*;
import java.util.Collections;
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

@Entity
public class Team {
    public Team(Long id) {
        this.id = id;
    }

    public Team() {
    }

    /* attributes */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * A team must have a unique name.
     */
    @Column(unique = true, nullable = false)
    private String name;

    /**
     * A team must also have a unique abbreviation.
     */
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
    @OneToMany(mappedBy = "team")
    private Set<Player> players = new HashSet<>();

    /**
     * A team participates in competitions.
     * Zero or more match records refer to the team based on such participation.
     */
    @OneToMany(mappedBy = "team")
    private Set<MatchRecord> matchRecords = new HashSet<>();

    /* getters and setters */

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Player> getPlayers() {
        return Collections.unmodifiableSet(players);
    }

    public void addPlayer(Player player) {
        this.players.add(player);
    }

    public void removePlayer(Player player) {
        this.players.remove(player);
    }

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
}
