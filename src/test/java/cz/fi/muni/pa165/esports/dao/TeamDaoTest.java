package cz.fi.muni.pa165.esports.dao;

import cz.fi.muni.pa165.esports.PersistenceSampleApplicationContext;
import cz.fi.muni.pa165.esports.entity.Team;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@Transactional
public class TeamDaoTest extends AbstractTestNGSpringContextTests {

    @PersistenceContext
    public EntityManager em;

    @Autowired
    public TeamDao teamDao;

    private Team t1;
    private Team t2;
    private Team t3;
    private Team t4;

    @BeforeMethod
    public void createTeams(){
        t1 = new Team();
        t2 = new Team();
        t3 = new Team();
        t4 = new Team();

        t1.setName("Orcs");
        t2.setName("Elves");
        t3.setName("Men of Gondor");
        t4.setName("Dwarves");

        t1.setAbbreviation("O");
        t2.setAbbreviation("E");
        t3.setAbbreviation("MOG");
        t4.setAbbreviation("D");

        teamDao.create(t1);
        teamDao.create(t2);
        teamDao.create(t3);
        teamDao.create(t4);
    }

    @Test
    public void findById(){
        Team found = teamDao.findById(t1.getId());
        Assert.assertEquals(found.getName(), t1.getName());
        Assert.assertNotEquals(found.getName(), t2.getName());
    }

    @Test
    public void findAll(){
        List<Team> found = teamDao.findAll();
        Assert.assertEquals(found.size(), 4);
    }

    @Test
    public void findByName(){
        Assert.assertEquals(teamDao.findByName("Orcs").getId(), t1.getId());
        Assert.assertEquals(teamDao.findByName("Elves").getId(), t2.getId());
        Assert.assertEquals(teamDao.findByName("Men of Gondor").getId(), t3.getId());
        Assert.assertEquals(teamDao.findByName("Dwarves").getId(), t4.getId());
    }

    @Test
    public void findByAbbreviation(){
        Assert.assertEquals(teamDao.findByAbbreviation("O").getId(), t1.getId());
        Assert.assertEquals(teamDao.findByAbbreviation("E").getId(), t2.getId());
        Assert.assertEquals(teamDao.findByAbbreviation("MOG").getId(), t3.getId());
        Assert.assertEquals(teamDao.findByAbbreviation("D").getId(), t4.getId());
    }
}
