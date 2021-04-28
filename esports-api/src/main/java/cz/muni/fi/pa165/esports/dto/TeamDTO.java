package cz.muni.fi.pa165.esports.dto;

import lombok.Getter;
import lombok.Setter;

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
}
