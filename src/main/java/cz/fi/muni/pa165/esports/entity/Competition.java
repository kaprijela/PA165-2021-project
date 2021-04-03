package cz.fi.muni.pa165.esports.entity;

import cz.fi.muni.pa165.esports.enums.Game;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Objects;

/**
 * @author jan gavlik
 */

@Entity
public class Competition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Game game;
    private String location;
    private int prizepool;
    private LocalDate date;

    public Competition(Long id) {
        this.id = id;
    }

    public Competition() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getPrizepool() {
        return prizepool;
    }

    public void setPrizepool(int prizepool) {
        this.prizepool = prizepool;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
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
