package cz.muni.fi.pa165.esports.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;
import java.util.Set;

/**
 * Data transfer object for the Team entity.
 * @author Gabriela Kandova
 */
@Getter
@Setter
public class TeamDTO {
    private Long id;
    private String name;
    private String abbreviation;
    private String description;
    private Set<PlayerDTO> players;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TeamDTO)) return false;
        TeamDTO teamDTO = (TeamDTO) o;
        return getName().equals(teamDTO.getName()) && getAbbreviation().equals(teamDTO.getAbbreviation());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getAbbreviation());
    }
}
