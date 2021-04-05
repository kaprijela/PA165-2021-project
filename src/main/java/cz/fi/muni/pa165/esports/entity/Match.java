package cz.fi.muni.pa165.esports.entity;

import java.util.*;

import javax.persistence.*;
//import javax.validation.constraints.NotNull;

/*
    @Author Elena Álvarez
*/

@Entity
public class Match {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int score;
    private long match_number;

    @OneToMany(mappedBy = "match")
    private Set<Team> teams = new HashSet<>();

    @OneToMany(mappedBy = "match")
    private Set<Player> players = new HashSet<>();

    @ManyToOne
    private Competition competition;

    //Constructors
    public Match() { }
    public Match(String name, long match_number, int score) {
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
    public Set<Team> getTeams(){
        return teams;
    }

    public void setTeams(Set<Team> teams){
        this.teams = teams;
    }

    public void addTeam(Team team){
        teams.add(team);
    }

    public Set<Player> getPlayers(){
        return players;
    }

    public void setPlayers(Set<Player> players){
        this.players = players;
    }

    public void addPlayer(Player player){
        players.add(player);
    }

    public Competition getCompetitions(){
        return competition;
    }

    public void setCompetitions(Competition competitions){
        this.competition = competition;
    }

    public void addCompetition(Competition competition){
        competition.addMatch(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Match)) return false;
        Match match = (Match) o;
        return getScore() == match.getScore() && getMatch_number() == match.getMatch_number() && getName().equals(match.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getScore(), getMatch_number());
    }
}
