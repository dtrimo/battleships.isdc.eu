package eu.isdc.internship.db.dao;

import java.util.ArrayList;
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

import eu.isdc.internship.db.model.AvailableBattleship;
import eu.isdc.internship.db.model.Game;
import eu.isdc.internship.db.model.GameType;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/test-beans.xml")
@TransactionConfiguration(transactionManager="transactionManager")
public class TestGameTypeDAO {

	@Autowired
	private GameTypeDAO gameTypeDAO;
	
	@Autowired
	private AvailableBattleshipDAO abtDAO;
	
	@Autowired
	private GameDAO gameDAO;
	
	@Before
	public void initTests() {
		GameType gameType = new GameType(10,20);
		
		AvailableBattleship ab1 = new AvailableBattleship();
		AvailableBattleship ab2 = new AvailableBattleship();
		ArrayList<AvailableBattleship> abList = new ArrayList<AvailableBattleship>();
		abList.add(ab1);
		abList.add(ab2);
		ab1.setGameType(gameType);
		ab2.setGameType(gameType);
		gameType.setAvailableBTs(abList);
		
		Game game1 = new Game(new Date());
		Game game2 = new Game(new Date());
		ArrayList<Game> gameList = new ArrayList<Game>();
		gameList.add(game1);
		gameList.add(game2);
		game1.setGameType(gameType);
		game2.setGameType(gameType);
		gameType.setGames(gameList);
		
		gameTypeDAO.save(gameType);
	}
	
	@Test
	@Transactional
	public void testAvailableBattleshipMapping() {
		Assert.assertEquals(2, abtDAO.readAll().size());
		GameType gameType = gameTypeDAO.readAll().get(0);
		
		Assert.assertEquals(2, gameType.getAvailableBTs().size());
		abtDAO.delete(abtDAO.readAll().get(1));
		gameType = gameTypeDAO.readAll().get(0);
		Assert.assertEquals(1, gameType.getAvailableBTs().size());
	}
	
	@Test
	@Transactional
	public void testGameMapping() {
		Assert.assertEquals(2, gameDAO.readAll().size());
		GameType gameType = gameTypeDAO.readAll().get(0);
		
		Assert.assertEquals(2, gameType.getGames().size());
		gameDAO.delete(gameDAO.readAll().get(1));
		gameType = gameTypeDAO.readAll().get(0);
		Assert.assertEquals(1, gameType.getGames().size());
	}
	
	@Test
	@Transactional
	public void testGetGameTypesByShape() {
		Assert.assertEquals(1, gameTypeDAO.getAllGamesByShape(10, 20).size());
		Assert.assertEquals(0, gameTypeDAO.getAllGamesByShape(20, 10).size());
		Assert.assertEquals(0, gameTypeDAO.getAllGamesByShape(5, 17).size());
		
		gameTypeDAO.save(new GameType(10, 20));
		Assert.assertEquals(2, gameTypeDAO.getAllGamesByShape(10, 20).size());
	}
}
