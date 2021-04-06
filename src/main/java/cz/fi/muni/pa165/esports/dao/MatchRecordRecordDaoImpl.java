package cz.fi.muni.pa165.esports.dao;

import cz.fi.muni.pa165.esports.entity.MatchRecord;
import cz.fi.muni.pa165.esports.entity.Player;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

/*
    @Author Elena Álvarez
*/

@Repository
@Transactional
public class MatchRecordRecordDaoImpl implements MatchRecordDao {

    @PersistenceContext
    private EntityManager em;

    @Override
    public MatchRecord findById(Long id) {
        return em.find(MatchRecord.class, id);
    }

    @Override
    public void create(MatchRecord matchRecord) {
        em.persist(matchRecord);
    }

    @Override
    public void delete(MatchRecord matchRecord) {
        em.remove(matchRecord);
    }

    @Override
    public List<MatchRecord> findAll() {
        return em.createQuery("select m from MatchRecord m", MatchRecord.class).getResultList();
    }

    @Override
    public MatchRecord findByName(String name) {
        try {
            return em.createQuery("select m from MatchRecord m where m.name = :name", MatchRecord.class)
                    .setParameter("name", name).getSingleResult();
        } catch (NoResultException nfr){
            return null;
        }
    }

    @Override
    public MatchRecord findByMatchNumber(Long match_number) {
        try {
            return em.createQuery("select m from MatchRecord m where m.match_number = :mn", MatchRecord.class)
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
    public List<Player> findPlayers() {
        try {
            return em.createQuery("select m.player from MatchRecord m", Player.class).getResultList();
        } catch (NoResultException nfr){
            return null;
        }
    }

}
