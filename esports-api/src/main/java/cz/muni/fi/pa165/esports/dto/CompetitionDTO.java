package cz.muni.fi.pa165.esports.dto;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CompetitionDTO {
    private Long id;
    @EqualsAndHashCode.Include
    private String name;
    private String game;
    private String location;
    private int prizepool;
    private Set<TeamDTO> teams = new HashSet<>();
    private LocalDate date;
}
