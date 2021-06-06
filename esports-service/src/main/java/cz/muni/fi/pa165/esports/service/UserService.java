package cz.muni.fi.pa165.esports.service;

import cz.muni.fi.pa165.esports.entity.SystemUser;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Elena Álvarez
 */
@Service
public interface UserService {
    /**
     * Register a user in the system.
     *
     * @param systemUser User to be registered
     * @param password   password without hash
     */
    SystemUser create(SystemUser systemUser, String password);

    /**
     * Remove a user from the system.
     *
     * @param systemUser User to be removed
     */
    void delete(SystemUser systemUser);

    /**
     * Fetches all Users.
     *
     * @return return all Users
     */
    List<SystemUser> findAll();

    /**
     * Fetch a registered User according to its ID.
     *
     * @param id unique ID of a team
     * @return User with the given ID, NULL if not found
     */
    SystemUser findById(Long id);

    /**
     * Fetch a registered User according to its username.
     *
     * @param username username of a user
     * @return User with the given username, NULL if not found
     */
    SystemUser findByUsername(String username);

    /**
     * Fetch a registered User according to its email.
     *
     * @param email email of a User
     * @return User with the given email, NULL if not found
     */
    SystemUser findByEmail(String email);

    /**
     * Prove if a user is authenticate in the system
     *
     * @param systemUser entity of a user
     * @param password   password provided
     * @return true, if the password matches the hash stored previously, false if not
     */
    boolean isAuthenticated(SystemUser systemUser, String password);

    /**
     * Prove if a user is admin
     *
     * @param id ID of a user
     * @return true, if user exists and is admin, false if not
     */
    boolean isAdmin(Long id);
}
