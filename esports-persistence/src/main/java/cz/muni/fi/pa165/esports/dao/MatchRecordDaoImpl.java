package cz.muni.fi.pa165.esports.dao;

import cz.muni.fi.pa165.esports.entity.Competition;
import cz.muni.fi.pa165.esports.entity.MatchRecord;
import cz.muni.fi.pa165.esports.entity.Player;
import cz.muni.fi.pa165.esports.entity.Team;
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
public class MatchRecordDaoImpl implements MatchRecordDao {

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

    @Override
    public List<MatchRecord> findByCompetition(Competition competition) {
        try {
            return em.createQuery("select m from MatchRecord m where m.competition = :competition ", MatchRecord.class)
                    .setParameter("competition", competition).getResultList();
        } catch (NoResultException nfr){
            return null;
        }
    }

    @Override
    public List<MatchRecord> findByTeam(Team team) {
        try {
            return em.createQuery("select m from MatchRecord m where m.team = :team ", MatchRecord.class)
                    .setParameter("team", team).getResultList();
        } catch (NoResultException nfr){
            return null;
        }
    }
}
