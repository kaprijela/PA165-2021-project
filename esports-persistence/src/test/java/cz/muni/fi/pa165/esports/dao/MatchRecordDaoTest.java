package cz.muni.fi.pa165.esports.dao;

import cz.muni.fi.pa165.esports.PersistenceSampleApplicationContext;
import cz.muni.fi.pa165.esports.entity.MatchRecord;
import cz.muni.fi.pa165.esports.entity.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.util.List;

/**
 * @author jan gavlik
 */

@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@Transactional
@DirtiesContext
public class MatchRecordDaoTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private MatchRecordDao matchRecordDao;

    @Autowired
    private PlayerDao playerDao;
    @Test
    public void createTest() {
        MatchRecord matchRecord = new MatchRecord();
        matchRecord.setScore(1);
        matchRecordDao.create(matchRecord);
        Assert.assertFalse(matchRecordDao.findAll().isEmpty());
    }

    @Test
    public void findByIdTest(){
        MatchRecord byId = matchRecordDao.findById(1L);
        Assert.assertEquals(byId.getScore(), 1);
    }

    @Test
    public void findByNonExistantIdTest(){
        MatchRecord byId = matchRecordDao.findById(23L);
        Assert.assertNull(byId);
    }

    @Test
    public void removeTest(){
        MatchRecord byId = matchRecordDao.findById(1L);
        matchRecordDao.delete(byId);
        Assert.assertTrue(matchRecordDao.findAll().isEmpty());
    }

    @Test
    public void findByPlayerTest(){
        MatchRecord matchRecord = new MatchRecord();
        Player bonkedPlayer = new Player();
        bonkedPlayer.setName("Bonked");
        matchRecord.setScore(0);

        matchRecord.setPlayer(bonkedPlayer);
        playerDao.create(bonkedPlayer);
        matchRecordDao.create(matchRecord);
        List<MatchRecord> byPlayer = matchRecordDao.findByPlayer(bonkedPlayer);
        Assert.assertEquals(byPlayer.get(0).getScore(), 0);
        matchRecordDao.delete(matchRecord);
        playerDao.delete(bonkedPlayer);
    }
}
