package cz.muni.fi.pa165.esports.dao;

import java.util.List;

/**
 * A generic data access object (DAO) interface.
 * @param <T> accessed entity class
 *
 * @author Gabriela Kandova
 */
public interface Dao<T> {
    /**
     * Stores entity of type {@link T} in the database.
     * @param t entity to be stored
     */
    void create(T t);

    /**
     * Removes entity of type {@link T} from the database.
     * @param t entity to be removed
     */
    void delete(T t);

    /**
     * Gets an entity of type {@link T} from the database according to its ID.
     * @param id ID of the entity
     * @return entity if found, else empty
     */
    T findById(Long id);

    /**
     * Gets all entities of type {@link T} from the database.
     * @return list of entities
     */
    List<T> findAll();
}
