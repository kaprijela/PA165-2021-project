package cz.muni.fi.pa.esports.dto;

import java.util.Date;
import java.util.Objects;

/**
 * @author Radovan Tomasik
 */
public class PlayerDTO {
    private Long id;

    private String Name;

    private Date joinedDate;

    public PlayerDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public Date getJoinedDate() {
        return joinedDate;
    }

    public void setJoinedDate(Date joinedDate) {
        this.joinedDate = joinedDate;
    }

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

