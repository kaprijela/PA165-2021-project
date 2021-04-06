package cz.fi.muni.pa165.tasks;

import cz.fi.muni.pa165.esports.PersistenceSampleApplicationContext;
import cz.fi.muni.pa165.esports.entity.MatchRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.transaction.annotation.Transactional;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * @author jan gavlik
 */

@ContextConfiguration(classes = PersistenceSampleApplicationContext.class)
@Transactional
public class MatchRecordDaoTest extends AbstractTestNGSpringContextTests {

	@Autowired
	private MatchRecordDao matchRecordDao;

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
	public void removeTest(){
		MatchRecord byId = matchRecordDao.findById(1L);
		matchRecordDao.delete(byId);
		Assert.assertTrue(matchRecordDao.findAll().isEmpty());
	}



}