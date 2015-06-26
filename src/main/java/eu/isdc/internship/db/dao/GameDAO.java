package eu.isdc.internship.db.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import eu.isdc.internship.application.DateFormatter;
import eu.isdc.internship.db.model.Game;

/**
 * 
 * @author Horia.Galbenu
 * GameDAO class - used for accessing Game entities
 *
 */
@Repository
public class GameDAO extends GenericDAO<Game, Long> {
	
	/**
	 * Get a list of games with given start date
	 * @param date
	 * 		The date to check for
	 * @return List of games played on given date
	 */
	@SuppressWarnings("unchecked")
	public List<Game> getAllGamesByDate(final Date date) {
		final DetachedCriteria criteria = DetachedCriteria.forClass(Game.class);
		criteria.add(Restrictions.le("date", DateFormatter.getFormattedFromDateTime(date)));
		criteria.add(Restrictions.ge("date", DateFormatter.getFormattedToDateTime(date)));
		return (List<Game>)hibernateTemplate.findByCriteria(criteria);
	}
	
	/**
	 * Get a list of games with given height and width
	 * @param height
	 * 		The height of the game board
	 * @param width
	 * 		The width of the game board
	 * @return List of games with specified shape
	 */
	@SuppressWarnings("unchecked")
	public List<Game> getAllGamesByShape(final int height, final int width) {
		final DetachedCriteria criteria = DetachedCriteria.forClass(Game.class);
		criteria.add(Restrictions.eq("m", height));
		criteria.add(Restrictions.eq("n", width));
		return (List<Game>)hibernateTemplate.findByCriteria(criteria);
	}
	
	/**
	 * Get a list of games with given Ids
	 * @param gameIds
	 * 		The list of game ids to check for
	 * @return List of Games with given ids
	 */
	public List<Game> getAllGamesByIds(final List<Long> gameIds) {
		if (gameIds == null || gameIds.size() == 0) {
			return new ArrayList<Game>();
		}
		final DetachedCriteria criteria = DetachedCriteria.forClass(Game.class);
		criteria.add(Restrictions.in("game_id", gameIds));
		return readWithPagination(criteria, -1, -1);
	}
}
