package eu.isdc.internship.persistence.dao;


import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import eu.isdc.internship.persistence.model.User;

/**
 * 
 * @author Horia.Galbenu
 * UserDAO class - used for accessing User entities
 */
@Repository
@SuppressWarnings("unchecked")
public class UserDAO extends GenericDAO<User, Long>{
	
	/**
	 * Get user by unique username
	 * @param userName
	 * 		Username represented as a string
	 * @return User with given username
	 */
	public User getUserByName(final String userName) {
		if(StringUtils.isEmpty(userName)) {
			return null;
		}
		final DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
		criteria.add(Restrictions.eq("name", userName));
		List<User> results = (List<User>)hibernateTemplate.findByCriteria(criteria);
		if(results.size() == 0) {
			return null;
		}
		User user = results.get(0);
		return user;
	}
	
	/**
	 * Get a list of users with given Ids
	 * @param userIds
	 * 		The list of user Ids to check for
	 * @return List of Users with given Ids
	 */
	public List<User> getAllUsersByIds(final List<Long> userIds) {
		if (userIds == null || userIds.isEmpty()) {
			return new ArrayList<User>();
		}
		final DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
		criteria.add(Restrictions.in("user_id", userIds));
		return readWithPagination(criteria, -1, -1);
	}
	
}
