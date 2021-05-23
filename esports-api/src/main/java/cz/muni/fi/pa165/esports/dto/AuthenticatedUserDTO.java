package cz.muni.fi.pa165.esports.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

/**
 * Data transfer object for Users who are authenticated
 *
 * @author Elena Alvarez
 */
@Getter
@Setter
public class AuthenticatedUserDTO {
    //You only need the password to check the hash for knowing if it is authenticated
    private String username;
    private String email;
    private String password;
    private boolean isAdmin;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AuthenticatedUserDTO)) return false;
        AuthenticatedUserDTO that = (AuthenticatedUserDTO) o;
        return Objects.equals(getEmail(), that.getEmail()) && Objects.equals(getPassword(), that.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmail(), getPassword());
    }
}
