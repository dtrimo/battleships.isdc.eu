package eu.isdc.internship.db.dao;

import java.util.ArrayList;
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

import eu.isdc.internship.db.model.BattleshipModel;
import eu.isdc.internship.db.model.Position;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/test-beans.xml")
@TransactionConfiguration(transactionManager="transactionManager")
public class TestPositionDAO {

	@Autowired
	private PositionDAO positionDAO;
	
	@Autowired
	private BattleshipModelDAO modelDAO;
	
	@Before
	public void initTests() {
		Position pos1 = new Position(1, 2);
		Position pos2 = new Position(10, 20);
		
		BattleshipModel model = new BattleshipModel("model");
		pos1.setModel(model);
		pos2.setModel(model);
		ArrayList<Position> posList = new ArrayList<Position>();
		posList.add(pos1);
		posList.add(pos2);
		model.setPositions(posList);
		
		modelDAO.save(model);
	}
	
	@Test
	@Transactional
	public void testBattleshipModelMapping() {
		Assert.assertEquals(2, positionDAO.readAll().size());
		Assert.assertEquals(1, modelDAO.readAll().size());
		
		BattleshipModel model = modelDAO.readAll().get(0);
		List<Position> posList = model.getPositions();
		Assert.assertEquals(2, posList.size());
		Assert.assertEquals(1, posList.get(0).getX());
		Assert.assertEquals(2, posList.get(0).getY());
		Assert.assertEquals(10, posList.get(1).getX());
		Assert.assertEquals(20, posList.get(1).getY());
		
		positionDAO.delete(posList.get(0));
		Assert.assertEquals(1, modelDAO.readAll().size());
		Assert.assertEquals(1, positionDAO.readAll().size());
		positionDAO.delete(posList.get(0));
		Assert.assertEquals(1, modelDAO.readAll().size());
		Assert.assertEquals(0, positionDAO.readAll().size());
		Assert.assertEquals(0, model.getPositions().size());
	}
}
