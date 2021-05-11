package cz.muni.fi.pa165.esports.dao;

import cz.muni.fi.pa165.esports.entity.SystemUser;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Elena Álvarez
 */
@Repository
@Transactional
public class UserDaoImpl implements UserDao{

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(SystemUser systemUser) { em.persist(systemUser); }

    @Override
    public void delete(SystemUser systemUser) {
        if (em.contains(systemUser)) {
            em.remove(systemUser);
        } else {
            em.remove(em.merge(systemUser));
        }
    }

    @Override
    public SystemUser findById(Long id) { return em.find(SystemUser.class, id); }

    @Override
    public List<SystemUser> findAll() {
        return em.createQuery("select u from SystemUser u", SystemUser.class).getResultList();
    }

    @Override
    public SystemUser findByUsername(String username) { return em.find(SystemUser.class, username); }

    @Override
    public SystemUser findByEmail(String email) { return em.find(SystemUser.class, email); }
}
