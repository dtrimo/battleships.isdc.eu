package eu.isdc.internship.db.dao;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import eu.isdc.internship.persistence.dao.StatisticsDAO;
import eu.isdc.internship.persistence.dao.UserDAO;
import eu.isdc.internship.persistence.model.Statistics;
import eu.isdc.internship.persistence.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/test-beans.xml")
@TransactionConfiguration(transactionManager="transactionManager")
public class TestStatisticsDAO {

	@Autowired
	private StatisticsDAO statDAO;
	
	@Autowired
	private UserDAO userDAO;
	
	@Before
	public void initTests() {
		Statistics stats = new Statistics(1, 2, 3, 4);
		User user = new User("name1", "pass1", new Date());
		user.setStatistic(stats);
		stats.setUser(user);
		
		statDAO.save(stats);
		userDAO.save(user);
	}
	
	@Test
	@Transactional
	public void testUserMapping() {
		Assert.assertEquals(1, statDAO.readAll().size());
		Assert.assertEquals(1, userDAO.readAll().size());
		User user = userDAO.readAll().get(0);
		Statistics stats = user.getStatistic();
		Assert.assertEquals(2, stats.getNrOfPlayedGames());
		Assert.assertEquals(1, stats.getNrOfWins());
		Assert.assertEquals(3, stats.getNrOfRoundsToWin());
		Assert.assertEquals(4, stats.getNrOfRundsToLose());
		
		
		statDAO.delete(statDAO.readAll().get(0));
		Assert.assertEquals(1, userDAO.readAll().size());
		Assert.assertEquals(0, statDAO.readAll().size());
		Assert.assertNull(user.getStatistic());
	}
	
}
