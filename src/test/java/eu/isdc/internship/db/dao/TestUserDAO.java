package eu.isdc.internship.db.dao;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import eu.isdc.internship.persistence.dao.MoveDAO;
import eu.isdc.internship.persistence.dao.StartConfigDAO;
import eu.isdc.internship.persistence.dao.StatisticsDAO;
import eu.isdc.internship.persistence.dao.UserDAO;
import eu.isdc.internship.persistence.model.Move;
import eu.isdc.internship.persistence.model.StartConfig;
import eu.isdc.internship.persistence.model.Statistics;
import eu.isdc.internship.persistence.model.User;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/test-beans.xml")
@TransactionConfiguration(transactionManager="transactionManager")
public class TestUserDAO{
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private StatisticsDAO statDAO;
	
	@Autowired
	private MoveDAO moveDAO;
	
	@Autowired
	private StartConfigDAO confDAO;
	
	@Before
    public void initTests() {
		User entity = new User("user1", "pass1", new Date());
		Statistics stat = new Statistics(1, 2, 3, 4);
		entity.setStatistic(stat);
		stat.setUser(entity);
		
		Move move1 = new Move(1, new Date(), 2, 3);
		Move move2 = new Move(10, new Date(), 20, 30);
		ArrayList<Move> moveList = new ArrayList<Move>();
		moveList.add(move1);
		moveList.add(move2);
		move1.setUser(entity);
		move2.setUser(entity);
		entity.setMove(moveList);
		
		StartConfig conf1 = new StartConfig();
		StartConfig conf2 = new StartConfig();
		ArrayList<StartConfig> confList = new ArrayList<StartConfig>();
		confList.add(conf1);
		confList.add(conf2);
		conf1.setUser(entity);
		conf2.setUser(entity);
		entity.setStartConfig(confList);
		
		userDAO.save(entity);
    }
	
	@Test
	@Transactional
	public void testUserStatsMapping() {
		Assert.assertEquals(1, statDAO.readAll().size());
		
		userDAO.delete(userDAO.readAll().get(0));
		Assert.assertEquals(0, statDAO.readAll().size());
	}
	
	@Test
	@Transactional
	public void testMoveMapping() {
		Assert.assertEquals(2, moveDAO.readAll().size());
		User user = userDAO.readAll().get(0);
		
		Assert.assertEquals(2, user.getMove().size());
		moveDAO.delete(moveDAO.readAll().get(1));
		user = userDAO.readAll().get(0);
		Assert.assertEquals(1, user.getMove().size());
		
		userDAO.delete(userDAO.readAll().get(0));
		Assert.assertEquals(0, moveDAO.readAll().size());
	}
	
	@Test
	@Transactional
	public void testStartConfigMapping() {
		Assert.assertEquals(2, confDAO.readAll().size());
		User user = userDAO.readAll().get(0);
		
		Assert.assertEquals(2, user.getStartConfig().size());
		confDAO.delete(confDAO.readAll().get(1));
		user = userDAO.readAll().get(0);
		Assert.assertEquals(1, user.getStartConfig().size());
	}
	
	@Test
	@Transactional
	public void testGetUserByName() {
		User user1 = userDAO.getUserByName("user1");
		User user2 = userDAO.getUserByName("user2");
		Assert.assertNotNull(user1);
		Assert.assertNull(user2);
		
		Assert.assertEquals("user1", user1.getName());
		Assert.assertEquals("pass1", user1.getPassword());
	}
	
	@Test
	@Transactional
	public void testGetUsersByIds() {
		ArrayList<Long> ids = new ArrayList<Long>();
		List<User> results = userDAO.readAll();
		
		ids.add(results.get(0).getUserId());
		ids.add(Long.valueOf(2));
		
		results = userDAO.getAllUsersByIds(ids);
		Assert.assertEquals(1, results.size());
		
		ids.clear();
		ids.add(results.get(0).getUserId() + 1);
		results = userDAO.getAllUsersByIds(ids);
		Assert.assertEquals(0, results.size());
	}
	
	@Test
	@Transactional
	public void testRead() {
		List<User> results = userDAO.readAll();
		Assert.assertEquals(1, results.size());
		
		User user = results.get(0);
		Assert.assertEquals(user.getName(), userDAO.read(user.getUserId()).getName());
		Assert.assertEquals(user.getBirthDate(), userDAO.read(user.getUserId()).getBirthDate());
		Assert.assertEquals(user.getPassword(), userDAO.read(user.getUserId()).getPassword());
	}

	@Test
    @Transactional
    public void testSave(){
		List<User> results = userDAO.readAll();
		Assert.assertEquals(1, results.size());
		User user1 = results.get(0);		
		Assert.assertSame(user1, userDAO.read(user1.getUserId()));
		
		User user2 = new User("user2", "pass2", new Date());
		results = userDAO.readAll();
		userDAO.save(user2);
		Assert.assertEquals(1, results.size());
		Assert.assertSame(user1, userDAO.read(user1.getUserId()));
		Assert.assertSame(user2, userDAO.read(user2.getUserId()));
		Assert.assertNotSame(user1, userDAO.read(user2.getUserId()));
    }
	
	@Test
	@Transactional
	public void testDelete() {
		List<User> results = userDAO.readAll();
		Assert.assertEquals(1, results.size());
		
		User user = results.get(0);
		userDAO.delete(user);
		
		results = userDAO.readAll();
		Assert.assertEquals(0, results.size());
	}
	
	@Test
	@Transactional
	public void testUpdate() {
		List<User> results = userDAO.readAll();
		User user1 = results.get(0);
		user1.setName("new name");
		
		userDAO.save(user1);
		Assert.assertEquals("new name", userDAO.read(user1.getUserId()).getName());
	}
}
