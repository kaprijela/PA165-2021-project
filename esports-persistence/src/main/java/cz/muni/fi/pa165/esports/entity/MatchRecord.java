package cz.muni.fi.pa165.esports.entity;

import java.util.*;

import javax.persistence.*;
//import javax.validation.constraints.NotNull;


/**
 @author Elena Álvarez
 */

@Entity
public class MatchRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int score;
    private long matchNumber;

    @ManyToOne
    private Team team;

    @ManyToOne
    private Player player;

    @ManyToOne
    private Competition competition;

    //Constructors
    public MatchRecord() {
    }

    public MatchRecord(long matchNumber, int score) {
        this.matchNumber = matchNumber;
        this.score = score;
    }

    public MatchRecord(Long id) {
        this.id = id;
    }

    //Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public long getMatchNumber() {
        return matchNumber;
    }

    public void setMatchNumber(long matchNumber) {
        this.matchNumber = matchNumber;
    }

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


    //Equals and HashCode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MatchRecord)) return false;
        MatchRecord that = (MatchRecord) o;
        return getMatchNumber() == that.getMatchNumber() && getTeam().equals(that.getTeam()) && getPlayer().equals(that.getPlayer()) && getCompetition().equals(that.getCompetition());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getMatchNumber(), getTeam(), getPlayer(), getCompetition());
    }
}
