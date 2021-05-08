package cz.muni.fi.pa165.esports.service;

import java.util.List;

import cz.muni.fi.pa165.esports.entity.*;

import org.springframework.stereotype.Service;

/**
 * @author Elena Álvarez
 */
@Service
public interface UserService {
    /**
     * Register a user in the system.
     *
     * @param user User to be registered
     * @param password password without hash
     */
    public User create(User user, String password);

    /**
     * Remove a user from the system.
     *
     * @param user User to be removed
     */
    public void delete(User user);


    /**
     * Fetches all Users.
     *
     * @return return all Users
     */
    public List<User> findAll();

    /**
     * Fetch a registered User according to its ID.
     *
     * @param id unique ID of a team
     * @return User with the given ID, NULL if not found
     */
    public User findById(Long id);

    /**
     * Fetch a registered User according to its username.
     *
     * @param username username of a user
     * @return User with the given username, NULL if not found
     */
    public User findByUsername(String username);

    /**
     * Fetch a registered User according to its email.
     *
     * @param email email of a User
     * @return User with the given email, NULL if not found
     */
    public User findByEmail(String email);

    /**
     * Prove if a user is authenticate in the system
     *
     * @param user entity of a user
     * @param password  password provided
     * @return true, if the password matches the hash stored previously, false if not
     */
    boolean isAuthenticated(User user, String password);

    /**
     * Prove if a user is admin
     *
     * @param id ID of a user
     * @return true, if user exists and is admin, false if not
     */
    boolean isAdmin(Long id);
}
