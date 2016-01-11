package eu.isdc.internship.persistence.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import eu.isdc.internship.persistence.model.AvailableBattleship;
import eu.isdc.internship.persistence.model.BattleshipModel;
import eu.isdc.internship.persistence.model.GameType;

/**
 * 
 * @author Horia.Galbenu
 * AvailableBattleshipDAO class - used for accessing AvailableBattleship entities
 */
@Repository
public class AvailableBattleshipDAO extends GenericDAO<AvailableBattleship, Long> {

	@Autowired
	private GameTypeDAO gameTypeDAO;
	
	@Autowired
	private BattleshipModelDAO modelDAO;
	
	/**
	 * Delete is overridden to make sure the StartConfig instance is also deleted from the collection of its parent entities
	 */
	@Override
	public AvailableBattleship delete(AvailableBattleship availableBattleship) {
		GameType gameType = availableBattleship.getGameType();
		if(gameType != null) {
			gameType.getAvailableBTs().remove(availableBattleship);
			gameTypeDAO.save(gameType);
		}
		BattleshipModel model = availableBattleship.getBattleshipModel();
		if(model != null) {
			model.getAvailableBattleships().remove(availableBattleship);
			modelDAO.save(model);
		}
		hibernateTemplate.delete(availableBattleship);
		return availableBattleship;
	}
}
