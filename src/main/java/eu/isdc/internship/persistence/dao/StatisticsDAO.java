package eu.isdc.internship.persistence.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import eu.isdc.internship.persistence.model.Statistics;
import eu.isdc.internship.persistence.model.User;

/**
 * 
 * @author Horia.Galbenu
 * StatisticsDAO class - used for accessing Statistics entities
 */
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
	
}
