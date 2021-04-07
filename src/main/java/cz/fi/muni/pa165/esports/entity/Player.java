package cz.fi.muni.pa165.esports.entity;

import cz.fi.muni.pa165.esports.enums.Gender;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Objects;

/**
 * A Player is an entity representing an eSports Player, who might be a part of a team.
 *
 * @author Radovan Tomasik
 */
@Entity
public class Player {

    /* attributes */

    @Column(nullable = false)
    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Team team;
    /**
     * Represents a year of birth
     */
    private Integer year;

    @Enumerated(EnumType.STRING)
    private Gender gender;



    public Player() {
    }

    public Player(Long id) {
        this.id = id;
    }

    /* getters and setters */

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Integer getAge() {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        return currentYear - this.year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;
        Player player = (Player) o;
        return getId().equals(player.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
