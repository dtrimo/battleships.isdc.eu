package eu.isdc.internship.db.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import eu.isdc.internship.db.model.BattleshipModel;
import eu.isdc.internship.db.model.Position;

@Repository
public class PositionDAO extends GenericDAO<Position, Long> {

	@Autowired
	private BattleshipModelDAO modelDAO;
	
	/**
	 * Delete is overridden to make sure the Position instance is also deleted from the collection of its parent entities
	 */
	@Override
	public Position delete(final Position position) {
		BattleshipModel model = position.getModel();
		if(model != null) {
			model.getPositions().remove(position);
			modelDAO.save(model);
		}
		hibernateTemplate.delete(position);
		return position;
	}
}
