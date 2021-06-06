package cz.muni.fi.pa165.esports.entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

import cz.muni.fi.pa165.esports.enums.*;

/**
 * An entity describing users.
 * @author Elena Álvarez
 */

@Entity
@Getter
@Setter
@NoArgsConstructor
public class SystemUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Column(nullable = false, unique = true)
    private String username;

    @NotNull
    @NotBlank
    @Column(nullable = false)
    private String passwordHash;

    @Email
    @NotNull
    @NotBlank
    @Column(nullable = false, unique = true)
    private String email;

    private Role role;

    public SystemUser(@NotBlank @NotNull String username, @NotBlank @NotNull String passwordHash, @NotBlank @NotNull @Email String email) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SystemUser)) return false;
        SystemUser that = (SystemUser) o;
        return getEmail().equals(that.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmail());
    }
}
