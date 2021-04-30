package cz.muni.fi.pa165.esports.entity;

import cz.muni.fi.pa165.esports.enums.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

/**
 * A Player is an entity representing an eSports Player, who might be a part of a team.
 *
 * @author Radovan Tomasik
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
public class Player {

    /* attributes */

    @Column(nullable = false)
    private String name;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Team team;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    public Player(Long id) {
        this.id = id;
    }

    /**
     * This implementation of equals is is this way because we allow tow players with the same name and gender to be on
     * the same team.
     * @param o object
     * @return true or false
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Player)) return false;
        Player player = (Player) o;
        return getName().equals(player.getName()) && getId().equals(player.getId()) && Objects.equals(getTeam(), player.getTeam()) && getGender() == player.getGender();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getId(), getTeam(), getGender());
    }
}
