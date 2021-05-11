package cz.muni.fi.pa165.esports.dao;

import cz.muni.fi.pa165.esports.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.esports.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
public class UserDaoTest extends AbstractTestNGSpringContextTests {

    @PersistenceContext
    public EntityManager em;

    @Autowired
    private UserDao userDao;

    private User user1;
    private User user2;


    @Test
    public void createTest() {
        user1 = new User();
        user1.setAdmin(false);
        user1.setUsername("elenuki33");
        user1.setEmail("elenuki33@gmail.com");
        user1.setPasswordHash("password");

        user2 = new User();
        user2.setAdmin(false);
        user2.setUsername("lauratrinanes");
        user2.setEmail("lauratrinanes@gmail.com");
        user2.setPasswordHash("password");

        userDao.create(user1);
        userDao.create(user2);

       Assert.assertFalse(userDao.findAll().isEmpty());
    }

    @Test
    public void findAllTest(){
        List<User> userFound = userDao.findAll();
        Assert.assertEquals(userFound.size(), 2);
    }

    @Test
    public void findByIdTest(){
        User userById = userDao.findById(user1.getId());
        Assert.assertEquals(userById.getUsername(), "elenuki33");
    }


    @Test
    public void findByUsernameTest(){
        Assert.assertEquals(userDao.findByUsername("elenuki33").getId(), user1.getId());
        Assert.assertEquals(userDao.findByUsername("lauratrinanes").getId(), user2.getId());

    }

    @Test
    public void findByEmailTest(){
        Assert.assertEquals(userDao.findByEmail("elenuki33@gmail.com").getId(), user1.getId());
        Assert.assertEquals(userDao.findByEmail("lauratrinanes@gmail.com").getId(), user2.getId());

    }
}
