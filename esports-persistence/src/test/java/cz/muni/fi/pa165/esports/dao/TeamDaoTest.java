package cz.muni.fi.pa165.esports.dao;

import cz.muni.fi.pa165.esports.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.esports.entity.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import java.util.List;

/**
 * @author Radovan Tomasik
 * Class for testing {@link TeamDao}
 */
@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@Transactional
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_CLASS)
public class TeamDaoTest extends AbstractTestNGSpringContextTests {

    @PersistenceUnit
    protected EntityManagerFactory emf;

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private TeamDao teamDao;

    private Team t1;
    private Team t2;
    private Team t3;
    private Team t4;

    @BeforeClass
    public void setup() {
        em = emf.createEntityManager();
        em.getTransaction().begin();

        t1 = new Team();
        t1.setName("Orcs");
        t1.setAbbreviation("O");
        em.persist(t1);
        t1 = em.find(Team.class, t1.getId()); // replace with managed instance

        t2 = new Team();
        t2.setName("Elves");
        t2.setAbbreviation("E");
        em.persist(t2);
        t2 = em.find(Team.class, t2.getId()); // replace with managed instance

        t3 = new Team();
        t3.setName("Men of Gondor");
        t3.setAbbreviation("MOG");
        em.persist(t3);
        t3 = em.find(Team.class, t3.getId()); // replace with managed instance

        t4 = new Team();
        t4.setName("Dwarves");
        t4.setAbbreviation("D");
        em.persist(t4);
        t4 = em.find(Team.class, t4.getId()); // replace with managed instance

        em.getTransaction().commit();
    }

    @Test
    public void createTeam() {
        Team t5 = new Team();
        t5.setName("Team 5");
        t5.setAbbreviation("T5");
        t5.setDescription("Team 5");
        teamDao.create(t5);

        Team foundTeam = em.find(Team.class, t5.getId());
        Assert.assertNotNull(foundTeam);
        Assert.assertEquals(foundTeam, t5);
    }

    @Test
    public void findById() {
        Team found = teamDao.findById(t1.getId());
        Assert.assertEquals(found.getName(), t1.getName());
        Assert.assertNotEquals(found.getName(), t2.getName());
    }

    @Test
    public void findAll() {
        List<Team> found = teamDao.findAll();
        Assert.assertEquals(found.size(), 5);
    }

    @Test
    public void findByName() {
        Assert.assertEquals(teamDao.findByName("Orcs").getId(), t1.getId());
        Assert.assertEquals(teamDao.findByName("Elves").getId(), t2.getId());
        Assert.assertEquals(teamDao.findByName("Men of Gondor").getId(), t3.getId());
        Assert.assertEquals(teamDao.findByName("Dwarves").getId(), t4.getId());
    }

    @Test
    public void findByAbbreviation() {
        Assert.assertEquals(teamDao.findByAbbreviation("O").getId(), t1.getId());
        Assert.assertEquals(teamDao.findByAbbreviation("E").getId(), t2.getId());
        Assert.assertEquals(teamDao.findByAbbreviation("MOG").getId(), t3.getId());
        Assert.assertEquals(teamDao.findByAbbreviation("D").getId(), t4.getId());
    }

    @Test
    public void removeTeam() {
        teamDao.delete(t4);
        Assert.assertNull(teamDao.findById(t4.getId()));
    }
}
