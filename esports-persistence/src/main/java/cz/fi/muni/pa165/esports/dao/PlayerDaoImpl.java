package cz.fi.muni.pa165.esports.dao;

import cz.fi.muni.pa165.esports.entity.Player;
import cz.fi.muni.pa165.esports.enums.Gender;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Radovan Tomasik
 */
@Repository
@Transactional
public class PlayerDaoImpl implements PlayerDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Player findById(Long id) {
        return em.find(Player.class, id);
    }

    @Override
    public void create(Player player) {
        em.persist(player);
    }

    @Override
    public void delete(Player player) {
        if (em.contains(player)) {
            em.remove(player);
        } else {
            em.remove(em.merge(player));
        }
    }

    @Override
    public List<Player> findAll() {
        return em.createQuery("select p from Player p", Player.class).getResultList();
    }

    @Override
    public List<Player> findByName(String name) {
        return em.createQuery("select p from Player p where p.name = :name", Player.class)
                .setParameter("name", name).getResultList();
    }

    @Override
    public List<Player> findByGender(Gender gender) {
        return em.createQuery("select p from Player p where p.gender = :gender", Player.class)
                .setParameter("gender", gender).getResultList();
    }
}
