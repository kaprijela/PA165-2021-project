package cz.muni.fi.pa165.esports.dao;

import cz.muni.fi.pa165.esports.entity.Competition;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author jan gavlik
 */

@Repository
@Transactional
public class CompetitionDaoImpl implements CompetitionDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Competition findById(Long id) {
        return em.find(Competition.class, id);
    }

    @Override
    public void create(Competition competition) {
        em.persist(competition);
    }

    @Override
    public void delete(Competition competition) {
        if (em.contains(competition)) {
            em.remove(competition);
        } else {
            em.remove(em.merge(competition));
        }
    }

    @Override
    public List<Competition> findAll() {
        return em.createQuery("select c from Competition c", Competition.class).getResultList();
    }

    @Override
    public Competition findByName(String name) {
        try {
            return em.createQuery("select c from Competition c where c.name = :name", Competition.class)
                    .setParameter("name", name).getSingleResult();
        } catch (NoResultException nfr){
            return null;
        }
    }
}
