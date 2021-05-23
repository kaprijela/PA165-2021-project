package cz.muni.fi.pa165.esports.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

/**
 * Data transfer object for the User entity.
 *
 * @author Elena Alvarez
 */
@Getter
@Setter
public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private boolean isAdmin;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDTO)) return false;
        UserDTO userDTO = (UserDTO) o;
        return Objects.equals(getUsername(), userDTO.getUsername()) && Objects.equals(getEmail(), userDTO.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getEmail());
    }
}
