package cz.fi.muni.pa165.esports.dao;

import cz.fi.muni.pa165.esports.entity.MatchRecord;
import cz.fi.muni.pa165.esports.entity.Player;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;



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
    public MatchRecord findByMatchNumber(Long match_number) {
        try {
            return em.createQuery("select m from MatchRecord m where m.match_number = :mn", MatchRecord.class)
                    .setParameter("mn", match_number).getSingleResult();
        } catch (NoResultException nfr){
            return null;
        }
    }


    @Override
    public List<Player> findPlayers(Long match_number) {
        try {
            return em.createQuery("select m.player from MatchRecord m where m.match_number = :match_number ", Player.class)
                    .setParameter("match_number", match_number).getResultList();
        } catch (NoResultException nfr){
            return null;
        }
    }

}
