package cz.muni.fi.pa165.esports.entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
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

    @NotBlank
    @Column(nullable = false, unique = true)
    private String username;

    @NotNull
    @Column(nullable = false)
    private String passwordHash;

    @NotBlank
    @Column(nullable = false, unique = true)
    private String email;

    private boolean isAdmin;

    //User role
    private Role role;

    public SystemUser(@NotBlank String username, @NotNull String passwordHash, @NotBlank String email) {
        this.username = username;
        this.passwordHash = passwordHash;
        this.email = email;
        this.isAdmin = false;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public Role getRole() { return role; }

    public void setRole(Role role) { this.role = role; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SystemUser)) return false;
        SystemUser systemUser = (SystemUser) o;
        return Objects.equals(getUsername(), systemUser.getUsername()) && Objects.equals(getEmail(), systemUser.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getEmail());
    }
}
