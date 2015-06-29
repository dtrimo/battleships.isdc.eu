package eu.isdc.internship.db.dao;

import java.util.Date;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import eu.isdc.internship.db.model.Friend;
import eu.isdc.internship.db.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/test-beans.xml")
@TransactionConfiguration(transactionManager="transactionManager")
public class TestFriendDAO {
	
	@Autowired
	private FriendDAO friendDAO;
	
	@Autowired
	private UserDAO userDAO;
	
	@Before
	public void initTests() {
		User user1 = new User("user1", "pass1", new Date());
		User user2 = new User("user2", "pass2", new Date());
		User user3 = new User("user3", "pass3", new Date());
		
		Friend friend1 = new Friend(user1, user2);
		Friend friend2 = new Friend(user3, user1);
		friendDAO.save(friend1);
		friendDAO.save(friend2);
		
		userDAO.save(user1);
		userDAO.save(user2);
		userDAO.save(user3);
	}
	
	@Test
	@Transactional
	public void testFriendship() {
		User user1 = userDAO.read((long)1);
		User user2 = userDAO.read((long)2);
		User user3 = userDAO.read((long)3);
		Assert.assertEquals("user1", user1.getName());
		Assert.assertEquals("user2", user2.getName());
		Assert.assertEquals("user3", user3.getName());
		
		Assert.assertEquals(2, friendDAO.getFriendsOfUser(user1).size());
		Assert.assertEquals(1, friendDAO.getFriendsOfUser(user2).size());
		Assert.assertEquals(1, friendDAO.getFriendsOfUser(user3).size());
		
		Assert.assertEquals(user2, friendDAO.getFriendsOfUser(user1).get(0).getUser2());
		Assert.assertEquals(user3, friendDAO.getFriendsOfUser(user1).get(1).getUser1());
		Assert.assertEquals(user1, friendDAO.getFriendsOfUser(user2).get(0).getUser1());
		Assert.assertEquals(user1, friendDAO.getFriendsOfUser(user3).get(0).getUser2());
	}
}
