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
     * @param systemUser entity to be persisted
     */
    public void create(SystemUser systemUser);

    /**
     * Deletes a User from the database.
     * @param systemUser entity to be deleted
     */
    public void delete(SystemUser systemUser);

    /**
     * Fetches a User according to its ID from the database.
     * @param id of the sought MatchRecord entity
     * @return user entity if found, else null
     */
    public SystemUser findById(Long id);

    /**
     * Fetches all Users.
     * @return list of all User entities in the database
     */
    public List<SystemUser> findAll();

    /**
     * Fetches a User.
     * @param name
     * @return return User for a given name
     */
    SystemUser findByUsername(String name);

    /**
     * Fetches a User.
     * @param email
     * @return return User for a given email
     */
    SystemUser findByEmail(String email);

}
