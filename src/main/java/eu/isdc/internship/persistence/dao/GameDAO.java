package eu.isdc.internship.persistence.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import eu.isdc.internship.persistence.model.Game;
import eu.isdc.internship.persistence.model.GameType;
import eu.isdc.internship.utils.DateFormatter;

/**
 * 
 * @author Horia.Galbenu
 * GameDAO class - used for accessing Game entities
 *
 */
@Repository
public class GameDAO extends GenericDAO<Game, Long> {
	
	@Autowired
	private GameTypeDAO gameTypeDAO;
	
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
	
	/**
	 * Delete is overridden to make sure the Move instance is also deleted from the collection of its parent entities
	 */
	@Override
	public Game delete(Game game) {
		GameType gameType = game.getGameType();
		if (gameType != null) {
			gameType.getGames().remove(game);
			gameTypeDAO.save(gameType);
		}
		hibernateTemplate.delete(game);
		return game;
	}
}
