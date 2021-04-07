package cz.fi.muni.pa165.esports.entity;

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
    private long matchnumber;

    @ManyToOne
    private Team team;

    @ManyToOne
    private Player player;

    @ManyToOne
    private Competition competition;

    //Constructors
    public MatchRecord() {
    }

    public MatchRecord(long matchnumber, int score) {
        this.matchnumber = matchnumber;
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

    public long getMatchnumber() {
        return matchnumber;
    }

    public void setMatchnumber(long match_number) {
        this.matchnumber = match_number;
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

    public void setCompetition(Competition competitions) {
        this.competition = competition;
    }

    public void addCompetition(Competition competition) {
        competition.addMatch(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MatchRecord)) return false;
        MatchRecord that = (MatchRecord) o;
        return getScore() == that.getScore() && getMatchnumber() == that.getMatchnumber();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getScore(), getMatchnumber());
    }
}
