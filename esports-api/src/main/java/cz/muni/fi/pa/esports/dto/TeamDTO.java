package cz.muni.fi.pa.esports.dto;

import java.util.Set;

/**
 * Data transfer object for the Team entity.
 * @author Gabriela Kandova
 */
public class TeamDTO {
    private Long id;
    private String name;
    private String abbreviation;
    private String description;
    private Set<PlayerDTO> players;

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

    public String getAbbreviation() {
        return abbreviation;
    }

    public void setAbbreviation(String abbreviation) {
        this.abbreviation = abbreviation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<PlayerDTO> getPlayers() {
        return players;
    }

    public void setPlayers(Set<PlayerDTO> players) {
        this.players = players;
    }
}
