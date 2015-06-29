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

import eu.isdc.internship.application.Comparison;
import eu.isdc.internship.db.model.Statistics;
import eu.isdc.internship.db.model.User;

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
	
	@Test
	@Transactional
	public void testFilterStats() {
		Statistics stats1 = new Statistics(10, 20, 30, 40);
		Statistics stats2 = new Statistics(100, 200, 300, 400);
		Statistics stats3 = new Statistics(1000, 2000, 3000, 4000);
		statDAO.save(stats1);
		statDAO.save(stats2);
		statDAO.save(stats3);
		
		Assert.assertEquals(4, statDAO.readAll().size());
		Assert.assertEquals(1, statDAO.getAllStatsByWins(100, Comparison.GT).size());
		Assert.assertEquals(2, statDAO.getAllStatsByWins(100, Comparison.GTE).size());
		Assert.assertEquals(2, statDAO.getAllStatsByWins(100, Comparison.LT).size());
		Assert.assertEquals(3, statDAO.getAllStatsByWins(100, Comparison.LTE).size());
		Assert.assertEquals(1, statDAO.getAllStatsByWins(1000, Comparison.EQ).size());
		
		Assert.assertEquals(1, statDAO.getAllStatsByGamesPlayed(200, Comparison.GT).size());
		Assert.assertEquals(2, statDAO.getAllStatsByGamesPlayed(200, Comparison.GTE).size());
		Assert.assertEquals(2, statDAO.getAllStatsByGamesPlayed(200, Comparison.LT).size());
		Assert.assertEquals(3, statDAO.getAllStatsByGamesPlayed(200, Comparison.LTE).size());
		Assert.assertEquals(1, statDAO.getAllStatsByGamesPlayed(2000, Comparison.EQ).size());
	}
}
