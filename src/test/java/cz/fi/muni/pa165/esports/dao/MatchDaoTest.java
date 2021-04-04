package cz.fi.muni.pa165.esports.dao;

import cz.fi.muni.pa165.esports.PersistenceSampleApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.transaction.annotation.Transactional;
import org.testng.annotations.Test;


import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

/**
 * @author jan gavlik
 */

@ContextConfiguration(classes= PersistenceSampleApplicationContext.class)
public class MatchDaoTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private MatchDao matchDao;

    @Test
    public void createTest(){

    }



}
