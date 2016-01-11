package eu.isdc.internship.db.dao;

import java.util.ArrayList;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import eu.isdc.internship.persistence.dao.GameDAO;
import eu.isdc.internship.persistence.dao.StartConfigDAO;
import eu.isdc.internship.persistence.dao.UserDAO;
import eu.isdc.internship.persistence.model.Game;
import eu.isdc.internship.persistence.model.StartConfig;
import eu.isdc.internship.persistence.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/test-beans.xml")
@TransactionConfiguration(transactionManager="transactionManager")
public class TestStartConfigDAO {

	@Autowired
	private StartConfigDAO confDAO;
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private GameDAO gameDAO;
	
	@Before
	public void initTests() {
		StartConfig conf1 = new StartConfig();
		StartConfig conf2 = new StartConfig();
		ArrayList<StartConfig> confList = new ArrayList<StartConfig>();
		confList.add(conf1);
		confList.add(conf2);
		
		User user = new User("name1", "pass1", new Date());
		user.setStartConfig(confList);
		conf1.setUser(user);
		conf2.setUser(user);
		
		Game game = new Game(new Date());
		game.setStartConfigs(confList);
		conf1.setGame(game);
		conf2.setGame(game);
		
		userDAO.save(user);
		gameDAO.save(game);
	}
	
	@Test
	@Transactional
	public void testUserMapping() {
		Assert.assertEquals(2, confDAO.readAll().size());
		Assert.assertEquals(1, userDAO.readAll().size());
		
		confDAO.delete(confDAO.readAll().get(1));
		Assert.assertEquals(1, confDAO.readAll().size());
		Assert.assertEquals(1, userDAO.readAll().size());	
		Assert.assertEquals(1, userDAO.readAll().get(0).getStartConfig().size());
		
		confDAO.delete(confDAO.readAll().get(0));
		Assert.assertEquals(0, confDAO.readAll().size());
		Assert.assertEquals(1, userDAO.readAll().size());	
		Assert.assertEquals(0, userDAO.readAll().get(0).getStartConfig().size());
	}
	
	@Test
	@Transactional
	public void testGameMapping() {
		Assert.assertEquals(2, confDAO.readAll().size());
		Assert.assertEquals(1, gameDAO.readAll().size());
		
		confDAO.delete(confDAO.readAll().get(1));
		Assert.assertEquals(1, confDAO.readAll().size());
		Assert.assertEquals(1, gameDAO.readAll().size());	
		Assert.assertEquals(1, gameDAO.readAll().get(0).getStartConfigs().size());
		
		confDAO.delete(confDAO.readAll().get(0));
		Assert.assertEquals(0, confDAO.readAll().size());
		Assert.assertEquals(1, gameDAO.readAll().size());	
		Assert.assertEquals(0, gameDAO.readAll().get(0).getStartConfigs().size());
	}
}
