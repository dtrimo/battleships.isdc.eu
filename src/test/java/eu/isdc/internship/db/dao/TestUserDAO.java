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

import eu.isdc.internship.db.model.Statistics;
import eu.isdc.internship.db.model.User;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/test-beans.xml")
@TransactionConfiguration(transactionManager="transactionManager")
public class TestUserDAO{
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private StatisticsDAO statDAO;
	
	@Before
    public void initTest() {
		User entity = new User("user1", "pass1", new Date());
		Statistics stat = new Statistics();
		stat.setNrOfPlayedGames(1);
		stat.setNrOfRoundsToWin(2);
		//stat.setUser(entity);
		//entity.setStatistic(stat);
		entity.setStatistic(stat);
		stat.setUser(entity);
		userDAO.save(entity);
		//statDAO.save(stat);
		//statDAO.readAll();
		
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
		Assert.assertEquals(1, user1.getStatistic().getNrOfPlayedGames());
		
		Assert.assertEquals(1, statDAO.readAll().size());
		userDAO.delete(user1);
		Assert.assertEquals(0, statDAO.readAll().size());
	}
	
	@Test
	@Transactional
	public void testGetUsersByIds() {
		ArrayList<Long> ids = new ArrayList<Long>();
		List<User> results = userDAO.readAll();
		
		ids.add(results.get(0).getUser_id());
		ids.add(Long.valueOf(2));
		
		results = userDAO.getAllUsersByIds(ids);
		Assert.assertEquals(1, results.size());
		
		ids.clear();
		ids.add(results.get(0).getUser_id() + 1);
		results = userDAO.getAllUsersByIds(ids);
		Assert.assertEquals(0, results.size());
	}
	
	@Test
	@Transactional
	public void testRead() {
		List<User> results = userDAO.readAll();
		Assert.assertEquals(1, results.size());
		
		User user = results.get(0);
		Assert.assertEquals(user.getName(), userDAO.read(user.getUser_id()).getName());
		Assert.assertEquals(user.getBirthDate(), userDAO.read(user.getUser_id()).getBirthDate());
		Assert.assertEquals(user.getPassword(), userDAO.read(user.getUser_id()).getPassword());
	}

	@Test
    @Transactional
    public void testSave(){
		List<User> results = userDAO.readAll();
		Assert.assertEquals(1, results.size());
		User user1 = results.get(0);		
		Assert.assertSame(user1, userDAO.read(user1.getUser_id()));
		
		User user2 = new User("user2", "pass2", new Date());
		results = userDAO.readAll();
		userDAO.save(user2);
		Assert.assertEquals(1, results.size());
		Assert.assertSame(user1, userDAO.read(user1.getUser_id()));
		Assert.assertSame(user2, userDAO.read(user2.getUser_id()));
		Assert.assertNotSame(user1, userDAO.read(user2.getUser_id()));
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
		Assert.assertEquals("new name", userDAO.read(user1.getUser_id()).getName());
	}
}
