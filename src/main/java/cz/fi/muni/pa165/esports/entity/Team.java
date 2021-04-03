package cz.fi.muni.pa165.esports.entity;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;
import java.util.HashSet;

/**
 * Team is an entity that represents an eSports team.
 * A team consists of players and participates in competitions.
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

    private String name;

    private String abbreviation;

    private String description;

    private Set<Player> players = new HashSet<>();

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
