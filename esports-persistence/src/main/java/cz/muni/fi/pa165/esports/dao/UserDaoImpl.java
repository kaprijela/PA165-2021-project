package cz.muni.fi.pa165.esports.dao;

import cz.muni.fi.pa165.esports.entity.User;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;

/**
 * @author Elena Álvarez
 */
@Repository
@Transactional
public class UserDaoImpl implements UserDao{
    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(User user) { em.persist(user); }

    @Override
    public void delete(User user) {
        if (em.contains(user)) {
            em.remove(user);
        } else {
            em.remove(em.merge(user));
        }
    }

    @Override
    public User findById(Long id) { return em.find(User.class, id); }

    @Override
    public List<User> findAll() {
        return em.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    public User findByUsername(String username) { return em.find(User.class, username); }

    @Override
    public User findByEmail(String email) { return em.find(User.class, email); }
}
