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

import eu.isdc.internship.db.model.AvailableBattleship;
import eu.isdc.internship.db.model.BattleshipModel;
import eu.isdc.internship.db.model.BattleshipPosition;
import eu.isdc.internship.db.model.Game;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/test-beans.xml")
@TransactionConfiguration(transactionManager="transactionManager")
public class TestAvailableBattleshipDAO {

	@Autowired
	private AvailableBattleshipDAO avbDAO;
	
	@Autowired
	private GameDAO gameDAO;
	
	@Autowired
	private BattleshipModelDAO modelDAO;
	
	@Autowired
	private BattleshipPositionDAO shipPosDAO;
	
	@Before
	public void initTests() {
		AvailableBattleship avb1 = new AvailableBattleship();
		AvailableBattleship avb2 = new AvailableBattleship();
		ArrayList<AvailableBattleship> avbList = new ArrayList<AvailableBattleship>();
		avbList.add(avb1);
		avbList.add(avb2);
		
		Game game = new Game(new Date(), 10, 20);
		game.setAvailableBTs(avbList);
		avb1.setGame(game);
		avb2.setGame(game);
		
		BattleshipModel model = new BattleshipModel("model");
		model.setAv_BT(avbList);
		avb1.setModel(model);
		avb2.setModel(model);
		
		BattleshipPosition pos1 = new BattleshipPosition(1,2,3,4,5);
		BattleshipPosition pos2 = new BattleshipPosition(10,20,30,40,50);
		BattleshipPosition pos3 = new BattleshipPosition(10,20,30,40,50);
		ArrayList<BattleshipPosition> posList1 = new ArrayList<BattleshipPosition>();
		ArrayList<BattleshipPosition> posList2 = new ArrayList<BattleshipPosition>();
		posList1.add(pos1);
		pos1.setAv_BT(avb1);
		avb1.setBT_Positions(posList1);
		posList2.add(pos2);
		posList2.add(pos3);
		pos2.setAv_BT(avb2);
		pos3.setAv_BT(avb2);
		avb2.setBT_Positions(posList2);
		
		gameDAO.save(game);
		modelDAO.save(model);
	}
	
	@Test
	@Transactional
	public void testGameMapping() {
		Assert.assertEquals(2, avbDAO.readAll().size());
		Assert.assertEquals(1, gameDAO.readAll().size());
		Game game = gameDAO.readAll().get(0);
		List<AvailableBattleship> avbList = game.getAvailableBTs();
		Assert.assertEquals(2, avbList.size());

		avbDAO.delete(avbDAO.readAll().get(1));
		Assert.assertEquals(1, avbDAO.readAll().size());
		Assert.assertEquals(1, gameDAO.readAll().size());	
		Assert.assertEquals(1, gameDAO.readAll().get(0).getAvailableBTs().size());
		
		avbDAO.delete(avbDAO.readAll().get(0));
		Assert.assertEquals(0, avbDAO.readAll().size());
		Assert.assertEquals(1, gameDAO.readAll().size());	
		Assert.assertEquals(0, gameDAO.readAll().get(0).getAvailableBTs().size());
	}
	
	@Test
	@Transactional
	public void testBattleshipModelMapping() {
		Assert.assertEquals(2, avbDAO.readAll().size());
		Assert.assertEquals(1, modelDAO.readAll().size());
		BattleshipModel model = modelDAO.readAll().get(0);
		List<AvailableBattleship> avbList = model.getAv_BT();
		Assert.assertEquals(2, avbList.size());

		avbDAO.delete(avbDAO.readAll().get(1));
		Assert.assertEquals(1, avbDAO.readAll().size());
		Assert.assertEquals(1, modelDAO.readAll().size());	
		Assert.assertEquals(1, modelDAO.readAll().get(0).getAv_BT().size());
		
		avbDAO.delete(avbDAO.readAll().get(0));
		Assert.assertEquals(0, avbDAO.readAll().size());
		Assert.assertEquals(1, modelDAO.readAll().size());	
		Assert.assertEquals(0, modelDAO.readAll().get(0).getAv_BT().size());
	}
	
	@Test
	@Transactional
	public void testBattleshipPositionMapping() {
		Assert.assertEquals(3, shipPosDAO.readAll().size());
		AvailableBattleship avb1 = avbDAO.readAll().get(0);
		AvailableBattleship avb2 = avbDAO.readAll().get(1);
		
		Assert.assertEquals(1, avb1.getBT_Positions().size());
		Assert.assertEquals(2, avb2.getBT_Positions().size());
		
		shipPosDAO.delete(shipPosDAO.readAll().get(0));
		Assert.assertEquals(2, shipPosDAO.readAll().size());
		Assert.assertEquals(0, avb1.getBT_Positions().size());
		Assert.assertEquals(2, avb2.getBT_Positions().size());
		
		shipPosDAO.delete(shipPosDAO.readAll().get(0));
		Assert.assertEquals(1, shipPosDAO.readAll().size());
		Assert.assertEquals(0, avb1.getBT_Positions().size());
		Assert.assertEquals(1, avb2.getBT_Positions().size());
		
		shipPosDAO.delete(shipPosDAO.readAll().get(0));
		Assert.assertEquals(0, shipPosDAO.readAll().size());
		Assert.assertEquals(0, avb1.getBT_Positions().size());
		Assert.assertEquals(0, avb2.getBT_Positions().size());
	}
}
