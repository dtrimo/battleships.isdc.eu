package eu.isdc.internship.db.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import eu.isdc.internship.db.model.Game;
import eu.isdc.internship.db.model.Move;
import eu.isdc.internship.db.model.User;

/**
 * 
 * @author Horia.Galbenu
 * MoveDAO class - used for accessing Move entities
 */
@Repository
public class MoveDAO extends GenericDAO<Move, Long> {

	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private GameDAO gameDAO;
	
	/**
	 * Delete is overridden to make sure the Move instance is also deleted from the collection of its parent entities
	 */
	@Override
	public Move delete(final Move move) {
		User user = move.getUser();
		if(user != null) {
			user.getMove().remove(move);
			userDAO.save(user);
		}
		Game game = move.getGame();
		if(game != null) {
			game.getMoves().remove(move);
			gameDAO.save(game);
		}
		hibernateTemplate.delete(move);
		return move;
	}
}
