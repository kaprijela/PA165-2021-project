package cz.muni.fi.pa165.esports.dao;

import cz.muni.fi.pa165.esports.entity.Player;
import cz.muni.fi.pa165.esports.entity.Team;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * An implementation of {@link TeamDao}.
 *
 * @author Gabriela Kandova
 */
@Repository
@Transactional
public class TeamDaoImpl implements TeamDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void create(Team team) {
        em.persist(team);
    }

    @Override
    public void delete(Team team) {
        if (em.contains(team)) {
            em.remove(team);
        } else {
            em.remove(em.merge(team));
        }
    }

    @Override
    public void addPlayer(Team team, Player player) {
        team.addPlayer(player);
        em.merge(team);
        em.merge(player);
    }

    @Override
    public void removePlayer(Team team, Player player) {
        team.removePlayer(player);
        em.merge(team);
        em.merge(player);
    }

    @Override
    public Team findById(Long id) {
        try {
            return em.createQuery("select t from Team t left join fetch t.players where t.id = :id", Team.class)
                    .setParameter("id", id).getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    @Override
    public List<Team> findAll() {
        return em.createQuery("select t from Team t left join fetch t.players", Team.class).getResultList();
    }

    @Override
    public Team findByName(String name) {
        try {
            return em.createQuery("select t from Team t left join fetch t.players where t.name = :name", Team.class)
                    .setParameter("name", name).getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    @Override
    public Team findByAbbreviation(String abbreviation) {
        try {
            return em.createQuery("select t from Team t left join fetch t.players where t.abbreviation = :abbr", Team.class)
                    .setParameter("abbr", abbreviation).getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }
}
