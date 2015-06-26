package eu.isdc.internship.db.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import eu.isdc.internship.db.model.Move;
import eu.isdc.internship.db.model.User;

@Repository
public class MoveDAO extends GenericDAO<Move, Long> {

	@Autowired
	private UserDAO userDAO;
	
	/**
	 * Delete is overriden to make sure the move instance is also deleted from the collection of its parent User entity
	 */
	@Override
	public Move delete(final Move move) {
		User user = move.getUser();
		user.getMove().remove(move);
		userDAO.save(user);
		hibernateTemplate.delete(move);
		return move;
	}
}
