package cz.muni.fi.pa165.esports.dto;

import cz.muni.fi.pa165.esports.enums.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Objects;

/**
 * @author Radovan Tomasik
 */
@Getter
@Setter
@NoArgsConstructor
public class PlayerDTO {
    private Long id;

    private String Name;

    private Gender gender;

    private Date joinedDate;

    private String team;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PlayerDTO)) return false;
        PlayerDTO playerDTO = (PlayerDTO) o;
        return getId().equals(playerDTO.getId()) && Name.equals(playerDTO.Name) && getJoinedDate().equals(playerDTO.getJoinedDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), Name, getJoinedDate());
    }
}

