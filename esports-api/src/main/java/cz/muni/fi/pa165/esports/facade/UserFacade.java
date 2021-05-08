package cz.muni.fi.pa165.esports.facade;

import cz.muni.fi.pa165.esports.dto.AuthenticatedUserDTO;
import cz.muni.fi.pa165.esports.dto.UserDTO;

import javax.validation.ValidationException;
import java.util.List;

/**
 * @author Elena Alvarez
 */
public interface UserFacade {
    /**
     * Will register a new user in the system
     *
     * @param user
     * @param password password without hash
     * @return id of the new user
     */
    Long registerNewUser(UserDTO user, String password) throws ValidationException;

    /**
     * Will remove a user int he system
     *
     * @param id Id of the user to be removed
     */
    void removeUser(Long id);

    /**
     * Prove if a user is authenticate in the system
     *
     * @param authenticatedUser user
     * @return true if the password matches the hash stored previously in the system
     */
    boolean isAuthenticated(AuthenticatedUserDTO authenticatedUser);

    /**
     * Prove if a user is admin
     *
     * @param user
     * @return true, if user exists and is admin, false if not
     */
    boolean isAdmin(UserDTO user);

    /**
     * Get all registered users.
     *
     * @return list of users
     */
    List<UserDTO> getAllUsers();

    /**
     * Get a registered USER according to its ID.
     *
     * @param id unique ID of a team
     * @return User with the given ID, NULL if not found
     */
    UserDTO findById(Long id);

    /**
     * Get a registered USER according to its USERNAME.
     *
     * @param username username of a user
     * @return User with the given username, NULL if not found
     */
    UserDTO findByUsername(String username);

    /**
     * Get a registered USER according to its EMAIL.
     *
     * @param email email of a User
     * @return User with the given email, NULL if not found
     */
    UserDTO findByEmail(String email);

}
