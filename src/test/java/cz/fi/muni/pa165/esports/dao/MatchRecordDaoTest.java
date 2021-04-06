package cz.fi.muni.pa165.esports.dao;

import cz.fi.muni.pa165.esports.PersistenceSampleApplicationContext;
import cz.fi.muni.pa165.esports.entity.MatchRecord;
import cz.fi.muni.pa165.esports.entity.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * @author jan gavlik
 */

@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@Transactional
public class MatchRecordDaoTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private MatchRecordDao matchRecordDao;

    @Autowired
    private PlayerDao playerDao;

    @Test
    public void createTest() {
        MatchRecord matchRecord = new MatchRecord();
        matchRecord.setName("Match");
        matchRecordDao.create(matchRecord);
        Assert.assertFalse(matchRecordDao.findAll().isEmpty());
    }

    @Test
    public void findByName(){
        MatchRecord matchRecord = matchRecordDao.findByName("Match");
        Long id = matchRecord.getId();
        Assert.assertEquals((long) id, 1L);
    }

    @Test
    public void findAll(){
        MatchRecord matchRecord1 = new MatchRecord();
        matchRecord1.setName("Match1");
        MatchRecord matchRecord2 = new MatchRecord();
        matchRecord2.setName("Match2");
        matchRecord2.setMatch_number(1L);
        matchRecordDao.create(matchRecord1);
        matchRecordDao.create(matchRecord2);
        List<MatchRecord> all = matchRecordDao.findAll();
        Assert.assertEquals(all.size(), 3);
    }

    @Test
    public void findByMatchNumber(){
        MatchRecord byMatchNumber = matchRecordDao.findByMatchNumber(1L);
        Assert.assertEquals(byMatchNumber.getName(),"Match2");
    }

    @Test
    public void findPlayers(){
        MatchRecord matchRecord = new MatchRecord();
        matchRecord.setName("Player Test");
        Player player = new Player();
        player.setName("Test, Subject");
        matchRecord.setPlayer(player);
        playerDao.create(player);
        matchRecordDao.create(matchRecord);
        List<Player> players = matchRecordDao.findPlayers();
        Assert.assertEquals(players.size(),1);
    }

    @Test
    public void removeTest(){
        MatchRecord byId = matchRecordDao.findById(1L);
        matchRecordDao.delete(byId);
        Assert.assertTrue(matchRecordDao.findAll().isEmpty());
    }





}
