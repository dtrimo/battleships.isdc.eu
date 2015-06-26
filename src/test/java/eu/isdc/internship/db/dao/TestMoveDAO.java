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

import eu.isdc.internship.db.model.Game;
import eu.isdc.internship.db.model.Move;
import eu.isdc.internship.db.model.StartConfig;
import eu.isdc.internship.db.model.Statistics;
import eu.isdc.internship.db.model.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/test-beans.xml")
@TransactionConfiguration(transactionManager="transactionManager")
public class TestMoveDAO {
	
	@Autowired
	private MoveDAO moveDAO;
	
	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private GameDAO gameDAO;
	
	@Autowired
	private StartConfigDAO confDAO;
	
	@Before
	public void initTests() {
		Move move1 = new Move(1, new Date(), 2, 3);
		Move move2 = new Move(10, new Date(), 20, 30);
		ArrayList<Move> moveList = new ArrayList<Move>();
		moveList.add(move1);
		moveList.add(move2);
		
		User user = new User("name1", "pass1", new Date());
		user.setMove(moveList);
		move1.setUser(user);
		move2.setUser(user);
		
		Game game = new Game(new Date(), 17, 14);
		game.setMoves(moveList);
		move1.setGame(game);
		move2.setGame(game);
		
		StartConfig conf = new StartConfig();
		conf.setMove(moveList);
		move1.setStartConfig(conf);
		move2.setStartConfig(conf);
		
		userDAO.save(user);
		gameDAO.save(game);
		confDAO.save(conf);
	}
	
	@Test
	@Transactional
	public void testUserMapping() {
		Assert.assertEquals(2, moveDAO.readAll().size());
		Assert.assertEquals(1, userDAO.readAll().size());
		User user = userDAO.readAll().get(0);
		List<Move> moves = user.getMove();
		Assert.assertEquals(2, moves.size());
		Assert.assertEquals(2, moves.get(0).getX());
		Assert.assertEquals(3, moves.get(0).getY());
		Assert.assertEquals(1, moves.get(0).getRound());

		Assert.assertEquals(20, moves.get(1).getX());
		Assert.assertEquals(30, moves.get(1).getY());
		Assert.assertEquals(10, moves.get(1).getRound());
		
		moveDAO.delete(moveDAO.readAll().get(1));
		Assert.assertEquals(1, moveDAO.readAll().size());
		Assert.assertEquals(1, userDAO.readAll().size());	
		Assert.assertEquals(1, userDAO.readAll().get(0).getMove().size());
		
		moveDAO.delete(moveDAO.readAll().get(0));
		Assert.assertEquals(0, moveDAO.readAll().size());
		Assert.assertEquals(1, userDAO.readAll().size());	
		Assert.assertEquals(0, userDAO.readAll().get(0).getMove().size());
	}
	
	@Test
	@Transactional
	public void testGameMapping() {
		Assert.assertEquals(2, moveDAO.readAll().size());
		Assert.assertEquals(1, gameDAO.readAll().size());
		Game game = gameDAO.readAll().get(0);
		List<Move> moves = game.getMoves();
		Assert.assertEquals(2, moves.size());
		Assert.assertEquals(2, moves.get(0).getX());
		Assert.assertEquals(3, moves.get(0).getY());
		Assert.assertEquals(1, moves.get(0).getRound());

		Assert.assertEquals(20, moves.get(1).getX());
		Assert.assertEquals(30, moves.get(1).getY());
		Assert.assertEquals(10, moves.get(1).getRound());
		
		moveDAO.delete(moveDAO.readAll().get(1));
		Assert.assertEquals(1, moveDAO.readAll().size());
		Assert.assertEquals(1, gameDAO.readAll().size());	
		Assert.assertEquals(1, gameDAO.readAll().get(0).getMoves().size());
		
		moveDAO.delete(moveDAO.readAll().get(0));
		Assert.assertEquals(0, moveDAO.readAll().size());
		Assert.assertEquals(1, gameDAO.readAll().size());	
		Assert.assertEquals(0, gameDAO.readAll().get(0).getMoves().size());
	}
	
	@Test
	@Transactional
	public void testConfMapping() {
		Assert.assertEquals(2, moveDAO.readAll().size());
		Assert.assertEquals(1, confDAO.readAll().size());
		StartConfig conf = confDAO.readAll().get(0);
		List<Move> moves = conf.getMove();
		Assert.assertEquals(2, moves.size());
		Assert.assertEquals(2, moves.get(0).getX());
		Assert.assertEquals(3, moves.get(0).getY());
		Assert.assertEquals(1, moves.get(0).getRound());

		Assert.assertEquals(20, moves.get(1).getX());
		Assert.assertEquals(30, moves.get(1).getY());
		Assert.assertEquals(10, moves.get(1).getRound());
		
		moveDAO.delete(moveDAO.readAll().get(1));
		Assert.assertEquals(1, moveDAO.readAll().size());
		Assert.assertEquals(1, confDAO.readAll().size());	
		Assert.assertEquals(1, confDAO.readAll().get(0).getMove().size());
		
		moveDAO.delete(moveDAO.readAll().get(0));
		Assert.assertEquals(0, moveDAO.readAll().size());
		Assert.assertEquals(1, confDAO.readAll().size());	
		Assert.assertEquals(0, confDAO.readAll().get(0).getMove().size());
	}
}
