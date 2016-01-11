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

import eu.isdc.internship.persistence.dao.AvailableBattleshipDAO;
import eu.isdc.internship.persistence.dao.BattleshipModelDAO;
import eu.isdc.internship.persistence.dao.PositionDAO;
import eu.isdc.internship.persistence.model.AvailableBattleship;
import eu.isdc.internship.persistence.model.BattleshipModel;
import eu.isdc.internship.persistence.model.Position;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/test-beans.xml")
@TransactionConfiguration(transactionManager = "transactionManager")
public class TestBattleshipModelDAO {

	@Autowired
	private BattleshipModelDAO modelDAO;

	@Autowired
	private PositionDAO positionDAO;

	@Autowired
	private AvailableBattleshipDAO abtDAO;

	@Before
	public void initTests() {
		BattleshipModel shipModel = new BattleshipModel("model1");

		Position pos1 = new Position(1, 2);
		Position pos2 = new Position(10, 20);
		ArrayList<Position> posList = new ArrayList<Position>();
		posList.add(pos1);
		posList.add(pos2);
		pos1.setBattleshipModel(shipModel);
		pos2.setBattleshipModel(shipModel);
		shipModel.setPositions(posList);

		AvailableBattleship ab1 = new AvailableBattleship();
		AvailableBattleship ab2 = new AvailableBattleship();
		ArrayList<AvailableBattleship> abList = new ArrayList<AvailableBattleship>();
		abList.add(ab1);
		abList.add(ab2);
		ab1.setBattleshipModel(shipModel);
		ab2.setBattleshipModel(shipModel);
		shipModel.setAvailableBattleships(abList);

		modelDAO.save(shipModel);
	}

	@Test
	@Transactional
	public void testPositionMapping() {
		Assert.assertEquals(2, positionDAO.readAll().size());
		BattleshipModel model = modelDAO.readAll().get(0);

		Assert.assertEquals(2, model.getPositions().size());
		positionDAO.delete(positionDAO.readAll().get(1));
		model = modelDAO.readAll().get(0);
		Assert.assertEquals(1, model.getPositions().size());

		modelDAO.delete(modelDAO.readAll().get(0));
		Assert.assertEquals(0, positionDAO.readAll().size());
	}

	@Test
	@Transactional
	public void testAvailableBattleshipMapping() {
		Assert.assertEquals(2, abtDAO.readAll().size());
		BattleshipModel model = modelDAO.readAll().get(0);

		Assert.assertEquals(2, model.getAvailableBattleships().size());
		abtDAO.delete(abtDAO.readAll().get(1));
		model = modelDAO.readAll().get(0);
		Assert.assertEquals(1, model.getAvailableBattleships().size());
	}

	@Test
	@Transactional
	public void testGetBattleshipModelByName() {
		BattleshipModel model1 = modelDAO.getBattleshipModelsByName("model1").get(0);
		Assert.assertEquals(0, modelDAO.getBattleshipModelsByName("model2").size());
		Assert.assertNotNull(model1);

		BattleshipModel model2 = new BattleshipModel("model1");
		modelDAO.save(model2);
		Assert.assertEquals(2, modelDAO.getBattleshipModelsByName("model1").size());

		Assert.assertEquals("model1", model1.getName());
	}

	@Test
	@Transactional
	public void testGetBattleshipModelsByIds() {
		ArrayList<Long> ids = new ArrayList<Long>();
		List<BattleshipModel> results = modelDAO.readAll();

		ids.add(results.get(0).getModelId());
		ids.add(Long.valueOf(2));

		results = modelDAO.getAllBattleshipModelsByIds(ids);
		Assert.assertEquals(1, results.size());

		ids.clear();
		ids.add(results.get(0).getModelId() + 1);
		results = modelDAO.getAllBattleshipModelsByIds(ids);
		Assert.assertEquals(0, results.size());
	}
}
