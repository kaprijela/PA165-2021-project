package cz.muni.fi.pa165.esports.dto;

import cz.muni.fi.pa165.esports.enums.Role;
import lombok.Getter;
import lombok.NonNull;
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
    private Role role;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDTO)) return false;
        UserDTO userDTO = (UserDTO) o;
        return getEmail().equals(userDTO.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmail());
    }
}
