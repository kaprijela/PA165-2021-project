package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.entity.Game;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class GameDaoImpl implements GameDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Game findById(Long id) {
        return em.find(Game.class, id);
    }

    @Override
    public void create(Game game) {
        em.persist(game);
    }

    @Override
    public void delete(Game game) {
        em.remove(game);
    }

    @Override
    public List<Game> findAll() {
        return em.createQuery("select g from Game g", Game.class).getResultList();
    }

    @Override
    public Game findByName(String name) {
        try {
            return em.createQuery("select g from Game g where c.name = :name", Game.class)
                    .setParameter("name", name).getSingleResult();
        } catch (NoResultException nfr){
            return null;
        }
    }
}
