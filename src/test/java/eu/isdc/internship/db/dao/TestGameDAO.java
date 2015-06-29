package eu.isdc.internship.db.dao;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import eu.isdc.internship.db.model.Game;
import eu.isdc.internship.db.model.Move;
import eu.isdc.internship.db.model.StartConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/test-beans.xml")
@TransactionConfiguration(transactionManager="transactionManager")
public class TestGameDAO {
	
	@Autowired
	private GameDAO gameDAO;
	
	@Autowired
	private StartConfigDAO confDAO;
	
	@Autowired
	private AvailableBattleshipDAO abtDAO;
	
	@Autowired
	private MoveDAO moveDAO;
	
	@Before
	public void initTests() {
		Game game = new Game(new Date());
		
		Move move1 = new Move(1, new Date(), 2, 3);
		Move move2 = new Move(10, new Date(), 20, 30);
		ArrayList<Move> moveList = new ArrayList<Move>();
		moveList.add(move1);
		moveList.add(move2);
		move1.setGame(game);
		move2.setGame(game);
		game.setMoves(moveList);
		
		StartConfig conf1 = new StartConfig();
		StartConfig conf2 = new StartConfig();
		ArrayList<StartConfig> confList = new ArrayList<StartConfig>();
		confList.add(conf1);
		confList.add(conf2);
		conf1.setGame(game);
		conf2.setGame(game);
		game.setStartConfigs(confList);
		
		gameDAO.save(game);
	}
	
	@Test
	@Transactional
	public void testMoveMapping() {
		Assert.assertEquals(2, moveDAO.readAll().size());
		Game game = gameDAO.readAll().get(0);
		
		Assert.assertEquals(2, game.getMoves().size());
		moveDAO.delete(moveDAO.readAll().get(1));
		game = gameDAO.readAll().get(0);
		Assert.assertEquals(1, game.getMoves().size());
		
		gameDAO.delete(gameDAO.readAll().get(0));
		Assert.assertEquals(0, moveDAO.readAll().size());
	}
	
	@Test
	@Transactional
	public void testStartConfigMapping() {
		Assert.assertEquals(2, confDAO.readAll().size());
		Game game = gameDAO.readAll().get(0);
		
		Assert.assertEquals(2, game.getStartConfigs().size());
		confDAO.delete(confDAO.readAll().get(1));
		game = gameDAO.readAll().get(0);
		Assert.assertEquals(1, game.getStartConfigs().size());
	}
	
	@Test
	@Transactional
	public void testGetGamesByIds() {
		ArrayList<Long> ids = new ArrayList<Long>();
		List<Game> results = gameDAO.readAll();
		
		ids.add(results.get(0).getGame_id());
		ids.add(Long.valueOf(2));
		
		results = gameDAO.getAllGamesByIds(ids);
		Assert.assertEquals(1, results.size());
		
		ids.clear();
		ids.add(results.get(0).getGame_id() + 1);
		results = gameDAO.getAllGamesByIds(ids);
		Assert.assertEquals(0, results.size());
	}
	
	@Test
	@Transactional
	public void testGetGamesByDate() {
		Assert.assertEquals(1, gameDAO.getAllGamesByDate(new Date()).size());
		Date otherDate = new Date();
		Calendar cal = Calendar.getInstance();
	    cal.setTime(otherDate);
	    cal.set(Calendar.MONTH, 0);
	    
	    Assert.assertEquals(0, gameDAO.getAllGamesByDate(cal.getTime()).size());
	    gameDAO.save(new Game(cal.getTime()));
	    Assert.assertEquals(1, gameDAO.getAllGamesByDate(cal.getTime()).size());
	    Assert.assertEquals(1, gameDAO.getAllGamesByDate(new Date()).size());
	}
}
