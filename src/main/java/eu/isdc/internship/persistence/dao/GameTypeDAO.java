package eu.isdc.internship.db.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import eu.isdc.internship.db.model.GameType;

@Repository
public class GameTypeDAO extends GenericDAO<GameType, Long> {

	/**
	 * Get a list of game types with given height and width
	 * @param height
	 * 		The height of the game board
	 * @param width
	 * 		The width of the game board
	 * @return List of game types with specified shape
	 */
	@SuppressWarnings("unchecked")
	public List<GameType> getAllGamesByShape(final int height, final int width) {
		final DetachedCriteria criteria = DetachedCriteria.forClass(GameType.class);
		criteria.add(Restrictions.eq("m", height));
		criteria.add(Restrictions.eq("n", width));
		return (List<GameType>)hibernateTemplate.findByCriteria(criteria);
	}
}
