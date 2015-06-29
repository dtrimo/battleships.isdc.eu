package eu.isdc.internship.db.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import eu.isdc.internship.db.model.AvailableBattleship;
import eu.isdc.internship.db.model.BattleshipPosition;
import eu.isdc.internship.db.model.StartConfig;

/**
 * 
 * @author Horia.Galbenu
 *
 */
@Repository
public class BattleshipPositionDAO extends GenericDAO<BattleshipPosition, Long> {
	
	@Autowired
	private AvailableBattleshipDAO avbDAO;
	
	@Autowired
	private StartConfigDAO configDAO;
	
	/**
	 * Delete is overridden to make sure the StartConfig instance is also deleted from the collection of its parent entities
	 */
	@Override
	public BattleshipPosition delete(BattleshipPosition position) {
		AvailableBattleship avb = position.getAv_BT();
		if (avb != null) {
			avb.getBT_Positions().remove(position);
			avbDAO.save(avb);
		}
		StartConfig config = position.getStartConfig();
		if (config != null) {
			config.getBT_Positions().remove(position);
			configDAO.save(config);
		}
		hibernateTemplate.delete(position);
		return position;
	}
}
