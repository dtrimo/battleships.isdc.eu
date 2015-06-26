package eu.isdc.internship.db.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import eu.isdc.internship.db.model.AvailableBattleship;
import eu.isdc.internship.db.model.Game;

/**
 * 
 * @author Horia.Galbenu
 * AvailableBattleshipDAO class - used for accessing AvailableBattleship entities
 */
@Repository
public class AvailableBattleshipDAO extends GenericDAO<AvailableBattleship, Long> {

	@Autowired
	private GameDAO gameDAO;
	
	/**
	 * Delete is overridden to make sure the StartConfig instance is also deleted from the collection of its parent entities
	 */
	@Override
	public AvailableBattleship delete(AvailableBattleship availableBattleship) {
		Game game = availableBattleship.getGame();
		if(game != null) {
			game.getAvailableBTs().remove(availableBattleship);
			gameDAO.save(game);
		}
		hibernateTemplate.delete(availableBattleship);
		return availableBattleship;
	}
}
