package cz.muni.fi.pa165.esports.entity;


import cz.muni.fi.pa165.esports.enums.Game;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * @author jan gavlik
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Competition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * name of competition - has to be unique
     */
    @Column(unique = true)
    private String name;
    private Game game;
    private String location;
    private int prizepool;
    private LocalDate date;
    @ManyToMany
    private Set<Team> teams = new HashSet<>();

    /**
     * multiple MachRecord for each competition
     */
    @OneToMany
    private Set<MatchRecord> matchRecords;

    public Competition(Long id) {
        this.id = id;
    }

    public void removeTeam(Team team) {
        teams.remove(team);
    }

    public void addTeam(Team team) {
        teams.add(team);
    }

    public void addMatch(MatchRecord matchRecord) {
        matchRecords.add(matchRecord);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Competition)) return false;
        Competition that = (Competition) o;
        return getPrizepool() == that.getPrizepool() &&
                getName().equals(that.getName()) &&
                getGame() == that.getGame() &&
                getLocation().equals(that.getLocation()) &&
                getDate().equals(that.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getGame(), getLocation(), getPrizepool(), getDate());
    }
}
