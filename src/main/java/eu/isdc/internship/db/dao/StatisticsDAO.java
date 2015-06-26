package eu.isdc.internship.db.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import eu.isdc.internship.application.Comparison;
import eu.isdc.internship.db.model.Statistics;
import eu.isdc.internship.db.model.User;

@Repository
public class StatisticsDAO extends GenericDAO<Statistics, Long> {

	@Autowired
	private UserDAO userDAO;
	
	/**
	 * Delete is overridden to make sure the Move instance is also deleted from the collection of its parent User entity
	 */
	@Override
	public Statistics delete(Statistics stats) {
		User user = stats.getUser();
		user.setStatistic(null);
		userDAO.save(user);
		hibernateTemplate.delete(stats);
		return stats;
	}
	
	@SuppressWarnings("unchecked")
	public List<Statistics> getAllStatsByWins(int wins, Comparison comp) {
		final DetachedCriteria criteria = DetachedCriteria.forClass(Statistics.class);
		switch(comp) {
		case GT: criteria.add(Restrictions.gt("nrOfWins", wins)); break;
		case LT: criteria.add(Restrictions.lt("nrOfWins", wins)); break;
		case GTE: criteria.add(Restrictions.ge("nrOfWins", wins)); break;
		case LTE: criteria.add(Restrictions.le("nrOfWins", wins)); break;
		default: criteria.add(Restrictions.eq("nrOfWins", wins)); break;
		}
		return (List<Statistics>)hibernateTemplate.findByCriteria(criteria);
	}
	
	@SuppressWarnings("unchecked")
	public List<Statistics> getAllStatsByGamesPlayed(int played, Comparison comp) {
		final DetachedCriteria criteria = DetachedCriteria.forClass(Statistics.class);
		switch(comp) {
		case GT: criteria.add(Restrictions.gt("nrOfPlayedGames", played)); break;
		case LT: criteria.add(Restrictions.lt("nrOfPlayedGames", played)); break;
		case GTE: criteria.add(Restrictions.ge("nrOfPlayedGames", played)); break;
		case LTE: criteria.add(Restrictions.le("nrOfPlayedGames", played)); break;
		default: criteria.add(Restrictions.eq("nrOfPlayedGames", played)); break;
		}
		return (List<Statistics>)hibernateTemplate.findByCriteria(criteria);
	}
}
