package cz.muni.fi.pa165.esports.dao;

import cz.muni.fi.pa165.esports.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.esports.entity.SystemUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.Test;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;


/**
 * @author Elena Álvarez
 * Class for testing UserDao
 */
@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@Transactional
@DirtiesContext
public class SystemUserDaoTest extends AbstractTestNGSpringContextTests {

    @PersistenceContext
    public EntityManager em;

    @Autowired
    private UserDao userDao;

    private SystemUser systemUser1;
    private SystemUser systemUser2;


    @Test
    public void createTest() {
        systemUser1 = new SystemUser();
        systemUser1.setAdmin(false);
        systemUser1.setUsername("elenuki33");
        systemUser1.setEmail("elenuki33@gmail.com");
        systemUser1.setPasswordHash("password");

        systemUser2 = new SystemUser();
        systemUser2.setAdmin(false);
        systemUser2.setUsername("lauratrinanes");
        systemUser2.setEmail("lauratrinanes@gmail.com");
        systemUser2.setPasswordHash("password");

        userDao.create(systemUser1);
        userDao.create(systemUser2);

       Assert.assertFalse(userDao.findAll().isEmpty());
    }

    @Test
    public void findAllTest(){
        List<SystemUser> systemUserFound = userDao.findAll();
        Assert.assertEquals(systemUserFound.size(), 2);
    }

    @Test
    public void findByIdTest(){
        SystemUser systemUserById = userDao.findById(systemUser1.getId());
        Assert.assertEquals(systemUserById.getUsername(), "elenuki33");
    }


    @Test
    public void findByUsernameTest(){
        Assert.assertEquals(userDao.findByUsername("elenuki33").getId(), systemUser1.getId());
        Assert.assertEquals(userDao.findByUsername("lauratrinanes").getId(), systemUser2.getId());

    }

    @Test
    public void findByEmailTest(){
        Assert.assertEquals(userDao.findByEmail("elenuki33@gmail.com").getId(), systemUser1.getId());
        Assert.assertEquals(userDao.findByEmail("lauratrinanes@gmail.com").getId(), systemUser2.getId());

    }
}
