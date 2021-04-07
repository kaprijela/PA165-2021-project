package cz.fi.muni.pa165.esports.entity;

import java.util.*;

import javax.persistence.*;
//import javax.validation.constraints.NotNull;


/**
 @Author Elena Álvarez
 */

@Entity
public class MatchRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int score;
    private long match_number;

@ManyToOne
    private Team team;

@ManyToOne
    private Player player;

    @ManyToOne
    private Competition competition;

    //Constructors
    public MatchRecord() { }
    public MatchRecord(String name, long match_number, int score) {
        this.match_number = match_number;
        this.name= name;
        this.score = score;
    }


    //Getters and Setters
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public long getMatch_number() {
        return match_number;
    }

    public void setMatch_number(long match_number) {
        this.match_number = match_number;
    }

    //Relations getters and setters
    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Competition getCompetition() {
        return competition;
    }

    public void setCompetition(Competition competition) {
        this.competition = competition;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MatchRecord)) return false;
        MatchRecord match = (MatchRecord) o;
        return getScore() == match.getScore() && getMatch_number() == match.getMatch_number() && getName().equals(match.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getScore(), getMatch_number());
    }
}
