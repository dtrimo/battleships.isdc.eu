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
import eu.isdc.internship.persistence.dao.BattleshipPositionDAO;
import eu.isdc.internship.persistence.dao.StartConfigDAO;
import eu.isdc.internship.persistence.model.AvailableBattleship;
import eu.isdc.internship.persistence.model.BattleshipPosition;
import eu.isdc.internship.persistence.model.StartConfig;
import eu.isdc.internship.persistence.model.Transformation;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/test-beans.xml")
@TransactionConfiguration(transactionManager = "transactionManager")
public class TestBattleshipPositionDAO {

	@Autowired
	private BattleshipPositionDAO posDAO;

	@Autowired
	private AvailableBattleshipDAO avbDAO;

	@Autowired
	private StartConfigDAO configDAO;

	@Before
	public void initTests() {
		// List<Transformation> transf = new ArrayList<Transformation>();

		BattleshipPosition pos1 = new BattleshipPosition(new ArrayList<Transformation>(), 4, 5);
		BattleshipPosition pos2 = new BattleshipPosition(new ArrayList<Transformation>(), 40, 50);
		ArrayList<BattleshipPosition> posList = new ArrayList<BattleshipPosition>();
		posList.add(pos1);
		posList.add(pos2);

		AvailableBattleship avb = new AvailableBattleship();
		pos1.setAvailableBattleship(avb);
		pos2.setAvailableBattleship(avb);
		avb.setBattleshipPositions(posList);

		StartConfig config = new StartConfig();
		pos1.setStartConfig(config);
		pos2.setStartConfig(config);
		config.setBattleshipPositions(posList);

		avbDAO.save(avb);
		configDAO.save(config);
	}

	@Test
	@Transactional
	public void testAvailableBattleshipMapping() {
		Assert.assertEquals(2, posDAO.readAll().size());
		Assert.assertEquals(1, avbDAO.readAll().size());
		AvailableBattleship avb = avbDAO.readAll().get(0);
		List<BattleshipPosition> positions = avb.getBattleshipPositions();
		Assert.assertEquals(2, positions.size());

		posDAO.delete(posDAO.readAll().get(1));
		Assert.assertEquals(1, posDAO.readAll().size());
		Assert.assertEquals(1, avbDAO.readAll().size());
		Assert.assertEquals(1, avbDAO.readAll().get(0).getBattleshipPositions().size());

		posDAO.delete(posDAO.readAll().get(0));
		Assert.assertEquals(0, posDAO.readAll().size());
		Assert.assertEquals(1, avbDAO.readAll().size());
		Assert.assertEquals(0, avbDAO.readAll().get(0).getBattleshipPositions().size());
	}

	@Test
	@Transactional
	public void testStartConfigMapping() {
		Assert.assertEquals(2, posDAO.readAll().size());
		Assert.assertEquals(1, configDAO.readAll().size());
		StartConfig conf = configDAO.readAll().get(0);
		List<BattleshipPosition> positions = conf.getBattleshipPositions();
		Assert.assertEquals(2, positions.size());
		;

		posDAO.delete(posDAO.readAll().get(1));
		Assert.assertEquals(1, posDAO.readAll().size());
		Assert.assertEquals(1, configDAO.readAll().size());
		Assert.assertEquals(1, configDAO.readAll().get(0).getBattleshipPositions().size());

		posDAO.delete(posDAO.readAll().get(0));
		Assert.assertEquals(0, posDAO.readAll().size());
		Assert.assertEquals(1, configDAO.readAll().size());
		Assert.assertEquals(0, configDAO.readAll().get(0).getBattleshipPositions().size());
	}
}
