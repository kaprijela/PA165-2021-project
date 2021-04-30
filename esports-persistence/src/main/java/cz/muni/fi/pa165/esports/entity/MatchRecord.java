package cz.muni.fi.pa165.esports.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;


/**
 * An entity describing the score result of one player in a specific competition match.
 * @author Elena Álvarez
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
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

    public MatchRecord(long matchNumber, int score) {
        this.matchNumber = matchNumber;
        this.score = score;
    }

    public MatchRecord(Long id) {
        this.id = id;
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
