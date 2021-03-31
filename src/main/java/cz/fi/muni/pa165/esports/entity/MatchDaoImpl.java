package cz.fi.muni.pa165.dao;

import cz.fi.muni.pa165.entity.Match;
import cz.fi.muni.pa165.entity.Player;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

/*
    @Author Elena Álvarez
*/

@Repository
public class MatchDaoImpl implements MatchDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Match findById(Long id) {
        return em.find(Match.class, id);
    }

    @Override
    public void create(Match match) {
        em.persist(match);
    }

    @Override
    public void delete(Match match) {
        em.remove(match);
    }

    @Override
    public List<Match> findAll() {
        return em.createQuery("select m from Match m", Match.class).getResultList();
    }

    @Override
    public Match findByName(String name) {
        try {
            return em.createQuery("select m from Match m where m.name = :name", Match.class)
                    .setParameter("name", name).getSingleResult();
        } catch (NoResultException nfr){
            return null;
        }
    }

    @Override
    public Match findByMatchNumber(Long match_number) {
        try {
            return em.createQuery("select m from Match m where m.match_number = :mn", Match.class)
                    .setParameter("mn", match_number).getSingleResult();
        } catch (NoResultException nfr){
            return null;
        }
    }


//    @Override
//    public Match findByPlayer(Player player) {
//        try {
//            return em.createQuery("select m from Match m join fetch m.players  p where p.id = :id", Match.class)
//                    .setParameter("id", player.id).getSingleResult();
//        } catch (NoResultException nfr){
//            return null;
//        }
//    }

    @Override
    public List<Match> findPlayers() {
        try {
            return em.createQuery("select m from Match m join fetch m.players", Match.class).getResultList();
        } catch (NoResultException nfr){
            return null;
        }
    }
}
