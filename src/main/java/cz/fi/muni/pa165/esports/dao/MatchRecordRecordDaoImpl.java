package cz.fi.muni.pa165.esports.dao;

import cz.fi.muni.pa165.esports.entity.MatchRecord;
import cz.fi.muni.pa165.esports.entity.Player;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;


/**
 * @author Elena Álvarez
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
        if (em.contains(matchRecord)) {
            em.remove(matchRecord);
        } else {
            em.remove(em.merge(matchRecord));
        }
    }

    @Override
    public List<MatchRecord> findAll() {
        return em.createQuery("select m from MatchRecord m", MatchRecord.class).getResultList();
    }

    @Override
    public List<MatchRecord> findByPlayer(Player player) {
        try {
            return em.createQuery("select m from MatchRecord m where m.player = :player ", MatchRecord.class)
                    .setParameter("player", player).getResultList();
        } catch (NoResultException nfr){
            return null;
        }
    }


}
