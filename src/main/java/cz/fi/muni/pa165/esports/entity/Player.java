package cz.fi.muni.pa165.esports.entity;

import cz.fi.muni.pa165.esports.enums.Gender;

import javax.persistence.*;
import java.util.Objects;

/**
 * Player entity
 * @author Radovan Tomasik
 */
@Entity
public class Player {
    @Column(nullable = false)
    private String name;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long player_id;
    private Integer age;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    public Player() {
    }

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
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
