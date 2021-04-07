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
    private Long player_id;

    @ManyToOne
    private Team team;

    private Integer year;

    @Enumerated(EnumType.STRING)
    private Gender gender;



    public Player() {
    }

    public Player(Long player_id) {
        this.player_id = player_id;
    }

    /* getters and setters */

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPlayer_id() {
        return player_id;
    }

    public void setPlayer_id(Long player_id) {
        this.player_id = player_id;
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
        return getPlayer_id().equals(player.getPlayer_id());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPlayer_id());
    }
}
