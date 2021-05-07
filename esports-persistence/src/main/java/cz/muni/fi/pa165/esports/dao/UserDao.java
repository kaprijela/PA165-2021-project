package cz.muni.fi.pa165.esports.dao;


import cz.muni.fi.pa165.esports.entity.*;

import java.util.List;

/**
 * A DAO interface for the User entity.
 *
 * @author Elena Álvarez
 */
public interface UserDao {

    /**
     * Adds a User to the database.
     * @param user entity to be persisted
     */
    public void create(User user);

    /**
     * Deletes a User from the database.
     * @param user entity to be deleted
     */
    public void delete(User user);

    /**
     * Fetches a User according to its ID from the database.
     * @param id of the sought MatchRecord entity
     * @return user entity if found, else null
     */
    public User findById(Long id);

    /**
     * Fetches all Users.
     * @return list of all User entities in the database
     */
    public List<User> findAll();

    /**
     * Fetches a User.
     * @param name
     * @return return User for a given name
     */
    User findByUsername(String name);

    /**
     * Fetches a User.
     * @param email
     * @return return User for a given email
     */
    User findByEmail(String email);

}
